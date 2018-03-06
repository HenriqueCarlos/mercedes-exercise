package backendApp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringRunner.class)
@WebMvcTest(value = AppController.class, secure = false)
public class AppControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void listByModel() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/listByModel");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String content = result.getResponse().getContentAsString();
        org.json.JSONObject jsonResult = new org.json.JSONObject(content);
        int expectedSize = 3; //number of models
        Assert.assertEquals(result.getResponse().getStatus(), 200); //200 is the Ok status
        Assert.assertEquals(jsonResult.length(), expectedSize);
    }

    @Test
    public void listByFuel() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/listByFuel");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String content = result.getResponse().getContentAsString();
        org.json.JSONObject jsonResult = new org.json.JSONObject(content);
        int expectedSize = 2; //number of fuel types
        Assert.assertEquals(result.getResponse().getStatus(), 200); //200 is the Ok status
        Assert.assertEquals(jsonResult.length(),expectedSize);

    }

    @Test
    public void listByTransmission() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/listByTransmission");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String content = result.getResponse().getContentAsString();
        org.json.JSONObject jsonResult = new org.json.JSONObject(content);
        int expectedSize = 2; //number of transmission types
        Assert.assertEquals(result.getResponse().getStatus(), 200); //200 is the Ok status
        Assert.assertEquals(jsonResult.length(),expectedSize);
    }

    @Test
    public void listByDealer() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/listByDealer");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String content = result.getResponse().getContentAsString();
        org.json.JSONObject jsonResult = new org.json.JSONObject(content);
        int expectedSize = 3; // number of dealers
        Assert.assertEquals(result.getResponse().getStatus(), 200); //200 is the Ok status
        Assert.assertEquals(jsonResult.length(),expectedSize);

    }

    @Test
    public void findClosestDealer() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/findClosestDealer?latitude=38.736574&longitude=-9.139184" +
                "&model=AMG&fuel=ELECTRIC&transmission=AUTO");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String content = result.getResponse().getContentAsString();
        org.json.JSONObject jsonResult = new org.json.JSONObject(content);
        Assert.assertEquals(result.getResponse().getStatus(), 200); //200 is the Ok status
        Assert.assertEquals(jsonResult.get("name"), "MB Lisboa");
    }

    @Test
    public void createBooking() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/createBooking?vehicleId=768a73af-4336-41c8-b1bd-76bd700378ce" +
                "&firstName=Test&lastName=Test&pickupDate=2018-03-05T10:30:00");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String content = result.getResponse().getContentAsString();
        Assert.assertEquals(result.getResponse().getStatus(), 200); //200 is the Ok status
        Assert.assertEquals(content, "Booking created successfully!");
    }

    @Test
    public void cancelBooking() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cancelBooking?id=ca7927a9-1262-4085-aad0-d06e105acd3a" +
                "&reason=testText");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertEquals(result.getResponse().getStatus(), 200); //200 is the Ok status
        Assert.assertEquals(content, "Booking cancelled successfully!");
    }
}