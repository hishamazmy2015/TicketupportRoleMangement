package com.programming.techie.springredditclone;

import com.programming.techie.springredditclone.model.Statement;
import com.programming.techie.springredditclone.repository.StatementsRepository;
//import com.programming.techie.springredditclone.service.StatementsService;
import com.programming.techie.springredditclone.service.StatementService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInfo;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Stubber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringApplicationTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringApplicationTests.class);

    /**
     * Autowire in the service we want to test
     */
    @Autowired
    private StatementService service;

    /**
     * Create a mock implementation of the Statement Repository
     */
    @MockBean
    private StatementsRepository repository;

//    @Test
//    @DisplayName("Test findById Success")
//    void testFindById() {
//        // Setup our mock repository
//        Statement  Statement  = new Statement (1l, "Statement  Name", "Description", 1);
//        doReturn(Optional.of(Statement )).when(repository).findById(1l);
//
//        // Execute the service call
//        Optional<Statement > returnedStatement  = service.getStatmentsByArgs(Statement);
//
//        // Assert the response
//        Assertions.assertTrue(returnedStatement .isPresent(), "Statement  was not found");
//        Assertions.assertSame(returnedStatement .get(), Statement , "The Statement  returned was not the same as the mock");
//    }
//
//    @Test
//    @DisplayName("Test findById Not Found")
//    void testFindByIdNotFound() {
//        // Setup our mock repository
//        doReturn(Optional.empty()).when(repository).findById(1l);
//
//        // Execute the service call
//        Optional<Statement > returnedStatement  = service.findById(1l);
//
//        // Assert the response
//        Assertions.assertFalse(returnedStatement .isPresent(), "Statement  should not be found");
//    }

    @Test
    @DisplayName("Test getAllByFilterArgs")
    public void testFindAll() {
        // Setup our mock repository
        Statement statement1 = new Statement(1L, 1L, "15.11.2020", "535.197875027054");
        Statement statement2 = new Statement(2L, 2L, "25.03.2020", "292.451273402434");
        Statement statement3 = new Statement(3L, 3L, "16.07.2020", "320.113318991709");
        Statement statement4 = new Statement(4L, 4L, "05.11.2020", "546.183713080865");
        Statement statement5 = new Statement(5L, 5L, "18.12.2012", "160.447345398501");
        Stubber stubber = doReturn(Arrays.asList(statement1, statement2, statement3, statement4, statement5));
        List<Statement> allByFilterArgs = doReturn(Arrays.asList(statement1, statement2, statement3, statement4, statement5))
                .when(repository)
                .getAllByFilterArgs(String.valueOf(statement1.getAccountId())
                        , statement5.getDatefield()
                        , statement1.getDatefield()
                        , statement5.getAmount()
                        , statement4.getAmount()
                );
        System.out.println("allByFilterArgs " + allByFilterArgs);

        // Execute the service call
//        List<Statement> Statement1 = service.findAll();

        // Assert the response
        Assertions.assertEquals(2, allByFilterArgs.size(), "findAll should return 2 Statement s");
    }

//    @Test
//    @DisplayName("Test save Statement ")
//    void testSave() {
//        // Setup our mock repository
//        Statement  Statement  = new Statement (1l, "Statement  Name", "Description", 1);
//        doReturn(Statement ).when(repository).save(any());
//
//        // Execute the service call
//        Statement  returnedStatement  = service.save(Statement );
//
//        // Assert the response
//        Assertions.assertNotNull(returnedStatement , "The saved Statement  should not be null");
//        Assertions.assertEquals(2, returnedStatement .getVersion(), "The version should be incremented");
//    }

//    @Test
//    @DisplayName("When the string is given, it should not be empty")
//    void checkStringIfIsNotBlank(TestInfo testInfo) {
//
//        LOGGER.info("Display Name::::::" + testInfo.getDisplayName());
//        LOGGER.info("Test Method:::::::" + testInfo.getTestMethod());
//        String str = "not blank";
//
//        assertTrue(CommonUtil.isNotBlank(str), "true");
//    }


    @Test
    public void contextLoads() {

    }


}
