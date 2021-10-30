package ru.itmo.soalab2.model;

public class CityNameAndRoadLength {
    private Double length;
    private String city;
    public CityNameAndRoadLength(Double length, String name) {
        this.length = length;
        this.city = name;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
