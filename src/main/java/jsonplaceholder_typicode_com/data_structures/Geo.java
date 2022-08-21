package jsonplaceholder_typicode_com.data_structures;

import java.util.Objects;

public class Geo {
    private String lat;
    private String lng;

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
    public Geo (String lat, String lng){
        this.lat = lat;
        this.lng = lng;
    }
    public Geo(){
        this("0","0");
    }

    @Override
    public String toString() {
        return "Geo{" +
                " lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Geo)) return false;
        Geo geo = (Geo) o;
        return getLat().equals(geo.getLat()) && getLng().equals(geo.getLng());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLat(), getLng());
    }
}
