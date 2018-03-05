package backendApp;

public class Index {
    /* This class is to use as index in the hashmaps, with this I can have an efficient lookup for bookings*/

    private String x;
    private String y;

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
