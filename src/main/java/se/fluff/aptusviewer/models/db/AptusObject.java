package se.fluff.aptusviewer.models.db;


import jakarta.persistence.Entity;

@Entity(name="Object")
public class AptusObject {

    private int id;

    private String name;

    private int addressId;

    private String entryPhoneCallCode;

    private int apartmentPhoneAddress;

    private int floor;

    private String floorText;

    private String apartmentNo;

    private String f1;

    private String f2;

    private String f3;


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

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getEntryPhoneCallCode() {
        return entryPhoneCallCode;
    }

    public void setEntryPhoneCallCode(String entryPhoneCallCode) {
        this.entryPhoneCallCode = entryPhoneCallCode;
    }

    public int getApartmentPhoneAddress() {
        return apartmentPhoneAddress;
    }

    public void setApartmentPhoneAddress(int apartmentPhoneAddress) {
        this.apartmentPhoneAddress = apartmentPhoneAddress;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getFloorText() {
        return floorText;
    }

    public void setFloorText(String floorText) {
        this.floorText = floorText;
    }

    public String getApartmentNo() {
        return apartmentNo;
    }

    public void setApartmentNo(String apartmentNo) {
        this.apartmentNo = apartmentNo;
    }

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    public String getF2() {
        return f2;
    }

    public void setF2(String f2) {
        this.f2 = f2;
    }

    public String getF3() {
        return f3;
    }

    public void setF3(String f3) {
        this.f3 = f3;
    }
}
