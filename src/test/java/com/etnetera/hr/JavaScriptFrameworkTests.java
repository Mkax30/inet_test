package com.etnetera.hr;

import com.etnetera.hr.controller.JavaScriptFrameworkController;
import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.data.Version;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Class used for Spring Boot/MVC based tests.
 *
 * @author Etnetera
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaScriptFrameworkTests {

    private MockMvc mockMvc;

    @Autowired
    private JavaScriptFrameworkRepository repository;

    private JavaScriptFramework framework;

    private JavaScriptFramework savedFramework;

    @Before
    public void setup() {
        JavaScriptFrameworkController javaScriptFrameworkController = new JavaScriptFrameworkController(repository);
        mockMvc = MockMvcBuilders.standaloneSetup(javaScriptFrameworkController).build();

        framework = new JavaScriptFramework()
                .setName("Test JavaScript Framework")
                .addVersion(new Version()
                        .setVersionNumber("1.1.5")
                        .setDeprecationDate(LocalDate.now()))
                .setHypeLevel(10);

        savedFramework = repository.save(framework);
    }

    @After
    public void destroy() {
        repository.deleteAll();
    }

    @Test
    public void createOperationTest() throws Exception {
        System.out.println(">>>>>>>>>>>>>>> " + asJsonString(framework));
        mockMvc.perform(post("/api/v1/frameworks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(framework)))
                .andExpect(status().isCreated());
    }

    @Test
    public void readOperationTest() throws Exception {
        mockMvc.perform(get("/api/v1/frameworks/{id}", savedFramework.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name", equalTo("Test JavaScript Framework")));
    }

    @Test
    public void updateOperationTest() throws Exception {
        JavaScriptFramework updatedFramework = framework;
        updatedFramework.setName("Updated");
        updatedFramework.setHypeLevel(11);

        Version version = new Version();
        version.setVersionNumber("1.1.6");
        version.setDeprecationDate(LocalDate.now().plusDays(1));

        updatedFramework.getVersions().add(version);

        System.out.println(">>>>>>>>>>>>>>> " + asJsonString(framework));

        mockMvc.perform(put("/api/v1/frameworks/{id}", savedFramework.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedFramework)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Updated")))
                .andExpect(jsonPath("$.hypeLevel", equalTo(11)))
                .andExpect(jsonPath("$.versions[0].versionNumber", equalTo("1.1.6")))
                .andExpect(jsonPath("$.versions[1].versionNumber", equalTo("1.1.5")));
    }

    @Test
    public void deleteOperationTest() throws Exception {
        mockMvc.perform(delete("/api/v1/frameworks/{id}", savedFramework.getId()))
                .andExpect(status().isNoContent());
    }

    private static String asJsonString(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(obj);
    }
}
