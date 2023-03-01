package ru.Yurkin.Queue;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.Yurkin.Queue.models.Series;

@SpringBootApplication
public class QueueApplication {
	public static void main(String[] args) {SpringApplication.run(QueueApplication.class, args);}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
