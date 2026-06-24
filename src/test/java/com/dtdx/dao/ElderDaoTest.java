package com.dtdx.dao;

import com.dtdx.entity.Elder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class ElderDaoTest {

    @Autowired
    private ElderDao elderDao;

    @Test
    public void elderListTest() {
        List<Elder> elderList = elderDao.elderList(new Elder());
        for(Elder elder : elderList ){
            System.out.println(elder);
        }
    }

    @Test
    public void getByIdTest() {
        Elder elder = elderDao.getById(2);
        System.out.println(elder);
    }

    @Test
    public void updateTest() {
        Elder elder = new Elder();
        elder.setId(3);
        elder.setName("哒哒国");
        elder.setBirthday(parseDate("1450-01-01"));
        elder.setGender("1");
        elder.setHealthStatus("心脏病");
        elder.setEmergencyContact("大强");
        elder.setRelation("女儿");
        elder.setContactNumber("15535553555");
        elderDao.update(elder);

    }


    @Test
    public void addTest() {
        Elder elder = new Elder();
        elder.setName("王建国");
        elder.setBirthday(parseDate("1990-01-01"));
        elder.setGender("1");
        elder.setHealthStatus("高血压、糖尿病");
        elder.setEmergencyContact("王富强");
        elder.setRelation("儿子");
        elder.setContactNumber("15535553555");
        elderDao.add(elder);
    }

    @Test
    public void deleteTest() {
        elderDao.delete(2);
    }

    private Date parseDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try{
            date = sdf.parse(dateStr);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return date;
    }


}
