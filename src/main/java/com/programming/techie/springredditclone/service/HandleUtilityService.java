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

    public String convertToFormatDateDB(String dateValue) {
        try {
            LocalDate date = LocalDate.parse(dateValue);
            // print instance
            System.out.println("LocalDate before"
                    + " adding months: " + date);
            // add 3 months
            LocalDate returnvalue = date.plusMonths(3);
            String format = returnvalue.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            return format;
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

    public static boolean areAllNotNull(Object... objects) {
        return Stream.of(objects).allMatch(Objects::nonNull);
    }

    public String LastThreeMonthDate() {
        try {
            LocalDate date = LocalDate.now();
            // add 3 months
            LocalDate returnThreeMonthvalue = date.plusMonths(3);
            System.out.println("Converted Date is " + returnThreeMonthvalue.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            return returnThreeMonthvalue.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        } catch (ParserException e) {
            log.error(" Error when parsing the String to  date ", e);
        }
        return null;
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

//    public List<StatementsDto> mapListToDTO(List<Statement> statements) {
//        return
//                statements.stream().
//                        map(statementsMapper::mapDtoToStatementDto).
//                        collect(toList());
//
//    }

    public List<StatementsDto> mapListToDTO(List<Statement> statements) {
        List<StatementsDto> StatementsDtos = new ArrayList<>();
        statements.stream().parallel().forEach(statement -> {
            StatementsDto StatementsDto = new StatementsDto();
            StatementsDto.setAccountId(bCryptPasswordEncoder.encode(String.valueOf(statement.getAccountId())));
            StatementsDto.setAmount(new BigDecimal(statement.getAmount()));
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
/*
    String date = "2017-12-04T08:07:00Z";
        System.out.println(Instant.parse(date));
//        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
//        String caseStartDate = dateFormat.format(tokenDto.getExpiredate());
//        System.out.println(caseStartDate);
//        LocalDateTime localdatetime = LocalDateTime.parse(caseStartDate, dateFormat);
//        System.out.println(localdatetime);
//        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//        DateTime dt = dtf.parseDateTime("2013-07-17T03:58:00.000Z");
//        Date date = dt.toDate();

//        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-06-02 22:48:18");


//        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-06-02 22:48:18.703").toInstant();
//        System.out.println("sdf.format(tokenDto.getExpiredate())  "+sdf.format(tokenDto.getExpiredate()));
        // use SimpleDateFormat to define how to PARSE the INPUT

//        LocalDate parse = LocalDate.parse(tokenDto.getExpiredate(), formatter);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//                Date date = sdf.parse(tokenDto.getExpiredate());
//        Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis());
//        return LocalDate.parse(date, DateTimeFormat.forPattern("ddMMyyyy"));

//        System.out.println(
//                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")
//                        .withZone(ZoneId.of("UTC"))
//                        .format(Instant.parse(tokenDto.getExpiredate()))
//        );


*/