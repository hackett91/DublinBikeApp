package com.example.cian.testproject.Model;

public class FavouriteStation {

        private int id;
        private String name;
        private String address;
        private String available_bike_stands;
        private String available_bikes;

        public FavouriteStation(int id, String name, String address, String available_bike_stands, String available_bikes){
            this.setId(id);
            this.setName(name);
            this.setAddress(address);
            this.setAvailable_bike_stands(available_bike_stands);
            this.setAvailable_bikes(available_bikes);
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvailable_bike_stands() {
        return available_bike_stands;
    }

    public void setAvailable_bike_stands(String available_bike_stands) {
        this.available_bike_stands = available_bike_stands;
    }

    public String getAvailable_bikes() {
        return available_bikes;
    }

    public void setAvailable_bikes(String available_bikes) {
        this.available_bikes = available_bikes;
    }

}
