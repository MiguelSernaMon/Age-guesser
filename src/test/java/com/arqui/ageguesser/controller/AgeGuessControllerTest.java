package com.arqui.ageguesser.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class AgeGuessControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AgeGuessController controller;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAge() throws Exception {
        // Configurar mock
        AgeDTO mockResponse = new AgeDTO(
                12345,
                "juan",
                62);

        Mockito.when(restTemplate.getForObject(Mockito.contains("?name=juan"), eq(AgeDTO.class)))
                .thenReturn(mockResponse);

        // Ejecutar y verificar
        mockMvc.perform(get("/age/juan"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("juan"))
                .andExpect(jsonPath("$.age").value(62))
                .andExpect(jsonPath("$.count").value(12345));
    }
}