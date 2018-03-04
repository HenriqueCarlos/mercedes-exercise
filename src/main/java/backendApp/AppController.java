package backendApp;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
