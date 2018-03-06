package backendApp;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;



@RestController
public class AppController {
    AppManager appManager = AppManager.getInstance();

    @GetMapping("/listByModel")
    public JSONObject listByModel(){
        return appManager.listByModel();
    }

    @GetMapping("/listByFuel")
    public JSONObject listByFuel(){
        return appManager.listByFuel();
    }

    @GetMapping("/listByTransmission")
    public JSONObject listByTransmission(){
        return appManager.listByTransmission();
    }

    @GetMapping("/listByDealer")
    public JSONObject listByDealer(){
        return appManager.listByDealer();
    }

    @GetMapping("/findClosestDealer")
    public JSONObject findClosestDealer(@RequestParam Double latitude, @RequestParam Double longitude, @RequestParam String model,
                                        @RequestParam String transmission, @RequestParam String fuel){
        return appManager.findClosestDealer(latitude, longitude, model, fuel, transmission);

    }
    @GetMapping("/createBooking")
    public String createBooking(@RequestParam String vehicleId, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String pickupDate){
        return appManager.createBooking(vehicleId,firstName,lastName,pickupDate);
    }

    @GetMapping("/cancelBooking")
    public String cancelBooking(@RequestParam String id, @RequestParam String reason){
        return appManager.cancelBooking(id,reason);
    }

}
