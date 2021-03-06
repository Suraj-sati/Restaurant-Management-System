package com.sati.restaurant_management_system;

public class putTable {

    String seats,location;

    public putTable() {
    }

    public putTable(String seats, String location) {
        this.seats = seats;
        this.location = location;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "putTable{" +
                "seats='" + seats + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
