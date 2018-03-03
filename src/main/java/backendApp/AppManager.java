package backendApp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AppManager {

    private List<Dealer> dealers;
    private List<Booking> bookings;
    private List<Vehicle> vehicles;

    //This is to insure that the class is a singleton
    private static AppManager instance = null;
    public static AppManager getInstance(){
        if(instance == null){
            instance = new AppManager();
        }
        return instance;
    }

    public AppManager() {
        JSONParser parser = new JSONParser();
        this.dealers = new ArrayList<Dealer>();
        this.bookings = new ArrayList<Booking>();
        this.vehicles = new ArrayList<Vehicle>();

        try{
            Object obj = parser.parse(new FileReader("./src/main/resources/dataset.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray dealersArray = (JSONArray) jsonObject.get("dealers"); //List of dealers from JSON

            //Iterate the dealers Array
            for(Object dealer : dealersArray){
                //instance to work with the dealer JSON
                JSONObject newDealer = (JSONObject) dealer;

                //Set of attributes retrieved from the dealer JSON:
                Double latitude = (Double) newDealer.get("latitude");
                Double longitude = (Double) newDealer.get("longitude");
                String id = (String) newDealer.get("id");
                String name = (String) newDealer.get("name");
                JSONArray vehiclesArray = (JSONArray) newDealer.get("vehicles");
                List<String> closedList = (List<String>) newDealer.get("closed");

                //temporary vehiclesList to build the dealer object
                List<Vehicle> vehiclesList = new ArrayList<Vehicle>();

                //Iterate the vehicles Array
                for(Object vehicle : vehiclesArray){
                    //instance to work with the Vehicle JSON
                    JSONObject newVehicle = (JSONObject) vehicle;

                    //Set of attributes retrieved from the dealer JSON:
                    String vehicleId = (String) newVehicle.get("id");
                    String model = (String) newVehicle.get("model");
                    String fuel = (String) newVehicle.get("fuel");
                    String transmission = (String) newVehicle.get("transmission");
                    JSONObject availability = (JSONObject) newVehicle.get("availability");

                    //temporary availability hashMAP to build the vehicles object
                    HashMap<String, List<String>> availabilityMap = new HashMap<String, List<String>>();

                    for (Object key : availability.keySet()){
                        String dayOfWeek = (String) key;
                        List<String> availabilityList = (List<String>) availability.get(dayOfWeek);
                        availabilityMap.put(dayOfWeek,availabilityList);
                    }

                    Vehicle vehicleObj = new Vehicle(vehicleId, model, fuel, transmission, availabilityMap);
                    vehiclesList.add(vehicleObj);

                    //I will create a new object but without id or availability, this is for the listBy method of the API
                    Vehicle vehicleSimplified = vehicleObj;
                    //vehicleSimplified.setAvailability(null);
                    //vehicleSimplified.setId(null);
                    //This is to ensure that one and only one model, fuel,transmission combination is inserted
                    if(!this.vehicles.contains(vehicleSimplified)){
                        this.vehicles.add(vehicleSimplified);
                    }
                }

                Dealer dealerObj = new Dealer(id,name,latitude,longitude,vehiclesList,closedList);
                this.dealers.add(dealerObj);


            }
            //Still missing the booking part

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public JSONObject listByModel(){
        Collections.sort(this.vehicles,Vehicle.comparatorByModel);
        JSONObject returnJSON = new JSONObject();
        JSONArray  arrayJSON = new JSONArray();
        for (int i = 0; i<this.vehicles.size(); i++){
            Vehicle vehicle = this.vehicles.get(i);
            JSONObject auxJSON = new JSONObject();
            auxJSON.put("fuel", vehicle.getFuel());
            auxJSON.put("transmission", vehicle.getTransmission());
            arrayJSON.add(auxJSON);
            if(i==this.vehicles.size()-1){
                returnJSON.put(vehicle.getModel(),arrayJSON);
                break;
            }
            Vehicle nextVehicle = this.vehicles.get(i+1);
            if(!vehicle.getModel().equals(nextVehicle.getModel())){
                returnJSON.put(vehicle.getModel(),arrayJSON);
                arrayJSON = new JSONArray();
            }
        }
        return returnJSON;
    }
}
