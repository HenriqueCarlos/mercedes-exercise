package backendApp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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

    public JSONObject getAvailabilityJSON(){
        JSONObject returnJSON = new JSONObject();
        for (Object key : this.availability.keySet()){
            String dayOfWeek = (String) key;
            List<String> hoursList =  availability.get(dayOfWeek);
            JSONArray hoursArray = new JSONArray();
            for( String hour : hoursList){
                hoursArray.add(hour);
            }
            returnJSON.put(dayOfWeek,hoursArray);
        }
        return  returnJSON;

    }

    public JSONObject toJSON(){
        JSONObject vehicleJSON = new JSONObject();
        vehicleJSON.put("id", this.id);
        vehicleJSON.put("model", this.model);
        vehicleJSON.put("fuel", this.fuel);
        vehicleJSON.put("transmission", this.transmission);
        vehicleJSON.put("availability", this.getAvailabilityJSON());
        return vehicleJSON;
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

    public boolean compareAtributes(String model, String transmission, String fuel){
        return model.equals(this.model) && transmission.equals(this.transmission) && fuel.equals(this.fuel);

    }

    public static Comparator<Vehicle> comparatorByModel = new Comparator<Vehicle>() {

        public int compare(Vehicle v1, Vehicle v2) {
            String v1Model = v1.getModel().toUpperCase();
            String v2Model = v2.getModel().toUpperCase();

            //ascending order
            return v1Model.compareTo(v2Model);
        }
    };

    public static Comparator<Vehicle> comparatorByFuel = new Comparator<Vehicle>() {

        public int compare(Vehicle v1, Vehicle v2) {
            String v1Model = v1.getFuel().toUpperCase();
            String v2Model = v2.getFuel().toUpperCase();

            //ascending order
            return v1Model.compareTo(v2Model);
        }
    };

    public static Comparator<Vehicle> comparatorByTransmission = new Comparator<Vehicle>() {

        public int compare(Vehicle v1, Vehicle v2) {
            String v1Model = v1.getTransmission().toUpperCase();
            String v2Model = v2.getTransmission().toUpperCase();

            //ascending order
            return v1Model.compareTo(v2Model);
        }
    };


}
