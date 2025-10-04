package com.campestre.clube.backend_application.core.exceptions;

public class ExceptionExtensions {
    public static final RuntimeException CONFLICT_TRANSPORT_SAME_COMPANY_AND_DRIVER =
            new ConflictException("Não é permitido cadastrar a mesma empresa e motorista para o transporte.");
    public static final RuntimeException NOT_FOUND_TRANSPORT =
            new NotFoundException("Não foi possível encontrar o transporte.");

    public static final RuntimeException CONFLICT_ACCOUNT_SAME_EMAIL =
            new ConflictException("Este email já está sendo utilizado.");
    public static final RuntimeException NOT_FOUND_ACCOUNT =
            new NotFoundException("Não foi possível encontrar a conta.");
    public static final RuntimeException BAD_REQUEST_ACCOUNT =
            new BadRequestException("Credenciais inválidas. Verifique seu e-mail e senha.");

    public static final RuntimeException CONFLICT_LOCAL_SAME_NAME =
            new ConflictException("Não é permitido cadastrar um local com nome duplicado.");
    public static final RuntimeException NOT_FOUND_LOCAL =
            new NotFoundException("Não foi possível encontrar o local.");

    public static final RuntimeException CONFLICT_MEMBER_DATA_SAME_CPF =
            new ConflictException("Não é permitido cadastrar um membro com CPF já existente.");
    public static final RuntimeException CONFLICT_MEMBER_DATA_SAME_CNS =
            new ConflictException("Não é permitido cadastrar um membro com CNS já existente.");
    public static final RuntimeException NOT_FOUND_MEMBER_DATA =
            new NotFoundException("Não foi possível encontrar o membro.");
    public static final RuntimeException BAD_REQUEST_MEDICAL_PROBLEM =
            new BadRequestException("O membro não pode ter medicação de um problema que ele não tem");

    public static final RuntimeException NOT_FOUND_UNIT =
            new NotFoundException("Não foi possível encontrar a unidade.");
    public static final RuntimeException BAD_REQUEST_UNIT_MUST_HAVE_COUNSELOR =
            new BadRequestException("A unidade deve ter pelo menos um conselheiro.");
    public static final RuntimeException BAD_REQUEST_UNIT_MUST_HAVE_ONLY_COUNSELOR =
            new BadRequestException("A unidade não pode ter mais de um conselheiro.");
    public static final RuntimeException BAD_REQUEST_UNIT_SCORE_MUST_NOT_BE_NULL =
            new BadRequestException("Não é permitido salvar um valor nulo na pontuação.");

    public static final RuntimeException BAD_REQUEST_CLASS_MUST_HAVE_INSTRUCTOR =
            new BadRequestException("A classe deve ter pelo menos um instrutor.");
    public static final RuntimeException BAD_REQUEST_CLASS_MUST_HAVE_ONLY_INSTRUCTOR =
            new BadRequestException("A classe não pode ter mais de um instrutor.");

    public static final RuntimeException NOT_FOUND_STATEMENT =
            new NotFoundException("Não foi possível encontrar a transferência.");
    public static final RuntimeException CONFLICT_STATEMENT_SAME_INFORMATION_AND_PRICE_AND_TRANSACTION_DATE_AND_TAG =
            new ConflictException("Não é permitido cadastrar uma transferência com descrição, valor, data e tag já existente.");

    public static final RuntimeException NOT_FOUND_TAG =
            new NotFoundException("Não foi possível encontrar a tag da transferência.");
    public static final RuntimeException CONFLICT_TAG_SAME_SURNAME =
            new ConflictException("Nome já utilizado em outra tag.");
    public static final RuntimeException CONFLICT_TAG_SAME_COLOR =
            new ConflictException("Cor já utilizada em outra tag.");
    public static final RuntimeException INVALID_REQUEST_TAG_WITH_ZERO_GOAL =
            new InvalidRequestException("Não é permitido cadastrar uma tag com meta zerada.");
    public static final RuntimeException NOT_FOUND_GOAL_BY_TAG =
            new NotFoundException("Não foi possível encontrar a tag da meta.");

    public static final RuntimeException INVALID_CNS =
            new InvalidRequestException("O número do CNS é inválido.");
    public static final RuntimeException INVALID_CEP =
            new InvalidRequestException("O número do CEP é inválido.");
    public static final RuntimeException INVALID_CPF =
            new InvalidRequestException("O número do CPF é inválido.");
    public static final RuntimeException INVALID_CELLPHONE_NUMBER =
            new InvalidRequestException("O número de telefone é inválido");

    public static final RuntimeException ERROR_ACCESS_TYPE_ENUM =
            new BadRequestException("Não foi possível encontrar o acesso da conta.");
    public static final RuntimeException ERROR_CLASS_CATEGORY_ENUM =
            new BadRequestException("Não foi possível encontrar a classe do membro.");
    public static final RuntimeException ERROR_CLASS_ROLE_ENUM =
            new BadRequestException("Não foi possível encontrar o papel da classe.");
    public static final RuntimeException ERROR_SEX_ENUM =
            new BadRequestException("Não foi possível encontrar o sexo do membro.");
    public static final RuntimeException ERROR_TSHIRT_SIZE_ENUM =
            new BadRequestException("Não foi possível encontrar o tamanho da camiseta.");
    public static final RuntimeException ERROR_UNIT_ENUM =
            new BadRequestException("Não foi possível encontrar a unidade do membro.");
    public static final RuntimeException ERROR_UNIT_ROLE_ENUM =
            new BadRequestException("Não foi possível encontrar o papel da unidade.");
}