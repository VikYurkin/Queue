package ru.Yurkin.Queue.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.Yurkin.Queue.models.Series;
import ru.Yurkin.Queue.repositories.SeriesRepository;

import java.util.Optional;

public class SeriesServiceImplTest {

    private SeriesRepository seriesRepository;
    private Series series;
    private SeriesServiceImpl seriesService;
    private final Series newSeries = new Series("d0d0");

    @BeforeEach
    public void init() {
        seriesRepository = Mockito.mock(SeriesRepository.class);

        seriesService = new SeriesServiceImpl(seriesRepository);

        Mockito.when(seriesRepository.findBySeriesStartingWith("")).thenReturn(Optional.of(newSeries));
        Mockito.when(seriesRepository.save(newSeries)).thenReturn((newSeries));
    }

    @Test
    public void shouldGiveSeries() {

        //when
        series=seriesService.giveSeries();

        //then
        Assertions.assertNotNull(series);
        Assertions.assertEquals("d0d0", series.getSeries());
        Assertions.assertEquals("d0d0", series.toString());
    }

    @Test
    public void shouldGiveNextSeries() {
        //when
        series=seriesService.giveNext();

        //then
        Assertions.assertNotNull(series);
        Assertions.assertEquals("d0d1", series.getSeries());
        Assertions.assertEquals("d0d1", series.toString());
    }

    @Test
    public void shouldBeSave() {

        //when
        seriesService.save(newSeries);

        //then
        Mockito.verify(seriesRepository).save(newSeries);
    }

}
