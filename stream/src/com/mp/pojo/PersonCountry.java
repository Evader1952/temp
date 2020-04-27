package com.mp.pojo;

public class PersonCountry {
    private String country;


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "PersonCountry{" +
                "country='" + country + '\'' +
                '}';
    }
}
