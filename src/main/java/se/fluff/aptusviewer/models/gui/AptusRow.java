package se.fluff.aptusviewer.models.gui;

import se.fluff.aptusviewer.models.db.User;

import java.util.ArrayList;
import java.util.List;

public class AptusRow {

    private long objectId;
    private String objectName;
    private int addressId;
    private int phoneAddress;
    private int floor;

    private long customerId;
    private String customer = "";
    private String FirstName;
    private String Surname = "";

    private String objectAuthorities;

    private List<User> userList = new ArrayList<>();

    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getPhoneAddress() {
        return phoneAddress;
    }

    public void setPhoneAddress(int phoneAddress) {
        this.phoneAddress = phoneAddress;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomer() {
        return customer == null ? "" : customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getSurname() {
        return Surname == null ? "" : Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getObjectAuthorities() {
        return objectAuthorities;
    }

    public void setObjectAuthorities(String objectAuthorities) {
        this.objectAuthorities = objectAuthorities;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "AptusRow{" +
                "objectId=" + objectId +
                ", objectName='" + objectName + '\'' +
                ", addressId=" + addressId +
                ", phoneAddress=" + phoneAddress +
                ", floor=" + floor +
                ", customerId=" + customerId +
                ", customer='" + customer + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", Surname='" + Surname + '\'' +
                ", authorities='" + objectAuthorities + '\'' +
                ", userList=" + userList +
                '}';
    }
}
