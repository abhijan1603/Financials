package com.bites.inventory.Financials.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootTest
@AutoConfigureMockMvc
public class WebConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void corsHeadersShouldBeAppliedToApiEndpoints() throws Exception {
        // Define the origin we are *sending* in our test request
        final String expectedOrigin = "http://fakefrontend.com";

        mockMvc.perform(MockMvcRequestBuilders.options("/api/some-resource")
                        .header("Origin", expectedOrigin) // Use the defined origin
                        .header("Access-Control-Request-Method", "POST"))

                // ... other checks ...
                .andExpect(MockMvcResultMatchers.status().isOk())

                // EXPECT the returned header to match the origin we SENT
                .andExpect(MockMvcResultMatchers.header().string("Access-Control-Allow-Origin", expectedOrigin))

                // ... rest of the checks ...
                .andExpect(MockMvcResultMatchers.header().string("Access-Control-Allow-Credentials", "true"))

                .andExpect(MockMvcResultMatchers.header().string("Access-Control-Allow-Methods",
                        org.hamcrest.Matchers.containsString("POST")));
    }

    @Test
    public void corsConfigurerBeanIsCreated() {
        WebConfig webConfig = new WebConfig();
        WebMvcConfigurer configurer = webConfig.corsConfigurer();

        // Ensure the method returns a valid configurer instance
        Assertions.assertNotNull(configurer, "WebMvcConfigurer bean should not be null");
    }
}




