package com.line.domain;

public class Hospital {

    private String id;
    private String address;
    private String district;
    private String category;
    public Hospital(String id) {
        this.id = id.replaceAll("\"","");
    }

    public String getId() {
        return id;
    }
}
