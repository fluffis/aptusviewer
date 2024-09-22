package se.fluff.aptusviewer.models.db;

import java.util.Date;

public class AptusSystem {

    private String name;
    private String progCode;
    private int apbtime;
    private int flags;
    private String comPort;
    private int baudrate;
    private int serverPort;
    private String telNo;
    private String modemConf;
    private String computer;
    private String serverName;
    private Date lastUpdateOk;
    private Date lastUpdateFail;
    private int id;
    private String cryptKey;
    private String nextCryptKey;
    private int fillLimit;
    private String modemConfMaster;
    private boolean isChanged;
    private Date lastcontactMasterTime;
    private Date lastContactFailedTime;
    private int domainId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgCode() {
        return progCode;
    }

    public void setProgCode(String progCode) {
        this.progCode = progCode;
    }

    public int getApbtime() {
        return apbtime;
    }

    public void setApbtime(int apbtime) {
        this.apbtime = apbtime;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public String getComPort() {
        return comPort;
    }

    public void setComPort(String comPort) {
        this.comPort = comPort;
    }

    public int getBaudrate() {
        return baudrate;
    }

    public void setBaudrate(int baudrate) {
        this.baudrate = baudrate;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getModemConf() {
        return modemConf;
    }

    public void setModemConf(String modemConf) {
        this.modemConf = modemConf;
    }

    public String getComputer() {
        return computer;
    }

    public void setComputer(String computer) {
        this.computer = computer;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Date getLastUpdateOk() {
        return lastUpdateOk;
    }

    public void setLastUpdateOk(Date lastUpdateOk) {
        this.lastUpdateOk = lastUpdateOk;
    }

    public Date getLastUpdateFail() {
        return lastUpdateFail;
    }

    public void setLastUpdateFail(Date lastUpdateFail) {
        this.lastUpdateFail = lastUpdateFail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCryptKey() {
        return cryptKey;
    }

    public void setCryptKey(String cryptKey) {
        this.cryptKey = cryptKey;
    }

    public String getNextCryptKey() {
        return nextCryptKey;
    }

    public void setNextCryptKey(String nextCryptKey) {
        this.nextCryptKey = nextCryptKey;
    }

    public int getFillLimit() {
        return fillLimit;
    }

    public void setFillLimit(int fillLimit) {
        this.fillLimit = fillLimit;
    }

    public String getModemConfMaster() {
        return modemConfMaster;
    }

    public void setModemConfMaster(String modemConfMaster) {
        this.modemConfMaster = modemConfMaster;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setChanged(boolean changed) {
        isChanged = changed;
    }

    public Date getLastcontactMasterTime() {
        return lastcontactMasterTime;
    }

    public void setLastcontactMasterTime(Date lastcontactMasterTime) {
        this.lastcontactMasterTime = lastcontactMasterTime;
    }

    public Date getLastContactFailedTime() {
        return lastContactFailedTime;
    }

    public void setLastContactFailedTime(Date lastContactFailedTime) {
        this.lastContactFailedTime = lastContactFailedTime;
    }

    public int getDomainId() {
        return domainId;
    }

    public void setDomainId(int domainId) {
        this.domainId = domainId;
    }
}
