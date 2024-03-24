package com.kg.alatoo.midtermSpring.dtoValidation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class DtoValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testInvalidUserDTO() throws Exception {
        String invalidUserJson = "{\"email\":\"invalidemail.com\",\"name\":\"John Doe\",\"username\":\"\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidUserJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    public void testInvalidOrderDTO() throws Exception {
        String invalidOrderJson = "{\"description\":\"Event Description\",\"userId\":null}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidOrderJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    public void testInvalidProductDTO() throws Exception {
        String invalidProductJson = "{\"name\":\"Product Name\",\"price\":-10.0,\"orderId\":null}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidProductJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
