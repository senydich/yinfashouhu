package com.dtdx.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Elder {

    private Integer id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private Integer age;
    private String gender;
    private String healthStatus;
    private String emergencyContact;
    private String relation;
    private String contactNumber;
    private Date CreateDate;
    private Date UpadteDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        if(this.birthday != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            String year = sdf.format(birthday);
            int intYear = Integer.parseInt(year);
            String now = sdf.format(new Date());
            int nowYear = Integer.parseInt(now);
            return (nowYear - intYear);

        }else {
            return null;
        }
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public Date getUpadteDate() {
        return UpadteDate;
    }

    public void setUpadteDate(Date upadteDate) {
        UpadteDate = upadteDate;
    }

    @Override
    public String toString() {
        return "Elder{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", healthStatus='" + healthStatus + '\'' +
                ", emergencyContact='" + emergencyContact + '\'' +
                ", relation='" + relation + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", CreateDate=" + CreateDate +
                ", UpadteDate=" + UpadteDate +
                '}';
    }
}
