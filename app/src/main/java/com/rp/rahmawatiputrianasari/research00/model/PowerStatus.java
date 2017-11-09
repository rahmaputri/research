package com.rp.rahmawatiputrianasari.research00.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by rahmawatiputrianasari on 11/7/17.
 */

@DatabaseTable(tableName = PowerStatus.TABLE_NAME)
public class PowerStatus {
    public static final String TABLE_NAME = "PowerStatus";
    public static final String FIELD_ID = "id";
    public static final String FIELD_TIME = "time";
    public static final String FIELD_STATUS = "status";

    @DatabaseField(id = true, columnName = FIELD_ID)
    private Integer id;

    @DatabaseField(columnName = FIELD_TIME)
    private String time;

    @DatabaseField(columnName = FIELD_STATUS)
    private String status;

    public PowerStatus() {
    }

    public PowerStatus(String time, String status) {
        this.id = id;
        this.time = time;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PowerStatus{" +
                "id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", status=" + status +
                '}';
    }
}
