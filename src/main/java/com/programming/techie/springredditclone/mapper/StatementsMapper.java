package com.programming.techie.springredditclone.mapper;

import com.programming.techie.springredditclone.dto.StatementsDto;
import com.programming.techie.springredditclone.model.Statement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface StatementsMapper {

    //    @Mapping(source = "AccountId", target = "AccountId", qualifiedBy = BigDecimal.class)
//    @Mapping(source = "Id", target = "Id", qualifiedBy = BigDecimal.class)
    @Mapping(source = "accountId", target = "AccountId")
    @Mapping(source = "id", target = "Id")
//    @Mapping(target = "Id", ignore = true)
    StatementsDto mapDtoToStatementDto(Statement subredditDto);
}
