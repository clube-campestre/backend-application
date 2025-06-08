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

     public Unit updateScoreById(Integer id, Integer score){
         UnitEnum.findByIdOrThrow(id);
         validateScore(score);
         Optional<Unit> unit = unitRepository.findById(id);

        unit.get().setScore(score);
        return unitRepository.save(unit.get());
    }

    public Unit increaseOrDecreaseTheScoreById(Integer id, Integer score, Boolean isSum){
        UnitEnum.findByIdOrThrow(id);
        validateScore(score);
        Optional<Unit> unit = unitRepository.findById(id);

        unit.get().setScore(isSum ? (unit.get().getScore() + score) : (unit.get().getScore() - score));
        return unitRepository.save(unit.get());
    }

    public Pair<List<String>, List<String>> resetAllScores() {
        Pair<List<String>, List<String>> pair = new Pair<>(new ArrayList<>(), new ArrayList<>());

        Arrays.stream(UnitEnum.values()).forEach(unitEnum -> {
            String name = unitEnum.name().replace("_", " ");
            Optional<Unit> unit = unitRepository.findBySurnameIgnoreCase(name);
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

    public List<Unit> getRanked() {
        List<Unit> units = unitRepository.findAllByOrderByScoreDesc();
        for (Unit unit : units) {
            unit.setSurname(UnitEnum.fromString(unit.getSurname()).getFormattedValue());
        }
        return units;
    }

    private void validateScore(Integer score) {
        if (score == null)
            throw new BadRequestException("O valor da pontuação não pode ser nulo.");
    }

    Unit findByIdOrThrow(Integer unitId) {
        return unitRepository.findById(unitId)
                .orElseThrow(() -> new NotFoundException("Não encontramos a unidade solicitada."));
    }

    Unit findBySurnameOrThrow(String surname) {
        return unitRepository.findBySurnameIgnoreCase(surname)
                .orElseThrow(() -> new NotFoundException("Não encontramos a unidade solicitada."));
    }
}
