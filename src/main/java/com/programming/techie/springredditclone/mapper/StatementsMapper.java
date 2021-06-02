package com.programming.techie.springredditclone.mapper;

import com.programming.techie.springredditclone.dto.StatementsDto;
import com.programming.techie.springredditclone.model.Statement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface StatementsMapper {

    //    @Mapping(source = "AccountId", target = "AccountId", qualifiedBy = BigDecimal.class)
//    @Mapping(source = "Id", target = "Id", qualifiedBy = BigDecimal.class)
    @Mapping(source = "accountId", target = "AccountId")
//    @Mapping(source = "datefield", target = "java.time.format.DateTimeFormatter stringToDate(datefield)")

    @Mapping(source = "id", target = "Id")
//    @Mapping(target = "Id", ignore = true)
    StatementsDto mapDtoToStatementDto(Statement subredditDto);

//    default BigDecimal map(String value) {
//        return value == null ? null : new BigDecimal(value);
//    }

//    default LocalDate stringToDate(String date) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//        return LocalDate.parse(date, formatter);
//    }


}
