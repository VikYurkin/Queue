package ru.Yurkin.Queue.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import ru.Yurkin.Queue.models.Series;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SeriesRepositoryIT {

    @Autowired
    private SeriesRepository seriesRepository;

    @Sql(scripts={"/sql/clearDbs.sql", "/sql/series.sql"})
    @Test
    public void shouldFindSeries(){
        //given
        Optional<Series> series = seriesRepository.findBySeriesStartingWith("");
        //then
        Assertions.assertTrue(series.isPresent());
        Assertions.assertEquals("b0b0", series.get().getSeries());
    }

    @Sql(scripts={"/sql/clearDbs.sql"})
    @Test
    public void shouldProperlySaveSeries(){
        //given
        Series series = new Series("c0c0");
        //when
        seriesRepository.save(series);
        Optional<Series> saved = seriesRepository.findBySeriesStartingWith("");
        //then
        Assertions.assertTrue(saved.isPresent());
        Assertions.assertEquals(series, saved.get());
    }

}
