package com.els.test;

import com.els.test.config.WebConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests for SalaryResource
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@SpringBootTest(classes = TestApplication.class)
public class SalaryResourceTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void test() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "", "application/json", "[{\"firstName\": \"Scarlet\",\"lastName\": \"Blundell\",\"email\": \"scarlet.blundell@gamil.com\",\"phoneNumber\": \"07 00 00 00 01\", \"salary\": 1000}, {\"firstName\": \"Alphonso\", \"lastName\": \"Hurlbert\", \"email\": \"alphonso.hurlbert@gamil.com\", \"phoneNumber\": \"07 00 00 00 02\", \"salary\": 1000}, {\"firstName\": \"Alphonso\", \"lastName\": \"Hurlbert\", \"email\": \"alphonso.hurlbert@gamil.com\", \"phoneNumber\": \"07 00 00 00 02\", \"salary\": 1000}]\n".getBytes());

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/distinct")
                .file(file)
                .param("criteria", "FIRST_NAME"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }
}
