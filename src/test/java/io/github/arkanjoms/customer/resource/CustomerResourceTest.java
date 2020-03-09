package io.github.arkanjoms.customer.resource;

import io.github.arkanjoms.customer.model.Customer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.CustomPageImpl;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class,
})
@DatabaseSetup(connection = "customerDatasource", value = {"/data/customers.xml"})
@DbUnitConfiguration(databaseConnection = {"customerDatasource", "storeDatasource"})
class CustomerResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findAll() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/customers?page=0&size=10")).andReturn();
        assertEquals(200, result.getResponse().getStatus());

        CustomPageImpl<Customer> customers = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<CustomPageImpl<Customer>>() {
        });
        assertFalse(customers.isEmpty());
        assertEquals(1, customers.getTotalElements());
        assertEquals(1, customers.getNumberOfElements());
        assertEquals(1, customers.getTotalPages());
    }
}
