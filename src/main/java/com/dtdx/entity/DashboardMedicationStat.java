package com.dtdx.entity;

public class DashboardMedicationStat {
    private String statDate;
    private String dateLabel;
    private String dayName;
    private Integer expectedCount;
    private Integer completedCount;
    private Integer pendingCount;
    private Integer rate;
    private Integer barHeight;
    private String rateClass;
    private String changeText;
    private String changeClass;
    private Boolean currentDay;

    public String getStatDate() {
        return statDate;
    }

    public void setStatDate(String statDate) {
        this.statDate = statDate;
    }

    public String getDateLabel() {
        return dateLabel;
    }

    public void setDateLabel(String dateLabel) {
        this.dateLabel = dateLabel;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public Integer getExpectedCount() {
        return expectedCount;
    }

    public void setExpectedCount(Integer expectedCount) {
        this.expectedCount = expectedCount;
    }

    public Integer getCompletedCount() {
        return completedCount;
    }

    public void setCompletedCount(Integer completedCount) {
        this.completedCount = completedCount;
    }

    public Integer getPendingCount() {
        return pendingCount;
    }

    public void setPendingCount(Integer pendingCount) {
        this.pendingCount = pendingCount;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getBarHeight() {
        return barHeight;
    }

    public void setBarHeight(Integer barHeight) {
        this.barHeight = barHeight;
    }

    public String getRateClass() {
        return rateClass;
    }

    public void setRateClass(String rateClass) {
        this.rateClass = rateClass;
    }

    public String getChangeText() {
        return changeText;
    }

    public void setChangeText(String changeText) {
        this.changeText = changeText;
    }

    public String getChangeClass() {
        return changeClass;
    }

    public void setChangeClass(String changeClass) {
        this.changeClass = changeClass;
    }

    public Boolean getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(Boolean currentDay) {
        this.currentDay = currentDay;
    }
}
