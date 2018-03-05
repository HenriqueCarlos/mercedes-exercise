package backendApp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class Dealer {

    private String id;
    private String name;
    private Double latitude;
    private Double longitude;
    private List<Vehicle> vehicles;
    private List<String> closed;

    public Dealer(String id, String name, Double latitude, Double longitude, List<Vehicle> vehicles, List<String> closed) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.vehicles = vehicles;
        this.closed = closed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<String> getClosed() {
        return closed;
    }

    public void setClosed(List<String> closed) {
        this.closed = closed;
    }

    public Double getDistance(Double latitude, Double longitude){
        return Math.hypot(latitude-this.latitude, longitude-this.longitude);

    }

    public boolean hasVehicle(String model, String transmission, String fuel){
        for (Vehicle vehicle : this.vehicles){
            if(vehicle.compareAtributes(model,transmission,fuel)){
                return true;
            }
        }
        return false;
    }

    public JSONObject toJSON(){
        JSONObject returnJSON = new JSONObject();
        returnJSON.put("id", this.id);
        returnJSON.put("name", this.name);
        returnJSON.put("latitude", this.latitude);
        returnJSON.put("longitude", this.longitude);
        JSONArray closedArray = new JSONArray();
        for( String day : this.closed){
            closedArray.add(day);
        }
        returnJSON.put("closed", closedArray);
        JSONArray vehiclesArray = new JSONArray();
        for( Vehicle vehicle : this.vehicles){
            vehiclesArray.add(vehicle.toJSON());
        }
        returnJSON.put("vehicles", vehiclesArray);

        return returnJSON;
    }
}
