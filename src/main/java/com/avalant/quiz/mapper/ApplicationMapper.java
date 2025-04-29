package com.avalant.quiz.mapper;

import com.avalant.quiz.dto.ApplicationRequestDto;
import com.avalant.quiz.dto.ApplicationResponseDto;
import com.avalant.quiz.entity.Application;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {
    ApplicationMapper INSTANCE = Mappers.getMapper(ApplicationMapper.class);

    Application toAppEntity(ApplicationRequestDto dto);

    ApplicationResponseDto toDto(Application entity);
}