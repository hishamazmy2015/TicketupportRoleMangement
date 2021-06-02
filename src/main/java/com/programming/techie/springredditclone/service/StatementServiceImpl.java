package com.programming.techie.springredditclone.service;


import com.programming.techie.springredditclone.dto.StatementsDto;
import com.programming.techie.springredditclone.repository.StatementsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Component
@Slf4j
public class StatementServiceImpl implements StatementService {
    @Autowired
    private StatementsRepository statementRepository;
    @Autowired
    private HandleUtilityService handleUtilityService;
    @Autowired
    private UserService userService;


    /**
     * Handle Statements by retriving resultSet from DB and convert it to StatementsDTo
     */
    @Override
    public List<StatementsDto> getStatments(String accountId, String fromDate, String toDate, String fromAmount, String toAmount) {
        try {
            boolean isDateRaneFilter = false;
            boolean isAmountRangeFilter = false;
//            LocalDate startDate = null;
//            LocalDate endDate = null;
//            BigDecimal startAmount = null;
//            BigDecimal endAmount = null;

            if (null != fromAmount && null != toAmount) {
                isAmountRangeFilter = true;
//                startAmount = new BigDecimal(fromAmount);
//                endAmount = new BigDecimal(toAmount);
            }
            if (null != fromDate && null != toDate) {
                isDateRaneFilter = true;
//                startDate = handleUtilityService.convertStrToDate(fromDate);
//                endDate = handleUtilityService.convertStrToDate(toDate);
            }

            List<StatementsDto> StatementsDtos = handleUtilityService.mapListToDTO(statementRepository.getAllByFilterArgs());

            LocalDate recentDate = StatementsDtos.stream().map(dat -> dat.getDatefield()).max(LocalDate::compareTo).get();
            LocalDate earlierDate = recentDate.minusMonths(3);

            return StatementsDtos.parallelStream()
                    .filter(isDateRaneFilter ?
                            StatementsDto ->
                                    (StatementsDto.getDatefield().isAfter(handleUtilityService.convertStrToDate(fromDate)) && StatementsDto.getDatefield().isBefore(handleUtilityService.convertStrToDate(toDate))) : dateFilter -> true)
                    .filter(isAmountRangeFilter ?
                            StatementsDto ->
                                    (StatementsDto.getAmount().compareTo(new BigDecimal(fromAmount)) > 0 && StatementsDto.getAmount().compareTo(new BigDecimal(toAmount)) < 0) : amountFilter -> true)
                    .filter((!isDateRaneFilter && !isAmountRangeFilter) ?
                            StatementsDto ->
                                    (StatementsDto.getDatefield().isAfter(earlierDate) && StatementsDto.getDatefield().isBefore(recentDate)) : dateFilter -> true)
                    .collect(Collectors.toList());

        } catch (
                Exception ex) {
            log.error("---- StatementServiceImpl.getStatments error->", ex);
        }
        return null;
    }

}