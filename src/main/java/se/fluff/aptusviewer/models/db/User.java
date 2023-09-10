package se.fluff.aptusviewer.models.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class User {

    private int id;

    private String name;

    private String code;

    private String card;

    private Date start;

    private Date stop;

    private boolean blocked;

    private String f0;

    private String f1;

    private String f2;

    private String f3;

    private String f4;

    private String f5;

    private String f6;

    private String f7;

    private String f8;

    private String f9;

    private String f10;

    private String f11;

    private String f12;

    private String f13;

    private String f14;

    private int fi0;

    private int fi1;

    private int fi2;

    private int fi3;

    private int fi4;

    private int changed;

    private int customerId;

    private Date createdTime;

    private boolean pulBlock;

    private String cardLabel;

    private String fastApiGuid;

    private int version;

    private String fastApiId;

    private Date inactiveCheck;

    private List<Authority> userAuthorities = new ArrayList<>();

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getStop() {
        return stop;
    }

    public void setStop(Date stop) {
        this.stop = stop;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getF0() {
        return f0;
    }

    public void setF0(String f0) {
        this.f0 = f0;
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

    public String getF4() {
        return f4;
    }

    public void setF4(String f4) {
        this.f4 = f4;
    }

    public String getF5() {
        return f5;
    }

    public void setF5(String f5) {
        this.f5 = f5;
    }

    public String getF6() {
        return f6;
    }

    public void setF6(String f6) {
        this.f6 = f6;
    }

    public String getF7() {
        return f7;
    }

    public void setF7(String f7) {
        this.f7 = f7;
    }

    public String getF8() {
        return f8;
    }

    public void setF8(String f8) {
        this.f8 = f8;
    }

    public String getF9() {
        return f9;
    }

    public void setF9(String f9) {
        this.f9 = f9;
    }

    public String getF10() {
        return f10;
    }

    public void setF10(String f10) {
        this.f10 = f10;
    }

    public String getF11() {
        return f11;
    }

    public void setF11(String f11) {
        this.f11 = f11;
    }

    public String getF12() {
        return f12;
    }

    public void setF12(String f12) {
        this.f12 = f12;
    }

    public String getF13() {
        return f13;
    }

    public void setF13(String f13) {
        this.f13 = f13;
    }

    public String getF14() {
        return f14;
    }

    public void setF14(String f14) {
        this.f14 = f14;
    }

    public int getFi0() {
        return fi0;
    }

    public void setFi0(int fi0) {
        this.fi0 = fi0;
    }

    public int getFi1() {
        return fi1;
    }

    public void setFi1(int fi1) {
        this.fi1 = fi1;
    }

    public int getFi2() {
        return fi2;
    }

    public void setFi2(int fi2) {
        this.fi2 = fi2;
    }

    public int getFi3() {
        return fi3;
    }

    public void setFi3(int fi3) {
        this.fi3 = fi3;
    }

    public int getFi4() {
        return fi4;
    }

    public void setFi4(int fi4) {
        this.fi4 = fi4;
    }

    public int getChanged() {
        return changed;
    }

    public void setChanged(int changed) {
        this.changed = changed;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isPulBlock() {
        return pulBlock;
    }

    public void setPulBlock(boolean pulBlock) {
        this.pulBlock = pulBlock;
    }

    public String getCardLabel() {
        return cardLabel;
    }

    public void setCardLabel(String cardLabel) {
        this.cardLabel = cardLabel;
    }

    public String getFastApiGuid() {
        return fastApiGuid;
    }

    public void setFastApiGuid(String fastApiGuid) {
        this.fastApiGuid = fastApiGuid;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getFastApiId() {
        return fastApiId;
    }

    public void setFastApiId(String fastApiId) {
        this.fastApiId = fastApiId;
    }

    public Date getInactiveCheck() {
        return inactiveCheck;
    }

    public void setInactiveCheck(Date inactiveCheck) {
        this.inactiveCheck = inactiveCheck;
    }

    public List<Authority> getUserAuthorities() {
        return userAuthorities;
    }

    public void setUserAuthorities(List<Authority> userAuthorities) {
        this.userAuthorities = userAuthorities;
    }

    public String getAuthorities() {

        return userAuthorities.stream()
                .map(a -> a.getName().replaceFirst("^Brf Ada[-| ]?", ""))
                .collect(Collectors.joining(", "));

    }
}
