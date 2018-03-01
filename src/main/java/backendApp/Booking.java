package backendApp;

import java.util.Date;

public class Booking {

    private String id;
    private String vehicleid;
    private String firstName;
    private String lastName;
    private Date pickupDate;
    private Date createdAt;
    private Date cancelledAt;
    private String cancelledReason;

    public Booking(String id, String vehicleid, String firstName, String lastName, Date pickupDate, Date createdAt, Date cancelledAt, String cancelledReason) {
        this.id = id;
        this.vehicleid = vehicleid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pickupDate = pickupDate;
        this.createdAt = createdAt;
        this.cancelledAt = cancelledAt;
        this.cancelledReason = cancelledReason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCancelledAt() {
        return cancelledAt;
    }

    public void setCancelledAt(Date cancelledAt) {
        this.cancelledAt = cancelledAt;
    }

    public String getCancelledReason() {
        return cancelledReason;
    }

    public void setCancelledReason(String cancelledReason) {
        this.cancelledReason = cancelledReason;
    }
}
