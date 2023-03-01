package ru.Yurkin.Queue.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PartOfSeriesTest {

    @Test
    public void shouldBeEmittedNextPart() {

        //given

        PartOfSeries partOfSeries = new PartOfSeries("b7");
        PartOfSeries partOfSeriesLastNumber = new PartOfSeries("b9");
        PartOfSeries partOfSeriesLast = new PartOfSeries("z9");

        //when
        PartOfSeries partOfSeriesNext = (PartOfSeries) partOfSeries.next();
        PartOfSeries partOfSeriesLastNumberNext = (PartOfSeries) partOfSeriesLastNumber.next();
        PartOfSeries partOfSeriesLastNext = (PartOfSeries) partOfSeriesLast.next();

        //then

        Assertions.assertEquals("b8", partOfSeriesNext.toString());
        Assertions.assertEquals("c0", partOfSeriesLastNumberNext.toString());
        Assertions.assertEquals("a0", partOfSeriesLastNext.toString());

    }

    @Test
    public void shouldProperlyHasNext() {

        //given
        PartOfSeries partOfSeries = new PartOfSeries("b7");
        PartOfSeries partOfSeriesLast = new PartOfSeries("z9");

        //then
        Assertions.assertTrue(partOfSeries.hasNext());
        Assertions.assertFalse(partOfSeriesLast.hasNext());
    }
}
