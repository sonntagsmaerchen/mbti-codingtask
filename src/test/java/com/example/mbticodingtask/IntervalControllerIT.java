package com.example.mbticodingtask;

import com.example.mbticodingtask.api.errorhandling.Fault;
import com.example.mbticodingtask.api.model.IntervalDTO;
import com.example.mbticodingtask.api.model.IntervalRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntervalControllerIT {

    private static final String PATH_TO_TESTRESOURCES = "src/test/resources/";
    private static final String INTERVAL_API_PATH = "/interval-merge";

    @Autowired
    MockMvc mockMvc;

    @Test
    void postIntervalMerge_correctMerge200() throws Exception {
        final MvcResult result = mockMvc.perform(post(INTERVAL_API_PATH)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(new String(Files.readAllBytes(Paths.get(PATH_TO_TESTRESOURCES + "input1.json")))))
                                        .andExpect(status().isOk())
                                        .andReturn();

        final ObjectMapper mapper = new ObjectMapper();
        final IntervalRequest resultInterval =
                mapper.readValue(result.getResponse().getContentAsString(), IntervalRequest.class);

        assertThat(resultInterval).usingRecursiveComparison()
                                  .isEqualTo(createExpectedResult());
    }

    @Test
    void postIntervalMerge_emptyRequest() throws Exception {
        final MvcResult result = mockMvc.perform(post(INTERVAL_API_PATH)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content("{}"))
                                        .andExpect(status().isBadRequest())
                                        .andReturn();

        final ObjectMapper mapper = new ObjectMapper();
        final Fault resultFault = mapper.readValue(result.getResponse().getContentAsString(), Fault.class);

        assertThat(resultFault).usingRecursiveComparison()
                               .isEqualTo(createExpectedFaultEmptyRequest());
    }

    @Test
    void postIntervalMerge_invalidInterval() throws Exception {
        final MvcResult result = mockMvc.perform(post(INTERVAL_API_PATH)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(new String(Files.readAllBytes(Paths.get(PATH_TO_TESTRESOURCES + "inputInvalid.json")))))
                                        .andExpect(status().isBadRequest())
                                        .andReturn();

        final ObjectMapper mapper = new ObjectMapper();
        final Fault resultFault = mapper.readValue(result.getResponse().getContentAsString(), Fault.class);

        assertThat(resultFault).usingRecursiveComparison()
                               .isEqualTo(createExpectedFaultInvalidInterval());
    }

    private IntervalRequest createExpectedResult() {
        return new IntervalRequest(List.of(new IntervalDTO(2, 23), new IntervalDTO(25, 30)));
    }

    private Fault createExpectedFaultEmptyRequest() {
        return new Fault("Interval List must not be empty; ", Collections.singletonList("intervalList"));
    }

    private Fault createExpectedFaultInvalidInterval() {
        return new Fault("Invalid Interval Range; ", Collections.singletonList("intervalList[1]"));
    }

}
