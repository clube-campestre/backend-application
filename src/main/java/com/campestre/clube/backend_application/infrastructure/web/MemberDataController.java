package com.campestre.clube.backend_application.infrastructure.web;

import com.campestre.clube.backend_application.core.application.memberdata.*;
import com.campestre.clube.backend_application.core.application.memberdata.command.*;
import com.campestre.clube.backend_application.core.application.valueobject.Pagination;
import com.campestre.clube.backend_application.core.domain.MemberData;
import com.campestre.clube.backend_application.core.domain.MemberDataForClass;
import com.campestre.clube.backend_application.core.domain.MemberDataForUnit;
import com.campestre.clube.backend_application.core.domain.enums.ClassCategory;
import com.campestre.clube.backend_application.infrastructure.web.dtos.memberdata.*;
import com.campestre.clube.backend_application.infrastructure.web.mappers.MemberDataDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/members")
@CrossOrigin("*")
@Tag(name = "Member data Controller", description = "Member data routes")
public class MemberDataController {
    private final SaveMemberDataUseCase saveMemberDataUseCase;
    private final UpdateMemberDataUseCase updateMemberDataUseCase;
    private final UpdateMemberUnitAndClassUseCase updateMemberUnitAndClassUseCase;
    private final DeleteMemberDataUseCase deleteMemberDataUseCase;
    private final GetMemberDataByCpfUseCase getMemberDataByIdUseCase;
    private final ListMemberDataByClassCategoryAndPaginationUseCase listMemberDataByClassCategoryAndPaginationUseCase;
    private final ListMemberDataByFilterAndPaginationUseCase listMemberDataByFilterAndPaginationUseCase;
    private final ListMemberDataByUnitNameAndPaginationUseCase listMemberDataByUnitNameAndPaginationUseCase;
    private final ListMemberDataUseCase listMemberDataUseCase;

//    TODO não baixar imagem caso seja a default
//    TODO não calcular valor total no extrato em toda visualização
//    TODO arrumar parte de formatar birthdate
//    TODO arrumar acesso no token
//    TODO arrumar validação denúmero de celular
//    TODO criptografia na foto

