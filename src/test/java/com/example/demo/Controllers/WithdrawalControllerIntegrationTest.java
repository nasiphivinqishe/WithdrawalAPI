package com.example.demo.Controllers;

import com.example.demo.Controllers.WithdrawalController;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(WithdrawalController.class)
@ComponentScan(basePackages = "com.example.demo")
public class WithdrawalControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testCreateWithdrawalEndpoint() throws Exception {
        RequestBuilder request = post("/api/withdrawals/do-withdrawal").contentType(MediaType.APPLICATION_JSON).content("{\"withdrawalAmount\":  \"19\", \"InvestmentId\":  \"3\", \"dateAndTime\":  \"2023-10-24T10:30:00\"}");
        MvcResult result = mvc.perform(request).andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }
}
