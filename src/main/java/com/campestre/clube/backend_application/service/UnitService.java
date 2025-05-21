package com.campestre.clube.backend_application.service;

import com.campestre.clube.backend_application.entity.Unit;
import com.campestre.clube.backend_application.entity.enums.UnitEnum;
import com.campestre.clube.backend_application.exceptions.BadRequestException;
import com.campestre.clube.backend_application.exceptions.NotFoundException;
import com.campestre.clube.backend_application.repository.UnitRepository;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UnitService {

    @Autowired
    private UnitRepository unitRepository;

     public Unit update(Integer id, Integer score){
        Optional<Unit> unit = unitRepository.findById(id);
         if (unit.isEmpty())
             throw new NotFoundException("Unit by id [%s] not found".formatted(id));
         if (score == null || score < 0)
             throw new BadRequestException("The unit score cannot be negative or zero");

        unit.get().setScore(score);
        return unitRepository.save(unit.get());
    }

    public Pair<List<String>, List<String>> resetAllScores() {
        Pair<List<String>, List<String>> pair = new Pair<>(new ArrayList<>(), new ArrayList<>());

        Arrays.stream(UnitEnum.values()).forEach(unitEnum -> {
            String name = unitEnum.name().replace("_", " ");
            Optional<Unit> unit = unitRepository.findBySurname(name);
            if (unit.isEmpty()) {
                pair.b.add(name);
            } else {
                pair.a.add(name);
                unit.get().setScore(0);
                unitRepository.save(unit.get());
            }
        });
        return pair;
    }

}
