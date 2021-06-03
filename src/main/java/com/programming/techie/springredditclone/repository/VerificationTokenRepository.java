package com.programming.techie.springredditclone.repository;
//

import com.programming.techie.springredditclone.model.Token;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends CrudRepository<Token, Long> {
    /**
     * Spring Data JPA also supports derived delete queries that let you avoid having to declare the JPQL query explicitly
     */

    @Modifying
    @Query(" delete from TOKEN  where tokenvalue=:token")
    void deleteByToken(@Param("token") String token);


    @Query("SELECT * FROM TOKEN WHERE username=:username")
    Optional<TokenDto> findByUser(@Param("username") String username);


}
