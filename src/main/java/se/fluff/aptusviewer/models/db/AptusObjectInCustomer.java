package se.fluff.aptusviewer.models.db;

import java.util.Date;

public class AptusObjectInCustomer {
    
    private long id;

    private long customerId;

    private long aptusObjectId;

    private String name;

    private Date startDate;

    private Date endDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getAptusObjectId() {
        return aptusObjectId;
    }

    public void setAptusObjectId(long aptusObjectId) {
        this.aptusObjectId = aptusObjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
