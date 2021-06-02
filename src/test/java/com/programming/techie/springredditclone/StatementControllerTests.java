package com.programming.techie.springredditclone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programming.techie.springredditclone.dto.StatementsDto;
import com.programming.techie.springredditclone.model.Statement;
//import com.programming.techie.springredditclone.service.StatementsService;
import com.programming.techie.springredditclone.service.StatementService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatementControllerTests {

    /**
     * Autowire in the service we want to test
     */
    @Autowired
    private StatementService service;
    @Autowired
    private MockMvc mockMvc;
//
//    @Test
//    @DisplayName("GET /Statements with parametres success")
//    public void testGetStatementsSuccess() throws Exception {
//        // Setup our mock repository
//        Statement statement1 = new Statement(1L, 1L, "15.11.2020", "535.197875027054");
//        Statement statement2 = new Statement(2L, 2L, "25.03.2020", "292.451273402434");
//        Statement statement3 = new Statement(3L, 3L, "16.07.2020", "320.113318991709");
//        Statement statement4 = new Statement(4L, 4L, "05.11.2020", "546.183713080865");
//        Statement statement5 = new Statement(5L, 5L, "18.12.2012", "160.447345398501");
//        Optional<StatementsDto> anyStatements = doReturn(Arrays.asList(statement1, statement2, statement3, statement4, statement5))
//                .when(service)
//                .getStatmentsByArgs(String.valueOf(statement1.getAccountId())
//                        , statement5.getDatefield()
//                        , statement1.getDatefield()
//                        , statement5.getAmount()
//                        , statement4.getAmount()
//                ).stream().findAny();
//
////        doReturn(Lists.newArrayList(widget1, widget2)).when(service).findAll();
//
//        // Execute the GET request
//        mockMvc.perform(get("/api/bank/statements"))
//                // Validate the response code and content type
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                // Validate headers
//                .andExpect(header().string(HttpHeaders.LOCATION, "/rest/widgets"))
//
//                // Validate the returned fields
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(1)))
//                .andExpect(jsonPath("$[0].name", is("Widget Name")))
//                .andExpect(jsonPath("$[0].description", is("Description")))
//                .andExpect(jsonPath("$[0].version", is(1)))
//                .andExpect(jsonPath("$[1].id", is(2)))
//                .andExpect(jsonPath("$[1].name", is("Widget 2 Name")))
//                .andExpect(jsonPath("$[1].description", is("Description 2")))
//                .andExpect(jsonPath("$[1].version", is(4)));
//    }


//    @Test
//    @DisplayName("GET /rest/widget/1 - Not Found")
//    public void testGetWidgetByIdNotFound() throws Exception {
//        // Setup our mocked service
//        doReturn(Optional.empty()).when(service).getStatmentsByThreeMoths();
//
//        // Execute the GET request
//        mockMvc.perform(get("/api/bank/statements/{id}", 1L))
//                // Validate the response code
//                .andExpect(status().isNotFound());
//    }
//
//    static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    public void contextLoads() {
//    }


}
