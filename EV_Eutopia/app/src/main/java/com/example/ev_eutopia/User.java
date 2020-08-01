package com.example.eveutopia;

public class User {
    public String first_name;
    public String last_name;
    public String address;
    public String email;
    public String latitude;
    public String longitude;
    public String vehicle_type;

    public User(){
    }

    public User(String first_name, String last_name, String address, String email, String latitude, String longitude, String vehicle_type){
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
        this.vehicle_type = vehicle_type;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getEv_type() {
        return vehicle_type;
    }

    public void setEv_type(String ev_type) {
        this.vehicle_type = ev_type;
    }
}
