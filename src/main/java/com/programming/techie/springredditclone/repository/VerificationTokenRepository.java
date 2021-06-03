package com.programming.techie.springredditclone.repository;
//

import com.programming.techie.springredditclone.model.Token;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends CrudRepository<Token, Long> {
//    Optional<VerificationToken> findByToken(String token);

    @Query("DELETE FROM TOKEN WHERE tokenvalue= :token ")
    void deleteByToken(String token);


//    @Query("SELECT * FROM TOKEN WHERE username=:username")
//    Optional<List<Token>> findByUser(String username);


    @Query("SELECT * FROM TOKEN WHERE username=:username")
    Optional<TokenDto> findByUser(@Param("username") String username);

    //    @Query("INSERT INTO LOG ( " +
    //            "            EMPLOYEECODE, STATUSID, LOCATIONID, TIME, DURATION, " +
    //            "            SHIFTID, LATECOMING, EARLYGOING, LOGDATE, STATIONID " +
    //            "            ) " +
    //            "    VALUES ( " +
    //            "    1, 1, 0, '4/21/2009 2:25:53 PM', 0, " +
    //            "            8, 0, 1, '1/1/2009', 1 " +
    //            "    )")
    //    saveVerification(String date )


}
