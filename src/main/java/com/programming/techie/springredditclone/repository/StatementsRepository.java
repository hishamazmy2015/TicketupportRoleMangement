package com.programming.techie.springredditclone.repository;

import com.programming.techie.springredditclone.model.Statement;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementsRepository extends PagingAndSortingRepository<Statement, Long> {

    //    @Query("select p FROM Statements p " +
//            "WHERE  p.accountId=:accountId " +
//            "AND p.accountId = ( CASE WHEN :supplier IS NULL THEN  supplier.supplier_id ELSE :supplier END  )")
//    List<Statement> getAllByAccountId(String accountId, String fromDate, String toDate, String fromAmount, String toAmount);
//
    @Query("select * FROM Statement   " +
            "WHERE  " +
//            "p.accountId=:accountId AND" +
            " account_id = ( CASE WHEN :accountId IS NULL THEN  account_id ELSE :accountId END  ) " +
            "AND datefield  >= :fromDate AND datefield  <= :toDate " +
            "AND amount  >= :fromAmount AND amount  <= :toAmount" +
//            "AND supplier.supplier_id = ( CASE WHEN :supplier IS NULL THEN  supplier.supplier_id ELSE :supplier END  ) " +
            "")
    List<Statement> getAllByFilterArgs(String accountId, String fromDate, String toDate, String fromAmount, String toAmount);

//
//    @Query("select * FROM statement where account_id =:accountId")
//    List<Statement> getAllByFilterArgs(String accountId, String fromDate, String toDate, String fromAmount, String toAmount);
}