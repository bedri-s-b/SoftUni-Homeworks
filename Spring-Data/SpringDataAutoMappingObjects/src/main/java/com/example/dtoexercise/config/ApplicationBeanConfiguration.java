package com.example.dtoexercise.config;

import com.example.dtoexercise.model.dto.GameAddDto;
import com.example.dtoexercise.model.entity.Game;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper mapper () throws NoSuchFieldException, NoSuchMethodException {
        ModelMapper modelMapper = new ModelMapper();
            modelMapper.typeMap(GameAddDto.class,Game.class)
                    .addMappings(mapping ->
                            mapping.map(GameAddDto::getThumbnailURL,Game::setImageThumbnail));

        Converter<String, LocalDate> localDateConverter = new Converter<>() {
            @Override
            public LocalDate convert(MappingContext<
                    String, LocalDate> context) {
                String source = context.getSource();
                return source == null ?
                        LocalDate.now() :
                        LocalDate.parse(context.getSource(),
                                DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            }
        };

       modelMapper.typeMap(GameAddDto.class,Game.class)
               .addMappings(mapping -> mapping.using(localDateConverter)
                       .map(GameAddDto::getReleaseDate,Game::setReleaseDate));

        return modelMapper;


    }





    //   ModelMapper modelMapper = new ModelMapper();
    //        modelMapper.
    //                typeMap(GameAddDto.class, Game.class)
    //                .addMappings(mapper ->
    //                        mapper.map(GameAddDto::getThumbnailURL,Game::setImageThumbnail));
    //        return modelMapper;

}
