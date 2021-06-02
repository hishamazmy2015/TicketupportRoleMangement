package com.programming.techie.springredditclone.repository;

import com.programming.techie.springredditclone.model.Statement;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementsRepository extends PagingAndSortingRepository<Statement, Long> {


    //    &fromDate="19.01.2017"&toDate="09.08.2020"
    @Query("select * FROM Statement   " +
            "WHERE  " +
            " account_id = ( CASE WHEN :accountId IS NULL THEN  account_id ELSE :accountId END  ) " +
            "AND datefield >= ( CASE WHEN :fromDate IS NULL THEN  datefield ELSE :fromDate END  )  " +
            "AND datefield  <= ( CASE WHEN :toDate IS NULL THEN  datefield ELSE :toDate END  )    " +
            "AND amount  >= ( CASE WHEN :fromAmount IS NULL THEN  amount ELSE :fromAmount END  )    " +
            "AND amount  <= ( CASE WHEN :toAmount IS NULL THEN  amount ELSE :toAmount END  )    " +
            "")
    List<Statement> getAllByFilterArgs(String accountId, String fromDate, String toDate, String fromAmount, String toAmount);


    @Query("select * FROM Statement   " +
            "  ")
    List<Statement> getAllByFilterArgs();

//    @Query(value = "select * FROM Statement   " +
//            "WHERE    format(cast(datefield as date),'dd.mm.yyyy') >= format(cast(:dateValue as date),'dd.mm.yyyy')   " )
//    List<Statement> getStatementsByLastThreeMonths(String dateValue);


    @Query(value = "select * FROM Statement ")
    List<Statement> getStatementsByLastThreeMonths();


//    @Query("select * FROM Statement   " +
//
//            "      WHERE DateValue(datefield) >= " +
//            " #" +
//            ":dateValue" +
//            "# "+
////                    "      WHERE STR_TO_DATE(datefield, '%d-%m-%Y')  >= STR_TO_DATE(:dateValue, '%d-%m-%Y')  " +
//
////            "      WHERE Format(datefield, \"dd.MM.yyyy\") >=  :dateValue " +
////            "    WHERE DATEPART(m, date_created) = DATEPART(m, DATEADD(m, -3, getdate()))  " +
//            "")
//    List<Statement> getStatementsByLastThreeMonths(String dateValue);


//    @Query(" " +
//            "SELECT IF(DATEDIFF(datefield, a.3Months) > 0,'Y','N') isMoreThanThreeMonths\n" +
//            "FROM  " +
//            "(SELECT SYSDATE() today, datefield, DATEDIFF(SYSDATE(), datefield) dtDiff,  DATE_SUB(CURDATE(), INTERVAL 90 DAY) 3Months " +
//            "FROM Statement ) a ")
//    List<Statement> getStatementsByLastThreeMonths(String dateValue);


//
//    @Query("select * FROM statement where account_id =:accountId")
//    List<Statement> getAllByFilterArgs(String accountId, String fromDate, String toDate, String fromAmount, String toAmount);
}

/*


    @Query("select * FROM Statement   " +
            "WHERE  " +
//            "p.accountId=:accountId AND" +
            " account_id = ( CASE WHEN :accountId IS NULL THEN  account_id ELSE :accountId END  ) " +
            "AND datefield >= ( CASE WHEN :fromDate IS NULL THEN  datefield ELSE :fromDate END  )  " +
            "AND datefield  <= ( CASE WHEN :toDate IS NULL THEN  datefield ELSE :toDate END  )    " +
            "AND amount  >= ( CASE WHEN :fromAmount IS NULL THEN  amount ELSE :fromAmount END  )    " +
            "AND amount  <= ( CASE WHEN :toAmount IS NULL THEN  amount ELSE :toAmount END  )    " +

//            "AND datefield  >= :fromDate AND datefield  <= :toDate " +
//            "AND amount  >= :fromAmount AND amount  <= :toAmount" +
//            "AND supplier.supplier_id = ( CASE WHEN :supplier IS NULL THEN  supplier.supplier_id ELSE :supplier END  ) " +
            "")
    List<Statement> getAllByFilterArgs(String accountId, String fromDate, String toDate, String fromAmount, String toAmount);




 */