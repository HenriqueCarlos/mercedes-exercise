package backendApp;

import java.util.HashMap;
import java.util.List;

public class Vehicle {

    private String id;
    private String model;
    private String fuel;
    private String transmission;
    private HashMap<String,List<String>> availability;

    public Vehicle(String id, String model, String fuel, String transmission, HashMap<String, List<String>> availability) {
        this.id = id;
        this.model = model;
        this.fuel = fuel;
        this.transmission = transmission;
        this.availability = availability;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public HashMap<String, List<String>> getAvailability() {
        return availability;
    }

    public void setAvailability(HashMap<String, List<String>> availability) {
        this.availability = availability;
    }
}
