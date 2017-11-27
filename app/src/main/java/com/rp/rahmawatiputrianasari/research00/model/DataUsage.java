package com.rp.rahmawatiputrianasari.research00.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by rahmawatiputrianasari on 11/27/17.
 */


@DatabaseTable(tableName = DataUsage.TABLE_NAME)
public class DataUsage {
    public static final String TABLE_NAME = "DataUsage";
    public static final String FIELD_ID = "id";
    public static final String FIELD_TIME = "time";
    public static final String FIELD_TOTAL = "total";
    public static final String FIELD_WIFI = "wifi";
    public static final String FIELD_NETWORK = "network";

    @DatabaseField(id = true, columnName = FIELD_ID)
    private Integer id;

    @DatabaseField(columnName = FIELD_TIME)
    private String time;

    @DatabaseField(columnName = FIELD_TOTAL)
    private String total;

    @DatabaseField(columnName = FIELD_WIFI)
    private String wifi;

    @DatabaseField(columnName = FIELD_NETWORK)
    private String network;

    public DataUsage() {
    }

    public DataUsage(String time, String total, String wifi, String network) {
        this.time = time;
        this.total = total;
        this.wifi = wifi;
        this.network = network;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    @Override
    public String toString() {
        return "DataUsage{" +
                "id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", total=" + total +
                ", wifi=" + wifi +
                ", network=" + network +
                '}';
    }
}

