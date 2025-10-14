package com.campestre.clube.backend_application.infrastructure.persistence.jpa.place;

import com.campestre.clube.backend_application.core.adapter.PlaceGateway;
import com.campestre.clube.backend_application.core.domain.Place;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaceJpaAdapter implements PlaceGateway {
    private final PlaceJpaRepository repository;

    public PlaceJpaAdapter(PlaceJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsByNameIgnoreCase(String name) {
        return repository.existsByNameIgnoreCase(name);
    }

    @Override
    public boolean existsByNameIgnoreCaseAndIdNot(String name, Long id) {
        return repository.existsByNameIgnoreCaseAndIdNot(name, id);
    }

    @Override
    public Place findById(Long id) {
        return repository.findById(id).map(PlaceEntityMapper::toDomain).get();
    }

    @Override
    public List<Place> findOrderedByRatingDesc() {
        return PlaceEntityMapper.toDomain(repository.findAllByOrderByRatingDesc());
    }

    @Override
    public Place save(Place place) {
        return PlaceEntityMapper.toDomain(
                repository.save(PlaceEntityMapper.toEntity(place))
        );
    }

    @Override
    public void removeById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
