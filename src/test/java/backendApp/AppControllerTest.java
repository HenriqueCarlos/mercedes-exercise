package backendApp;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AppController.class, secure = false)
public class AppControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppManager appManager;

    @Test
    public void listByModel() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/listByModel");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String content = result.getResponse().getContentAsString();
        org.json.JSONObject jsonResult = new org.json.JSONObject(content);
        int expectedSize = 3;
        Assert.assertEquals(jsonResult.length(),expectedSize);
    }

    @Test
    public void listByFuel() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/listByFuel");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String content = result.getResponse().getContentAsString();
        org.json.JSONObject jsonResult = new org.json.JSONObject(content);
        int expectedSize = 2;
        Assert.assertEquals(jsonResult.length(),expectedSize);

    }

    @Test
    public void listByTransmission() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/listByTransmission");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String content = result.getResponse().getContentAsString();
        org.json.JSONObject jsonResult = new org.json.JSONObject(content);
        int expectedSize = 2;
        Assert.assertEquals(jsonResult.length(),expectedSize);
    }

    @Test
    public void listByDealer() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/listByDealer");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String content = result.getResponse().getContentAsString();
        org.json.JSONObject jsonResult = new org.json.JSONObject(content);
        int expectedSize = 3;
        Assert.assertEquals(jsonResult.length(),expectedSize);

    }

    @Test
    public void findClosestDealer() {
    }

    @Test
    public void createBooking() {
    }

    @Test
    public void cancelBooking() {
    }
}