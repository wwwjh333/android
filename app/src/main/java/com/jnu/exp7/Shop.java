package com.jnu.exp7;

import org.json.JSONException;
import org.json.JSONObject;

public class Shop {
    public String name;
    public double latitude;
    public double longitude;
    public String memo;

    Shop(String name,Double latitude,Double longitude,String memo)
    {
        this.name=name;
        this.latitude=latitude;
        this.longitude=longitude;
        this.memo=memo;
    }
    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getMemo() {
        return memo;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Shop sectionData(JSONObject json){
        try {
            return new Shop(
                    json.getString("name"),
                    json.getDouble("latitude"),
                    json.getDouble("longitude"),
                    json.getString("memo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
