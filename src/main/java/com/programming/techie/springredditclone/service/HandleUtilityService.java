package com.programming.techie.springredditclone.service;

import com.programming.techie.springredditclone.dto.StatementsDto;
import com.programming.techie.springredditclone.mapper.StatementsMapper;
import com.programming.techie.springredditclone.model.Statement;
import com.programming.techie.springredditclone.model.Token;
import com.programming.techie.springredditclone.repository.TokenDto;
import jdk.nashorn.internal.runtime.ParserException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@Slf4j
@AllArgsConstructor
public class HandleUtilityService {


    private StatementsMapper statementsMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public LocalDate FromStringToDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try {
            return LocalDate.parse(date, formatter);
        } catch (ParserException e) {
            log.error(" Error when parsing the String to  date ", e);
        }
        return null;
    }


    /**
     * Last Three Months
     */

    public static boolean areAllNull(Object... objects) {
        return Stream.of(objects).allMatch(Objects::isNull);
    }


    public LocalDate convertStrToDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try {
            return LocalDate.parse(date, formatter);
        } catch (Exception ex) {
            log.error("<======== Error when convert String to  Date ======>", ex);
        }
        return null;
    }


    public List<StatementsDto> mapListToDTO(List<Statement> statements) {
        List<StatementsDto> StatementsDtos = new ArrayList<>();
        statements.stream().parallel().forEach(statement -> {
            StatementsDto StatementsDto = new StatementsDto();
            StatementsDto.setAccountId(bCryptPasswordEncoder.encode(String.valueOf(statement.getAccountId())));
            StatementsDto.setAmount(new BigDecimal(statement.getAmount()));
            StatementsDto.setId(statement.getId());
            StatementsDto.setDatefield(this.stringToDate(statement.getDatefield()));
            StatementsDtos.add(StatementsDto);
        });
        return StatementsDtos;
    }


    public Token mapTokenDTOList(TokenDto tokenDto) {

        List<StatementsDto> StatementsDtos = new ArrayList<>();
        Token token = new Token();
        token.setUsername(tokenDto.getUsername());
        try {
            token.setExpiredate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tokenDto.getExpiredate()).toInstant());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        token.setTokenvalue(tokenDto.getTokenvalue());
        return token;
    }

    public LocalDate stringToDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try {
            return LocalDate.parse(date, formatter);
        } catch (Exception ex) {
            log.error("---- StatementServiceImpl.stringToDate error->", ex);
        }
        return null;
    }

}