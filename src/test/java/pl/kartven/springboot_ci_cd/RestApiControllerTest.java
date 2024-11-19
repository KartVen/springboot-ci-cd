package pl.kartven.springboot_ci_cd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Testcontainers
@AutoConfigureMockMvc
class RestApiControllerTest {

    @Container
    private static final PostgreSQLContainer<?> dataSource;

    @Autowired
    private MockMvc mockMvc;

    static {
        dataSource = new PostgreSQLContainer<>("postgres:14.1-alpine");
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", dataSource::getJdbcUrl);
        registry.add("spring.datasource.username", dataSource::getUsername);
        registry.add("spring.datasource.password", dataSource::getPassword);
    }

    @Test
    void shouldGet() throws Exception {
        mockMvc.perform(get("/get"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldPostSuccess() throws Exception {
        mockMvc.perform(post("/post"))
                .andExpect(status().isCreated());
    }
}