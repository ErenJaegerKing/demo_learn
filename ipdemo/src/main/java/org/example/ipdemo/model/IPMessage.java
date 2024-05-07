package org.example.ipdemo.model;

public class IPMessage {
    private String country;
    private String province;
    private String city;
    private Double longitude;
    private Double Latitude;

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "IPMessage{" +
                "country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", longitude='" + longitude + '\'' +
                ", Latitude='" + Latitude + '\'' +
                '}';
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }
}
