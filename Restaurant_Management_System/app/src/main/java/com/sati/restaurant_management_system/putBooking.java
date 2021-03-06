package com.sati.restaurant_management_system;

public class putBooking {


    String name,number,city,time,Tablelocation;


    public putBooking() {
    }

    @Override
    public String toString() {
        return "putBooking{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", city='" + city + '\'' +
                ", time='" + time + '\'' +
                ", Tablelocation='" + Tablelocation + '\'' +
                '}';
    }

    public putBooking(String name, String number, String city, String time, String tablelocation) {
        this.name = name;
        this.number = number;
        this.city = city;
        this.time = time;
        Tablelocation = tablelocation;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTablelocation() {
        return Tablelocation;
    }

    public void setTablelocation(String tablelocation) {
        Tablelocation = tablelocation;
    }
}
