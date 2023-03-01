package ru.Yurkin.Queue.services;

import ru.Yurkin.Queue.models.Series;

public interface SeriesService {
    Series giveSeries();
    Series giveNext();
    void save(Series series);
}
