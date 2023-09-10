package se.fluff.aptusviewer.models.db;


import java.util.Calendar;

public class CustomerPerson {

    private int id;

    private int objectInCustomer;

    private String firstName;

    private String surname;

    private String phoneNumber;

    private String entryPhoneCallCode;

    private boolean visible;

    private String fastApiGuid;

    private boolean isImported;

    private int sortOrder;

    private int version;

    private Calendar fastApiTimestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getObjectInCustomer() {
        return objectInCustomer;
    }

    public void setObjectInCustomer(int objectInCustomer) {
        this.objectInCustomer = objectInCustomer;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEntryPhoneCallCode() {
        return entryPhoneCallCode;
    }

    public void setEntryPhoneCallCode(String entryPhoneCallCode) {
        this.entryPhoneCallCode = entryPhoneCallCode;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getFastApiGuid() {
        return fastApiGuid;
    }

    public void setFastApiGuid(String fastApiGuid) {
        this.fastApiGuid = fastApiGuid;
    }

    public boolean isImported() {
        return isImported;
    }

    public void setImported(boolean imported) {
        isImported = imported;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Calendar getFastApiTimestamp() {
        return fastApiTimestamp;
    }

    public void setFastApiTimestamp(Calendar fastApiTimestamp) {
        this.fastApiTimestamp = fastApiTimestamp;
    }
}
