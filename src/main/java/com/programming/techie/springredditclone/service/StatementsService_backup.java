//package com.programming.techie.springredditclone.service;
//
//
//import com.programming.techie.springredditclone.dto.StatementsDto;
//import com.programming.techie.springredditclone.mapper.StatementsMapper;
//import com.programming.techie.springredditclone.repository.StatementsRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static java.util.stream.Collectors.toList;
//
//@Service
//@AllArgsConstructor
//public class StatementsService_backup {
//
//    //    private final AuthService authService;
//    private StatementsRepository statementsRepository;
//    private StatementsMapper statementsMapper;
//
//    private HandleUtilityService handleUtilityService;
//
//
//    /*
//     *
//     *
//     *
//     * */
////    @Transactional(readOnly = true)
////    public List<StatementsDto> getStatmentsByArgs(List<Map<String, String>> maps) {
////        maps.stream().map((key-> ))
////        switch (params) {
////            case params.get("accountId"). == "d":
////                break;
////
////        }
////        if (params.get("accountId") != null) {
////            return offerRepo.findByQuery("Offer_find_by_title", params.get("title"));
////        }
////
////
////        return
////                statementsRepository.getAllByFilterArgs(accountId, fromDate, toDate, fromAmount, toAmount).stream().
////                        map(statementsMapper::mapDtoToStatementDto).
////                        collect(toList());
////    }
//
//    /**
//     * The search will return three months back.
//     */
//    @Transactional(readOnly = true)
//    public List<StatementsDto> getStatmentsByThreeMoths() {
////        System.out.println("handleUtilityService.LastThreeMonthDate() " + handleUtilityService.LastThreeMonthDate());
////        List<Statement> statementsByLastThreeMonths = statementsRepository.getStatementsByLastThreeMonths();
//        try {
//            LocalDate recentDate = statementRests
//                    .stream()
//                    .map(u -> u.getDatefield()).max(LocalDate::compareTo).get();
//            LocalDate earlierDate = recentDate.minusMonths(3);
//
//            List<StatementsDto> collect = statementsRepository.getStatementsByLastThreeMonths().stream()
//                    .filter(statementRest -> (statementRest.getDatefield().isAfter(earlierDate) && statementRest.getDatefield().isBefore(recentDate)))
//                    map(statementsMapper::mapDtoToStatementDto).
//                    collect(toList());
//
//            return statementRests.stream().filter(statementRest -> (statementRest.getDatefield().isAfter(earlierDate) && statementRest.getDatefield().isBefore(recentDate))).collect(Collectors.toList());
//        } catch (Exception ex) {
//            log.error("---- StatementServiceImpl.lastThreeMonthStatements error->", ex);
//        }
//
//
//        return lastThreeMonthStatementFilter(collect);
//    }
//
//
//    private List<StatementsDto> lastThreeMonthStatementFilter(List<StatementsDto> statementRests) {
//        try {
//            LocalDate recentDate = statementRests
//                    .stream()
//                    .map(u -> u.getDatefield()).max(LocalDate::compareTo).get();
//            LocalDate earlierDate = recentDate.minusMonths(3);
//            return statementRests.stream().filter(statementRest -> (statementRest.getDatefield().isAfter(earlierDate) && statementRest.getDatefield().isBefore(recentDate))).collect(Collectors.toList());
//        } catch (Exception ex) {
//            log.error("---- StatementServiceImpl.lastThreeMonthStatements error->", ex);
//        }
//        return null;
//    }
//
////    private List<StatementRest> lastThreeMonthStatements(List<StatementRest> statementRests) {
////        try {
////            // create a LocalDate object
////            LocalDate date = LocalDate.now();
////
////            // print instance
////            System.out.println("LocalDate before"
////                    + " adding months: " + date);
////
////            // add 5 months
////            LocalDate returnvalue = date.plusMonths(5);
////
////            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
////            LocalDateTime dateTime = LocalDateTime.parse(returnvalue.toString(), formatter);
////
////            // print result
////            System.out.println("LocalDate after "
////                    + " adding months: " + returnvalue);
////
////            LocalDate recentDate = statementRests.stream().map(u -> u.getDatefield()).max(LocalDate::compareTo).get();
////            LocalDate earlierDate = LocalDate.minusMonths(3);
////            return statementRests.stream().filter(statementRest -> (statementRest.getDatefield().isAfter(earlierDate) && statementRest.getDatefield().isBefore(recentDate))).collect(Collectors.toList());
////        } catch (Exception ex) {
////            log.error("---- StatementServiceImpl.lastThreeMonthStatements error->", ex);
////        }
////        return null;
////    }
//
//    /*
//     *
//     *
//     *
//     * */
//}
//
//
////    @Transactional(readOnly = true)
////    public List<StatementsDto> getStatmentsByArgs(String accountId, String fromDate, String toDate, String fromAmount, String toAmount) {
////        return
////                statementsRepository.getAllByFilterArgs(accountId, fromDate, toDate, fromAmount, toAmount).stream().
////                        map(statementsMapper::mapDtoToStatementDto).
////                        collect(toList());
////    }
