package dm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.PrimitiveIterator;

public class User implements Serializable {
    private Long id;
    String userName;
    String password;
    Long phoneNum;
    ArrayList<Ad> ads;

    public User(String userName, String password, Long phoneNum, ArrayList<Ad> ads) {
        this.userName = userName;
        this.password = password;
        this.phoneNum = phoneNum;
        this.ads = ads;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Long getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(Long phoneNum) {
        this.phoneNum = phoneNum;
    }
    public ArrayList<Ad> getAds() {
        return ads;
    }
    public void setAds(ArrayList<Ad> ads) {
        this.ads = ads;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
