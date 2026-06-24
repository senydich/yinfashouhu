package com.dtdx.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HealthData {
    private Integer id;
    private Integer elderId;
    private String elderName;
    private Elder elder;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date recordTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date endTime;
    private String bloodPressure;
    private Double bloodGlucose;
    private Integer heartRate;
    private Integer spo2;
    private Double temperature;
    private Double weight;
    private Integer pageNum;
    private Integer pageSize;
    private Integer offset;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getElderId() {
        return elderId;
    }

    public void setElderId(Integer elderId) {
        this.elderId = elderId;
    }

    public String getElderName() {
        return elderName;
    }

    public void setElderName(String elderName) {
        this.elderName = elderName;
    }

    public Elder getElder() {
        return elder;
    }

    public void setElder(Elder elder) {
        this.elder = elder;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public Double getBloodGlucose() {
        return bloodGlucose;
    }

    public void setBloodGlucose(Double bloodGlucose) {
        this.bloodGlucose = bloodGlucose;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public Integer getSpo2() {
        return spo2;
    }

    public void setSpo2(Integer spo2) {
        this.spo2 = spo2;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "HealthData{" +
                "id=" + id +
                ", elderId=" + elderId +
                ", elderName='" + elderName + '\'' +
                ", elder=" + elder +
                ", recordTime=" + recordTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", bloodPressure='" + bloodPressure + '\'' +
                ", bloodGlucose=" + bloodGlucose +
                ", heartRate=" + heartRate +
                ", spo2=" + spo2 +
                ", temperature=" + temperature +
                ", weight=" + weight +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", offset=" + offset +
                '}';
    }
}
