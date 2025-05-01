package com.avalant.quiz.mapper;

import com.avalant.quiz.dto.ApplicationRequestDto;
import com.avalant.quiz.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address toAddressEntity(ApplicationRequestDto dto);
}