package com.singlestone;

/**
 * pojo used for returning the results of the contact list sort functionality
 */

public class CallListObj {

    private Name name;
    private String phone;

    public CallListObj(){}

    public CallListObj(Name name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
