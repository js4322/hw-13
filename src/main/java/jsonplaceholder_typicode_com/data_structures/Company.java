package jsonplaceholder_typicode_com.data_structures;

import java.util.Objects;

public class Company {
    private String name;
    private String catchPhrase;
    private String bs;

    public String getBs() {
        return bs;
    }

    public String getName() {
        return name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Company(String bs, String catchPhrase, String name) {
        this.bs = bs;
        this.catchPhrase = catchPhrase;
        this.name = name;
    }
    public Company(){
        this("standard \"bs\"", "common company", "Earth");
    }

    @Override
    public String toString() {
        return "Company{" +
                " name='" + name + '\'' +
                ", catchPhrase='" + catchPhrase + '\'' +
                ", bs='" + bs + '\'' +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return getName().equals(company.getName()) && getCatchPhrase().equals(company.getCatchPhrase()) && getBs().equals(company.getBs());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCatchPhrase(), getBs());
    }
}
