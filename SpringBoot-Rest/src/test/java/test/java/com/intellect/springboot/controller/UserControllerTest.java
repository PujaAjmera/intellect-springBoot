package test.java.com.intellect.springboot.controller;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Date;

import javax.xml.ws.Response;

import jdk.nashorn.internal.ir.annotations.Ignore;

import main.java.com.intellect.springboot.controller.UserController;
import main.java.com.intellect.springboot.model.User;
import main.java.com.intellect.springboot.service.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	User mockUser = new User(11111, "LastName", "3", true, "abc@gmail.com", new Date("16-FEB-2017"), "ACB");


	@Ignore
	public void retrieveUser() throws Exception {

		Mockito.when(
				userService.retrieveUser(Mockito.anyString())).thenReturn(mockUser);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/users/3").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id:3,fname:ABC,lname:LastName,email:abc@gmail.com,pinCode:pinCode,birthDate: [value:{"
             + "date:16-FEB-2017}],isActive=true}";
		
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
		assertEquals(HttpStatus.FOUND.value(), result.getResponse().getStatus());
	}
	
	@Ignore
	public void retrieveUser_test() throws Exception {
		
		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/users/1")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		//assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/users/1",
				response.getHeader(HttpHeaders.LOCATION));

	}

}
