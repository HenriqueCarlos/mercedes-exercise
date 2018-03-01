package backendApp;

import java.util.List;

public class Dealer {

    private String id;
    private String name;
    private double latitude;
    private double longitude;
    private List<Vehicle> vehicles;
    private List<String> closed;

    public Dealer(String id, String name, double latitude, double longitude, List<Vehicle> vehicles, List<String> closed) {
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
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
}
