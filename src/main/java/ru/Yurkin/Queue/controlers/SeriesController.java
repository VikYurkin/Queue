package ru.Yurkin.Queue.controlers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.Yurkin.Queue.services.SeriesService;
import ru.Yurkin.Queue.dto.SeriesDTO;
import ru.Yurkin.Queue.models.Series;
import ru.Yurkin.Queue.util.CustomErrorResponse;
import ru.Yurkin.Queue.util.CustomNotCreatedException;
import java.util.List;

@RestController
@RequestMapping("/series")
public class SeriesController {

    private final SeriesService seriesService;
    private final ModelMapper modelMapper;

    @Autowired
    public SeriesController(SeriesService seriesService, ModelMapper modelMapper) {
        this.seriesService = seriesService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public SeriesDTO getSeries(){
        return convertToSeriesDTO(seriesService.giveSeries());
    }
    @GetMapping("/next")
    public SeriesDTO getNext(){
        return convertToSeriesDTO(seriesService.giveNext());
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> putSeries(@RequestBody @Valid SeriesDTO seriesDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors =  bindingResult.getFieldErrors();
            for(FieldError error:errors){
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new CustomNotCreatedException(errorMsg.toString());
        }
        seriesService.save(convertToSeries(seriesDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @ExceptionHandler
    private ResponseEntity<CustomErrorResponse> handleException(CustomNotCreatedException e){
        CustomErrorResponse response = new CustomErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private SeriesDTO convertToSeriesDTO(Series series){
        return modelMapper.map(series, SeriesDTO.class);
    }

    private Series convertToSeries(SeriesDTO seriesDTO){
        return modelMapper.map(seriesDTO, Series.class);
    }
}
