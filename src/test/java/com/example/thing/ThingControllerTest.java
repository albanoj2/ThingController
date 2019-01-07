package com.example.thing;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {TestConfiguration.class})
public class ThingControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@Test
	public void noTrailingSlashMapsCorrectly() throws Exception {
		mockMvc.perform(get("/thing"))
			.andExpect(status().isOk())
			.andExpect(content().string(is(equalTo("bar"))));
	}
	
	@Test
	public void noTrailingSlashWithQueryMapsCorrectly() throws Exception {
		mockMvc.perform(get("/thing?findByComponent=test"))
			.andExpect(status().isOk())
			.andExpect(content().string(is(equalTo("bar -> test"))));
	}
	
	@Test
	public void withTrailingSlashMapsCorrectly() throws Exception {
		mockMvc.perform(get("/thing/"))
			.andExpect(status().isOk())
			.andExpect(content().string(is(equalTo("bar"))));
	}
	
	@Test
	public void withTrailingSlashWithQueryMapsCorrectly() throws Exception {
		mockMvc.perform(get("/thing/?findByComponent=test"))
			.andExpect(status().isOk())
			.andExpect(content().string(is(equalTo("bar -> test"))));
	}
	
	@Test
	public void withPathVariableMapsCorrectly() throws Exception {
		mockMvc.perform(get("/thing/test"))
			.andExpect(status().isOk())
			.andExpect(content().string(is(equalTo("foo"))));
	}
}
