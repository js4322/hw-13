package jsonplaceholder_typicode_com.data_structures;

import java.util.Objects;

public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipCode;
    private Geo geo;

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getSuite() {
        return suite;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }
    public Address (String street, String suite, String city, String zipCode, Geo geo){
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipCode = zipCode;
        this.geo = geo;
    }
    public Address(){
        this("vyshneva", "404", "Vinnutsya", "68374", new Geo());
    }

    @Override
    public String toString() {
        return "Address{" +
                ", street='" + street + '\'' +
                ", suite='" + suite + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", geo=" + geo +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return getStreet().equals(address.getStreet()) && getSuite().equals(address.getSuite()) && getCity().equals(address.getCity()) && getZipCode().equals(address.getZipCode()) && getGeo().equals(address.getGeo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStreet(), getSuite(), getCity(), getZipCode(), getGeo());
    }
}
