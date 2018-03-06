package backendApp;

public class Index {
    /* This class is to use as index in the hashmaps, with this I can have an efficient lookup for bookings*/
    // this class is generic so it can be used for future needs
    private String x; //In this particular case vehicleId
    private String y; //In this particular case pickupDate

    public Index(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public boolean equals(Object object) {
        if (!(object instanceof Index)){
            return false;
        }
        if (!((Index) object).getX().equals(x)){
            return false;
        }
        if (!((Index) object).getY().equals(y)){
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.x.hashCode() + this.y.hashCode();
    }


}
