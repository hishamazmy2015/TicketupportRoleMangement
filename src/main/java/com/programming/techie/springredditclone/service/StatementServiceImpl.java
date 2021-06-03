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
import java.util.Optional;
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


            if (null != fromAmount && null != toAmount) {
                isAmountRangeFilter = true;
            }
            if (null != fromDate && null != toDate) {
                isDateRaneFilter = true;
            }

            List<StatementsDto> StatementsDtos = handleUtilityService.mapListToDTO(statementRepository.getAllByFilterArgs());
            Optional<LocalDate> max = StatementsDtos.stream().map(dat -> dat.getDatefield()).max(LocalDate::compareTo);
            LocalDate recentDate = null;
            LocalDate earlierDate = null;
            if (max.isPresent()) {
                recentDate = max.get();
                earlierDate = max.get().minusMonths(3);
            }

            LocalDate finalEarlierDate = earlierDate;
            LocalDate finalRecentDate = recentDate;
            return StatementsDtos.parallelStream()
                    .filter(isDateRaneFilter ?
                            StatementsDto ->
                                    (StatementsDto.getDatefield().isAfter(handleUtilityService.convertStrToDate(fromDate)) && StatementsDto.getDatefield().isBefore(handleUtilityService.convertStrToDate(toDate))) : dateFilter -> true)
                    .filter(isAmountRangeFilter ?
                            StatementsDto ->
                                    (StatementsDto.getAmount().compareTo(new BigDecimal(fromAmount)) > 0 && StatementsDto.getAmount().compareTo(new BigDecimal(toAmount)) < 0) : amountFilter -> true)
                    .filter((!isDateRaneFilter && !isAmountRangeFilter) ?
                            StatementsDto ->
                                    (StatementsDto.getDatefield().isAfter(finalEarlierDate) && StatementsDto.getDatefield().isBefore(finalRecentDate)) : dateFilter -> true)
                    .collect(Collectors.toList());

        } catch (
                Exception ex) {
            log.error("---- StatementServiceImpl.getStatments error->", ex);
        }
        return null;
    }

}