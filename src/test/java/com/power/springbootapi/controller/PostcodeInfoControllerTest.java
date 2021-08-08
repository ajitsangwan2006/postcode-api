package com.power.springbootapi.controller;

import com.power.springbootapi.common.Constants;
import com.power.springbootapi.helper.PostcodeInfoTestDataHelper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PostcodeInfoControllerTest {
    @Autowired
    private MockMvc mvc;

    private String postcodeData;

    @BeforeEach
    public void init() throws Exception {
        postcodeData = PostcodeInfoTestDataHelper.convertJsonToString("testData/savePostcodeInfo.json");
        saveInitialData();
    }

    @Test
    public void testPostPostcodeInformation() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/postcodes")
                .accept(MediaType.APPLICATION_JSON)
                .contentType("application/json")
                .content(postcodeData))
                .andExpect(status().is(400))
                .andExpect(content().string(CoreMatchers.containsString(Constants.DATA_EXIST_MSG)));
    }

    @Test
    public void testInvalidInputPostcodeInformation() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/postcodes")
                .accept(MediaType.APPLICATION_JSON)
                .contentType("application/json")
                .content("invalid data"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testInvalidContentTypePostPostcodeInformation() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/postcodes")
                .accept(MediaType.APPLICATION_JSON)
                .contentType("text")
                .content(postcodeData))
                .andExpect(status().is(415));
    }

    @Test
    public void testInvalidPostcode() throws Exception {
        String invalidInput = "[{\"postcode\": \"xxxxx\",\"name\": \"Jack Toto\" }\n" + "]";
        mvc.perform(MockMvcRequestBuilders.post("/postcodes")
                .accept(MediaType.APPLICATION_JSON)
                .contentType("application/json")
                .content(invalidInput))
                .andExpect(status().is(400))
                .andExpect(content().string(CoreMatchers.containsString(Constants.POSTCODE_INVALID_MSG)));
    }

    @Test
    public void testGetPostcodeInformation() throws Exception {
        String[] expectedOutput = new String[]{
                "Shortest",
                "George W. Bush",
                "Abraham Lincoln",
                "George Washington",
                "Joseph Smith, Jr.",
                "Alexander the Great",
                "William Shakespeare",
                "Henry VIII of England",
                "Elizabeth I of England",
                "Wolfgang Amadeus Mozart",
                "1111111111111111111111111111111111111111111"};
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/names/1234/2345")
                .accept(MediaType.APPLICATION_JSON)
                .contentType("application/json"))
                .andExpect(status().isOk());
        for (int i = 0; i < expectedOutput.length; i++) {
            actions.andExpect(jsonPath("$[" + i + "]").value(expectedOutput[i]));
        }

    }

    @Test
    public void testInvalidStartInputGetPostcodeInformation() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/names/xyz/abc")
                .accept(MediaType.APPLICATION_JSON)
                .contentType("application/json"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(CoreMatchers.containsString(Constants.START_VALUE_INVALID_MSG)));
    }

    @Test
    public void testInvalidEndInputGetPostcodeInformation() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/names/1234/abc")
                .accept(MediaType.APPLICATION_JSON)
                .contentType("application/json"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(CoreMatchers.containsString(Constants.END_VALUE_INVALID_MSG)));
    }

    private void saveInitialData() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/postcodes")
                .accept(MediaType.APPLICATION_JSON)
                .contentType("application/json")
                .content(postcodeData));
    }
}