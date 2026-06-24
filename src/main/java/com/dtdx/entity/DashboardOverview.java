package com.dtdx.entity;

public class DashboardOverview {
    private Integer elderCount;
    private Integer regularMedicationCount;
    private Integer healthRecordTodayCount;
    private Integer onlineDeviceCount;
    private Integer alertTodayCount;
    private Integer medicationPendingTodayCount;
    private String weeklyAvgRateText;
    private String weeklyMaxRateText;
    private String weeklyMinRateText;
    private String weeklyMaxDateLabel;
    private String weeklyMinDateLabel;
    private String weeklyTrendText;
    private String weeklyTrendClass;
    private String lastUpdateText;
    private Integer medicationTarget;

    public Integer getElderCount() {
        return elderCount;
    }

    public void setElderCount(Integer elderCount) {
        this.elderCount = elderCount;
    }

    public Integer getRegularMedicationCount() {
        return regularMedicationCount;
    }

    public void setRegularMedicationCount(Integer regularMedicationCount) {
        this.regularMedicationCount = regularMedicationCount;
    }

    public Integer getHealthRecordTodayCount() {
        return healthRecordTodayCount;
    }

    public void setHealthRecordTodayCount(Integer healthRecordTodayCount) {
        this.healthRecordTodayCount = healthRecordTodayCount;
    }

    public Integer getOnlineDeviceCount() {
        return onlineDeviceCount;
    }

    public void setOnlineDeviceCount(Integer onlineDeviceCount) {
        this.onlineDeviceCount = onlineDeviceCount;
    }

    public Integer getAlertTodayCount() {
        return alertTodayCount;
    }

    public void setAlertTodayCount(Integer alertTodayCount) {
        this.alertTodayCount = alertTodayCount;
    }

    public Integer getMedicationPendingTodayCount() {
        return medicationPendingTodayCount;
    }

    public void setMedicationPendingTodayCount(Integer medicationPendingTodayCount) {
        this.medicationPendingTodayCount = medicationPendingTodayCount;
    }

    public String getWeeklyAvgRateText() {
        return weeklyAvgRateText;
    }

    public void setWeeklyAvgRateText(String weeklyAvgRateText) {
        this.weeklyAvgRateText = weeklyAvgRateText;
    }

    public String getWeeklyMaxRateText() {
        return weeklyMaxRateText;
    }

    public void setWeeklyMaxRateText(String weeklyMaxRateText) {
        this.weeklyMaxRateText = weeklyMaxRateText;
    }

    public String getWeeklyMinRateText() {
        return weeklyMinRateText;
    }

    public void setWeeklyMinRateText(String weeklyMinRateText) {
        this.weeklyMinRateText = weeklyMinRateText;
    }

    public String getWeeklyMaxDateLabel() {
        return weeklyMaxDateLabel;
    }

    public void setWeeklyMaxDateLabel(String weeklyMaxDateLabel) {
        this.weeklyMaxDateLabel = weeklyMaxDateLabel;
    }

    public String getWeeklyMinDateLabel() {
        return weeklyMinDateLabel;
    }

    public void setWeeklyMinDateLabel(String weeklyMinDateLabel) {
        this.weeklyMinDateLabel = weeklyMinDateLabel;
    }

    public String getWeeklyTrendText() {
        return weeklyTrendText;
    }

    public void setWeeklyTrendText(String weeklyTrendText) {
        this.weeklyTrendText = weeklyTrendText;
    }

    public String getWeeklyTrendClass() {
        return weeklyTrendClass;
    }

    public void setWeeklyTrendClass(String weeklyTrendClass) {
        this.weeklyTrendClass = weeklyTrendClass;
    }

    public String getLastUpdateText() {
        return lastUpdateText;
    }

    public void setLastUpdateText(String lastUpdateText) {
        this.lastUpdateText = lastUpdateText;
    }

    public Integer getMedicationTarget() {
        return medicationTarget;
    }

    public void setMedicationTarget(Integer medicationTarget) {
        this.medicationTarget = medicationTarget;
    }
}
