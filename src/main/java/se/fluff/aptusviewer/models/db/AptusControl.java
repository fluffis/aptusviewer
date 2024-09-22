package se.fluff.aptusviewer.models.db;

import java.util.Date;

public class AptusControl {

    private int id;
    private int systemId;
    private String name;
    private int flags;
    private int type;
    private Date eventStart;
    private Date eventStop;
    private Date lastActiveTime;
    private Date lastInactiveTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return name.replaceFirst("^Brf Ada[-| ]?", "");
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getEventStart() {
        return eventStart;
    }

    public void setEventStart(Date eventStart) {
        this.eventStart = eventStart;
    }

    public Date getEventStop() {
        return eventStop;
    }

    public void setEventStop(Date eventStop) {
        this.eventStop = eventStop;
    }

    public Date getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(Date lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

    public Date getLastInactiveTime() {
        return lastInactiveTime;
    }

    public void setLastInactiveTime(Date lastInactiveTime) {
        this.lastInactiveTime = lastInactiveTime;
    }
}
