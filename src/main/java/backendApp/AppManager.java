package backendApp;

import java.util.List;

public class AppManager {

    private List<Dealer> _dealers;
    private List<Booking> _bookings;
    private List<Vehicle> _vehicles;

    //This is to insure that the class is a singleton
    private static AppManager instance = null;
    public static AppManager getInstance(){
        if(instance == null){
            instance = new AppManager();
        }
        return instance;
    }




}
