package backendApp;

import java.util.Date;

public class Booking {

    private String id;
    private String vehicleId;
    private String firstName;
    private String lastName;
    private Date pickupDate;
    private Date createdAt;
    private Date cancelledAt;
    private String cancelledReason;

    public Booking(String id, String vehicleId, String firstName, String lastName, Date pickupDate, Date createdAt) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pickupDate = pickupDate;
        this.createdAt = createdAt;
    }

    public Booking(String id, String vehicleId, String firstName, String lastName, Date pickupDate, Date createdAt, Date cancelledAt, String cancelledReason) {
        this.id = id;
        this.vehicleId = vehicleId;
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

    public String getvehicleId() {
        return vehicleId;
    }

    public void setvehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
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
