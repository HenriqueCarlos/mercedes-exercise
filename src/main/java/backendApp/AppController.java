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



}
