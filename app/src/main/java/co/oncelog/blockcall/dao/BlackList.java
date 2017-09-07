package co.oncelog.blockcall.dao;

/**
 * Created by son_g on 9/7/2017.
 */

public class BlackList {
    private long id;
    private String phoneNumber;

    public BlackList() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
