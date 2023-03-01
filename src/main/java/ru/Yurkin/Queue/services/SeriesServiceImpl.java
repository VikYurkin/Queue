package ru.Yurkin.Queue.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.Yurkin.Queue.models.Series;
import ru.Yurkin.Queue.repositories.SeriesRepository;

@Service
public class SeriesServiceImpl implements SeriesService {
    private final SeriesRepository seriesRepository;
    @Autowired
    public SeriesServiceImpl(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    @Override
    public Series giveSeries() {
        Series series = seriesRepository.findBySeriesStartingWith("").orElse(null);
        series.setSeries(series.getSeries());
        return series;
    }

    @Override
    public Series giveNext() {return seriesRepository.save(giveSeries().next());}

    @Override
    public void save(Series series) {
        Series seriesInDb = giveSeries();
        seriesInDb.setSeries(series.getSeries());
        seriesRepository.save(seriesInDb);
    }
}
