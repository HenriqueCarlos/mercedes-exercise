package backendApp;

import java.util.Comparator;
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

    /*public boolean equalsModel(Vehicle vehicle) {
        return vehicle.getFuel().equals(this.fuel) && vehicle.getTransmission().equals(this.transmission)
                && vehicle.getModel().equals(this.model);

    }*/

    //this method is to compare vehicles in terms of model, fuel and transmission
    @Override
    public boolean equals(Object obj) {
        Vehicle v1 = (Vehicle) obj;
        return v1.getModel().equals(this.model) && v1.getTransmission().equals(this.transmission) && v1.getFuel().equals(this.fuel);
    }

    public static Comparator<Vehicle> comparatorByModel = new Comparator<Vehicle>() {

        public int compare(Vehicle v1, Vehicle v2) {
            String v1Model = v1.getModel().toUpperCase();
            String v2Model = v2.getModel().toUpperCase();

            //ascending order
            return v1Model.compareTo(v2Model);
        }
    };


}
