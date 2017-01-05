package com.lihao.bitcoinj;

import java.util.List;

/**
 * Created by sbwdlihao on 05/01/2017.
 */
public class Difficulty {
    private String status;
    private String name;
    private String unit;
    private String period;
    private String description;
    private List<Point> values;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Point> getValues() {
        return values;
    }

    public void setValues(List<Point> values) {
        this.values = values;
    }

    public Difficulty() {
    }
}
