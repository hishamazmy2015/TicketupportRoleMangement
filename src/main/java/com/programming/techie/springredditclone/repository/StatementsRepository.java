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
            "WHERE  " +
            " account_id = ( CASE WHEN :accountId IS NULL THEN  account_id ELSE :accountId END  ) " +
            "AND datefield >= ( CASE WHEN :fromDate IS NULL THEN  datefield ELSE :fromDate END  )  " +
            "AND datefield  <= ( CASE WHEN :toDate IS NULL THEN  datefield ELSE :toDate END  )    " +
            "AND amount  >= ( CASE WHEN :fromAmount IS NULL THEN  amount ELSE :fromAmount END  )    " +
            "AND amount  <= ( CASE WHEN :toAmount IS NULL THEN  amount ELSE :toAmount END  )    " +
            "")
    List<Statement> getStatementsByLastThreeMonths(String accountId, String fromDate, String toDate, String fromAmount, String toAmount);


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