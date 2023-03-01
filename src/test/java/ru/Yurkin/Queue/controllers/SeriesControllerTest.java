package ru.Yurkin.Queue.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.Yurkin.Queue.controlers.SeriesController;
import ru.Yurkin.Queue.dto.SeriesDTO;
import ru.Yurkin.Queue.models.Series;
import ru.Yurkin.Queue.services.SeriesService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class SeriesControllerTest {
    @MockBean
    private SeriesService seriesService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MockMvc mockMvc;
    private Series newSeries = new Series("d0d0");
    private final SeriesDTO newSeriesDTO= new SeriesDTO();

    @Test
    public void shouldGetSeries() throws Exception {
        //given
        newSeriesDTO.setSeries("d0d0");
        Mockito.when(seriesService.giveSeries()).thenReturn(newSeries);
        SeriesController seriesController = new SeriesController(seriesService, modelMapper);
        //when
        mockMvc.perform(get("/series"))
                .andExpect(status().isOk());
        //then
        Assertions.assertNotNull(seriesController.getSeries());
        Assertions.assertEquals(newSeriesDTO.getSeries(), seriesController.getSeries().getSeries());
    }

    @Test
    public void shouldGetNextSeries() throws Exception {
        //given
        newSeriesDTO.setSeries("d0d1");
        Mockito.when(seriesService.giveNext()).thenReturn(newSeries.next());
        SeriesController seriesController = new SeriesController(seriesService, modelMapper);
        //when
        mockMvc.perform(get("/series/next"))
                .andExpect(status().isOk());
        //then
        Assertions.assertNotNull(seriesController.getNext());
        Assertions.assertEquals(newSeriesDTO.getSeries(), seriesController.getNext().getSeries());
    }

    @Test
    public void shouldSave() throws Exception {
        //given
        newSeriesDTO.setSeries("d0d0");
        newSeries =modelMapper.map(newSeriesDTO, Series.class) ;
        SeriesController seriesController = new SeriesController(seriesService, modelMapper);
        //when
        mockMvc.perform(post("/series")
                        .content(("""
                                {"series":"d0d0"}"""))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //then
        Mockito.verify(seriesService).save(newSeries);

    }

}
