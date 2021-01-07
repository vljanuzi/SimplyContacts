package com.example.simplycontacts;

public class Contact {

    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String toStringName() { return this.name; }

    public String toString() {
        return this.name + "  " + this.phoneNumber;
    }
}