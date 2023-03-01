package ru.Yurkin.Queue.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class SeriesTest {

    @Test
    public void shouldBeCreateSeries() {

        Series series = new Series("a3b7k9t6");

        Assertions.assertEquals("a3b7k9t6", series.toString());
    }


    @Test
    public void shouldBeCorrectNextSeries() {

        //given

        Series series = new Series("b7b7");
        Series seriesNextLastNumber = new Series("b7b9");
        Series seriesNextLastChar = new Series("b7z9");
        Series seriesNextGroup = new Series("z9z9");

        //then

        Assertions.assertEquals("b7b8", series.next().toString());
        Assertions.assertEquals("b7c0", seriesNextLastNumber.next().toString());
        Assertions.assertEquals("b8a0", seriesNextLastChar.next().toString());
        Assertions.assertEquals("a0a0a0", seriesNextGroup.next().toString());
    }
}
