package com.programming.techie.springredditclone;
import com.programming.techie.springredditclone.dto.StatementsDto;
import com.programming.techie.springredditclone.model.Statement;
import com.programming.techie.springredditclone.repository.StatementsRepository;
import com.programming.techie.springredditclone.service.StatementService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doReturn;


@SpringBootTest
public class StatementServiceTest {
    /**
     * Autowire in the service we want to test
     */
    @Autowired
    private StatementService service;

    /**
     * Create a mock implementation of the WidgetRepository
     */
    @MockBean
    private StatementsRepository repository;

    List<Statement> statements;

//    @Test
////    @DisplayName("findAll state ments Success")
//    void testFindAlls() {
//        // Setup our mock repository
//        Statement statement1 = new Statement(1L, 1L, "15.11.2020", "535.197875027054");
//        Statement statement2 = new Statement(2L, 2L, "25.03.2020", "292.451273402434");
//        Statement statement3 = new Statement(3L, 3L, "16.07.2020", "320.113318991709");
//        Statement statement4 = new Statement(4L, 4L, "05.11.2020", "546.183713080865");
//        Statement statement5 = new Statement(5L, 5L, "18.12.2012", "160.447345398501");
//        statements = Arrays.asList(statement1, statement2, statement3, statement4, statement5);
//
//        doReturn(Arrays.asList(statement1, statement2, statement3, statement4, statement5)).when(repository).getAllByFilterArgs();
//
//
//        // Execute the service call
//        List<StatementsDto> widgets = service.getStatments(null, null, null, null, null);
//
//        // Assert the response
//        Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
//    }
}
