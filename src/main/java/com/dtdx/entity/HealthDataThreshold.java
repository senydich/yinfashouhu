package com.dtdx.entity;

import java.util.Date;

public class HealthDataThreshold {

    private Integer id;
    private Double bloodPressureHigh;
    private Double bloodPressureLow;
    private Double bloodGlucoseHigh;
    private Double bloodGlucoseLow;
    private Integer heartRatelow;
    private Integer heartRatehigh;
    private Integer spo2;
    private Double temperatureHigh;
    private Double temperatureLow;
    private Double weight;
    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getBloodPressureHigh() {
        return bloodPressureHigh;
    }

    public void setBloodPressureHigh(Double bloodPressureHigh) {
        this.bloodPressureHigh = bloodPressureHigh;
    }

    public Double getBloodPressureLow() {
        return bloodPressureLow;
    }

    public void setBloodPressureLow(Double bloodPressureLow) {
        this.bloodPressureLow = bloodPressureLow;
    }

    public Double getBloodGlucoseHigh() {
        return bloodGlucoseHigh;
    }

    public void setBloodGlucoseHigh(Double bloodGlucoseHigh) {
        this.bloodGlucoseHigh = bloodGlucoseHigh;
    }

    public Double getBloodGlucoseLow() {
        return bloodGlucoseLow;
    }

    public void setBloodGlucoseLow(Double bloodGlucoseLow) {
        this.bloodGlucoseLow = bloodGlucoseLow;
    }

    public Integer getHeartRatelow() {
        return heartRatelow;
    }

    public void setHeartRatelow(Integer heartRatelow) {
        this.heartRatelow = heartRatelow;
    }

    public Integer getHeartRatehigh() {
        return heartRatehigh;
    }

    public void setHeartRatehigh(Integer heartRatehigh) {
        this.heartRatehigh = heartRatehigh;
    }

    public Integer getSpo2() {
        return spo2;
    }

    public void setSpo2(Integer spo2) {
        this.spo2 = spo2;
    }

    public Double getTemperatureHigh() {
        return temperatureHigh;
    }

    public void setTemperatureHigh(Double temperatureHigh) {
        this.temperatureHigh = temperatureHigh;
    }

    public Double getTemperatureLow() {
        return temperatureLow;
    }

    public void setTemperatureLow(Double temperatureLow) {
        this.temperatureLow = temperatureLow;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "HealthDataThreshold{" +
                "id=" + id +
                ", bloodPressureHigh=" + bloodPressureHigh +
                ", bloodPressureLow=" + bloodPressureLow +
                ", bloodGlucoseHigh=" + bloodGlucoseHigh +
                ", bloodGlucoseLow=" + bloodGlucoseLow +
                ", heartRatelow=" + heartRatelow +
                ", heartRatehigh=" + heartRatehigh +
                ", spo2=" + spo2 +
                ", temperatureHigh=" + temperatureHigh +
                ", temperatureLow=" + temperatureLow +
                ", weight=" + weight +
                ", updateDate=" + updateDate +
                '}';
    }
}
