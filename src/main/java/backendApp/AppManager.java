package backendApp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class AppManager {

    private List<Dealer> dealers;
    private HashMap<String,Index> bookingsIdMap;
    private HashMap<Index, Booking> bookings;
    private List<Vehicle> vehicles;
    private HashMap<String,Vehicle> idMapVehicle;
    private HashMap<String,Dealer> idMapDealer;


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
        this.vehicles = new ArrayList<Vehicle>();
        //The use of four hashMaps is to ensure a more efficient lookup method to the create and cancel operations
        this.bookingsIdMap = new HashMap<String, Index>();
        this.bookings = new HashMap<Index, Booking>();
        this.idMapDealer = new HashMap<String, Dealer>();
        this.idMapVehicle = new HashMap<String, Vehicle>();

        try{
            Object obj = parser.parse(new FileReader("./src/main/resources/dataset.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray dealersArray = (JSONArray) jsonObject.get("dealers"); //List of dealers from JSON
            JSONArray bookingsArray = (JSONArray) jsonObject.get("bookings"); //List of bookings from JSON

            DateFormat pickupFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            DateFormat createdAtFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            //Iterate the bookings Array
            for(Object booking : bookingsArray){
                JSONObject newBooking = (JSONObject) booking;
                String id = (String) newBooking.get("id");
                String vehicleId = (String) newBooking.get("vehicleId");
                String firstName = (String) newBooking.get("firstName");
                String lastName = (String) newBooking.get("lastName");
                Date pickupDate = pickupFormatter.parse((String)newBooking.get("pickupDate"));
                Date createdAt = createdAtFormatter.parse((String)newBooking.get("createdAt"));
                Booking bookingObj = new Booking(id,vehicleId,firstName,lastName, pickupDate,createdAt);
                Index index = new Index(vehicleId,pickupDate.toString());
                this.bookingsIdMap.put(id,index);
                this.bookings.put(index,bookingObj);


            }




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


                    //This is to ensure that one and only one model, fuel,transmission combination is inserted
                    if(!this.vehicles.contains(vehicleObj)){
                        this.vehicles.add(vehicleObj);
                    }
                    this.idMapVehicle.put(vehicleId,vehicleObj);
                }

                Dealer dealerObj = new Dealer(id,name,latitude,longitude,vehiclesList,closedList);
                this.dealers.add(dealerObj);
                for(Vehicle vehicle : dealerObj.getVehicles()){
                    this.idMapDealer.put(vehicle.getId(), dealerObj);
                }


            }
            //Still missing the booking part

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
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

    public JSONObject listByFuel(){
        Collections.sort(this.vehicles,Vehicle.comparatorByFuel);
        JSONObject returnJSON = new JSONObject();
        JSONArray  arrayJSON = new JSONArray();
        for(int i = 0;i< this.vehicles.size();i++){
            Vehicle vehicle = this.vehicles.get(i);
            JSONObject auxJSON = new JSONObject();
            auxJSON.put("transmission", vehicle.getTransmission());
            auxJSON.put("model", vehicle.getModel());
            arrayJSON.add(auxJSON);
            if(i==this.vehicles.size()-1){
                returnJSON.put(vehicle.getFuel(),arrayJSON);
                break;
            }
            Vehicle nextVehicle = this.vehicles.get(i+1);
            if(!vehicle.getFuel().equals(nextVehicle.getFuel())){
                returnJSON.put(vehicle.getFuel(),arrayJSON);
                arrayJSON = new JSONArray();
            }
        }
        return returnJSON;

    }

    public JSONObject listByTransmission(){
        Collections.sort(this.vehicles,Vehicle.comparatorByTransmission);
        JSONObject returnJSON = new JSONObject();
        JSONArray  arrayJSON = new JSONArray();
        for(int i = 0;i< this.vehicles.size();i++){
            Vehicle vehicle = this.vehicles.get(i);
            JSONObject auxJSON = new JSONObject();
            auxJSON.put("model", vehicle.getModel());
            auxJSON.put("fuel", vehicle.getFuel());
            arrayJSON.add(auxJSON);
            if(i==this.vehicles.size()-1){
                returnJSON.put(vehicle.getTransmission(),arrayJSON);
                break;
            }
            Vehicle nextVehicle = this.vehicles.get(i+1);
            if(!vehicle.getTransmission().equals(nextVehicle.getTransmission())){
                returnJSON.put(vehicle.getTransmission(),arrayJSON);
                arrayJSON = new JSONArray();
            }
        }
        return returnJSON;

    }


    public JSONObject listByDealer(){
        JSONObject returnJSON = new JSONObject();

        for( Dealer dealer : this.dealers){

            List<Vehicle> vehiclesArray = dealer.getVehicles();
            JSONArray vehiclesJSONArray = new JSONArray();

            for( Vehicle vehicle : vehiclesArray){
                vehiclesJSONArray.add(vehicle.toJSON());
            }
            returnJSON.put(dealer.getName(),vehiclesJSONArray);
        }
        return returnJSON;

    }

    public JSONObject findClosestDealer(Double latitude, Double longitude, String model, String fuel, String transmission){
        JSONObject closestDealer = new JSONObject();
        Double minDistance = Double.MAX_VALUE;
        for(Dealer dealer : this.dealers){
            if(dealer.hasVehicle(model,transmission,fuel)){
                if(dealer.getDistance(latitude,longitude) < minDistance){
                    closestDealer = dealer.toJSON();
                }
            }
        }
        return closestDealer;

    }

    public String createBooking(String vehicleId, String firstName, String lastName, String pickupDate){
        Index index = new Index(vehicleId,pickupDate);
        String statusCode = "Booking could not be created. Please choose a new date.";
        String uniqueID = UUID.randomUUID().toString();
        if(this.bookings.containsKey(index)){
            Booking newBooking = this.bookings.get(index);
            if(newBooking.getCancelledAt()!=null){ //means that exists but has been canceled, so we can delete it to make a new one
                this.bookingsIdMap.remove(newBooking.getId());
                this.bookings.remove(index);
            }
            else{
                statusCode = "This vehicle is already booked for this date. Please choose a new date.";
                return statusCode;
            }
        }
        Dealer dealer = this.idMapDealer.get(vehicleId);
        if(dealer==null){
            statusCode = "This vehicle does not exist!";
            return statusCode;
        }
        Vehicle vehicle = this.idMapVehicle.get(vehicleId);
        DateFormat dayOfWeekFormatter = new SimpleDateFormat("EEEE");
        DateFormat pickupFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date pickup = null;
        try {
            pickup = pickupFormatter.parse(pickupDate);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        String dayOfWeek = dayOfWeekFormatter.format(pickup).toLowerCase();
        if(dealer.getClosed().contains(dayOfWeek)){  //check if the dealer is closed on the pickup date
            statusCode = "Dealer closed on this date. Please choose a new date.";
            return statusCode;
        }
        if(vehicle.getAvailability().containsKey(dayOfWeek)){  //check if the car has availabilty
            List<String> hoursAvailable = vehicle.getAvailability().get(dayOfWeek);
            SimpleDateFormat hoursFormatter = new SimpleDateFormat("HHmm");
            String pickupHours = hoursFormatter.format(pickup);
            if(hoursAvailable.contains(pickupHours)){

                DateFormat createdAtFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
                Date createdAt = new Date(); //this is created with current date
                createdAtFormatter.format(createdAt);
                Booking booking = new Booking(uniqueID, vehicleId, firstName,lastName, pickup, createdAt);
                this.bookingsIdMap.put(uniqueID,index);
                this.bookings.put(index,booking);
                statusCode = "Booking created successfully! ID created: " + uniqueID;
                return statusCode;

            }
        }
        return statusCode;

    }


    public String cancelBooking(String id, String reason){

        Index index = this.bookingsIdMap.get(id);
        if(index == null){
            return "This booking does not exist.";
        }
        Booking booking = this.bookings.get(index);
        if(booking.getCancelledAt()!= null){
            return "Booking already been cancelled.";
        }
        DateFormat createdAtFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Date cancelledAt = new Date(); //this is created with current date
        createdAtFormatter.format(cancelledAt);
        booking.setCancelledAt(cancelledAt);
        booking.setCancelledReason(reason);
        this.bookings.replace(index,booking);
        return "Booking cancelled successfully";
    }

}