    public MemberDataController(
            SaveMemberDataUseCase saveMemberDataUseCase,
            UpdateMemberDataUseCase updateMemberDataUseCase,
            UpdateMemberUnitAndClassUseCase updateMemberUnitAndClassUseCase,
            DeleteMemberDataUseCase deleteMemberDataUseCase,
            GetMemberDataByCpfUseCase getMemberDataByIdUseCase,
            ListMemberDataByClassCategoryAndPaginationUseCase listMemberDataByClassCategoryAndPaginationUseCase,
            ListMemberDataByFilterAndPaginationUseCase listMemberDataByFilterAndPaginationUseCase,
            ListMemberDataByUnitNameAndPaginationUseCase listMemberDataByUnitNameAndPaginationUseCase,
            ListMemberDataUseCase listMemberDataUseCase
    ) {
        this.saveMemberDataUseCase = saveMemberDataUseCase;
        this.updateMemberDataUseCase = updateMemberDataUseCase;
        this.updateMemberUnitAndClassUseCase = updateMemberUnitAndClassUseCase;
        this.deleteMemberDataUseCase = deleteMemberDataUseCase;
        this.getMemberDataByIdUseCase = getMemberDataByIdUseCase;
        this.listMemberDataByClassCategoryAndPaginationUseCase = listMemberDataByClassCategoryAndPaginationUseCase;
        this.listMemberDataByFilterAndPaginationUseCase = listMemberDataByFilterAndPaginationUseCase;
        this.listMemberDataByUnitNameAndPaginationUseCase = listMemberDataByUnitNameAndPaginationUseCase;
        this.listMemberDataUseCase = listMemberDataUseCase;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Endpoint for create member")
    public ResponseEntity<MemberDataResponseDto> register(
            @RequestPart("data") @Valid String jsonDto,
            @RequestPart(value = "file", required = false) MultipartFile file
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(MemberDataDtoMapper.toResponse(
                saveMemberDataUseCase.execute(MemberDataDtoMapper.toSaveCommand(jsonDto, file))
        ));
    }

    @GetMapping
    @Operation(summary = "Endpoint for list all member data")
    public ResponseEntity<List<MemberDataResponseDto>> getAll(){
        List<MemberData> members = listMemberDataUseCase.execute();
        if(members.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(MemberDataDtoMapper.toResponse(members));
    }

    @GetMapping("/{cpf}")
    @Operation(summary = "Endpoint for get member data by cpf")
    public ResponseEntity<MemberDataResponseDto> getById(@PathVariable String cpf){
        return ResponseEntity.status(HttpStatus.OK).body(MemberDataDtoMapper.toResponse(
                getMemberDataByIdUseCase.execute(new GetMemberDataByCpfCommand(cpf))
        ));
    }

    @GetMapping("/unit")
    @Operation(summary = "Endpoint for list member data by unit")
    public ResponseEntity<MemberDataForUnitResponseDto> getByUnit(
            @RequestParam(required = false) String unitName, @RequestParam Integer page, @RequestParam Integer size
    ){
        MemberDataForUnit memberDataForUnit = listMemberDataByUnitNameAndPaginationUseCase.execute(
                new ListMemberDataByUnitIdAndPaginationCommand(unitName, Pagination.of(page, size))
        );
        return ResponseEntity.status(HttpStatus.OK).body(MemberDataDtoMapper.toResponse(memberDataForUnit));
    }

    @GetMapping("/class")
    @Operation(summary = "Endpoint for list member data by class")
    public ResponseEntity<MemberDataForClassResponseDto> getByClass(
            @RequestParam(required = false) ClassCategory classCategory,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        MemberDataForClass memberDataForClass = listMemberDataByClassCategoryAndPaginationUseCase.execute(
                new ListMemberDataByClassCategoryAndPaginationCommand(classCategory, Pagination.of(page, size))
        );
        return ResponseEntity.status(HttpStatus.OK).body(MemberDataDtoMapper.toResponse(memberDataForClass));
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Endpoint for update member data by cpf")
    public ResponseEntity<MemberDataResponseDto> update(
            @RequestPart("data") @Valid String jsonDto,
            @RequestPart(value = "file", required = false) MultipartFile file
    ){
        return ResponseEntity.status(HttpStatus.OK).body(MemberDataDtoMapper.toResponse(
                updateMemberDataUseCase.execute(MemberDataDtoMapper.toUpdateCommand(jsonDto, file))
        ));
    }

    @PutMapping("/unit-and-class/{cpf}")
    @Operation(summary = "Endpoint for update member unit and class by cpf")
    public ResponseEntity<MemberDataResponseDto> updateUnitAndClass(
            @PathVariable String cpf, @RequestBody UpdateMemberUnitAndClassRequestDto dto
    ){
        return ResponseEntity.status(HttpStatus.OK).body(MemberDataDtoMapper.toResponse(
                updateMemberUnitAndClassUseCase.execute(MemberDataDtoMapper.toCommand(cpf, dto))
        ));
    }

    @DeleteMapping("/{cpf}")
    @Operation(summary = "Endpoint for remove member data by cpf")
    public ResponseEntity<Void> delete(@PathVariable String cpf) {
        deleteMemberDataUseCase.execute(new DeleteMemberDataCommand(cpf));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Endpoint for get member data by filter and pagination")
    @GetMapping("/filter")
    public ResponseEntity<GetByFilterAndPaginationMemberDataResponseDto> getByFilterAndPagination(
            @RequestParam(required = false)
            String unit,
            @RequestParam(required = false)
            String classCategory,
            @RequestParam(required = false)
            String name,

            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        Pair<List<MemberData>, Pagination> data = listMemberDataByFilterAndPaginationUseCase.execute(
                new ListMemberDataByFilterAndPaginationCommand(unit, classCategory, name, Pagination.of(page, size))
        );
        return ResponseEntity.ok(MemberDataDtoMapper.toResponse(data));
    }
}
