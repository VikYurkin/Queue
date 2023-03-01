package ru.Yurkin.Queue.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.Yurkin.Queue.models.Series;

import java.util.Optional;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Integer> {
    Optional<Series> findBySeriesStartingWith(String startingWith);
}
