package com.line.domain;

public class Hospital {

    private String id;
    private String address;
    private String district;
    private String category;
    private Integer emergencyRoom;
    private String name;
    private String subdivition;

    public Hospital(String id, String address, String category, Integer emergencyRoom, String name) {
        this.id = id;
        this.address = address;
        this.category = category;
        this.emergencyRoom = emergencyRoom;
        this.name = name;
        this.setDistrict();
    }

    public void setDistrict() {
        String[] splitted = this.address.split(" ");
        this.district = String.format("%s %s", splitted[0], splitted[1]);
    }
    public Hospital(String id, String address) {
        this.id = id;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getDistrict() {
        return district;
    }

    public String getCategory() {
        return category;
    }

    public Integer getEmergencyRoom() {
        return emergencyRoom;
    }

    public String getName() {
        return name;
    }

    public String getSubdivition() {
        return subdivition;
    }
}
