package hcmte.edu.vn.Nhom19;

public class Shop {
    private String name;
    private String detail;
    private int thumbnail;
    private double rate;
    private String address;
    private double distance;


    public Shop(String name, String detail, int thumbnail) {
        this.name = name;
        this.detail = detail;
        this.thumbnail = thumbnail;
    }

    public Shop(String name, int thumbnail, double rate, String address, double distance) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.rate = rate;
        this.address = address;
        this.distance = distance;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getRate() {
        return this.rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
