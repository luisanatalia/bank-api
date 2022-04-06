package com.livingit.test.bank_api.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerStatementControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	private String requestOk = "[{\"transactionReference\": \"123456\", \"accountNumber\": \"5314780\", \"startBalance\": 10.0, \"mutation\": 5.0, \"description\": \"test\", \"endBalance\": 15.0}]";
	private String requestIBAN = "[{\"transactionReference\": \"123457\", \"accountNumber\": \"5114780\", \"startBalance\": 10.0, \"mutation\": 5.0, \"description\": \"test\", \"endBalance\": 15.0}]";
	private String requestConflict = "[{\"transactionReference\": \"123458\", \"accountNumber\": \"5314780\", \"startBalance\": 11.0, \"mutation\": 5.0, \"description\": \"test\", \"endBalance\": 15.0}]";
	private String requestBadJson = "[{\"transactionReference\": \"123456\", \"accountNumber\" \"5314780\", \"startBalance\": 10.0, \"mutation\": 5.0, \"description\": \"test\", \"endBalance\": 15.0}]";
	
    @Test
    public void testOkResponse() throws Exception {
    	
    	int result = this.mockMvc.perform(MockMvcRequestBuilders.post("/statement")
                				.accept(MediaType.APPLICATION_JSON)
                				.contentType(MediaType.APPLICATION_JSON)
    							.content(requestOk))
		    			.andReturn()
		    			.getResponse()
		    			.getStatus();
    	assertEquals(result, HttpStatus.ACCEPTED.value());
    }
    
    @Test
    public void testInvalidIban() throws Exception {
    	
    	int result = this.mockMvc.perform(MockMvcRequestBuilders.post("/statement")
                				.accept(MediaType.APPLICATION_JSON)
                				.contentType(MediaType.APPLICATION_JSON)
    							.content(requestIBAN))
		    			.andReturn()
		    			.getResponse()
		    			.getStatus();
    	assertEquals(result, HttpStatus.PRECONDITION_FAILED.value());
    }
    
    @Test
    public void testConflict() throws Exception {
    	
    	int result = this.mockMvc.perform(MockMvcRequestBuilders.post("/statement")
                				.accept(MediaType.APPLICATION_JSON)
                				.contentType(MediaType.APPLICATION_JSON)
    							.content(requestConflict))
		    			.andReturn()
		    			.getResponse()
		    			.getStatus();
    	assertEquals(result, HttpStatus.CONFLICT.value());
    }
    
    @Test
    public void testBadJson() throws Exception {
    	
    	int result = this.mockMvc.perform(MockMvcRequestBuilders.post("/statement")
                				.accept(MediaType.APPLICATION_JSON)
                				.contentType(MediaType.APPLICATION_JSON)
    							.content(requestBadJson))
		    			.andReturn()
		    			.getResponse()
		    			.getStatus();
    	assertEquals(result, HttpStatus.BAD_REQUEST.value());
    }
}

