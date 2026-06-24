package com.dtdx.dao;

import com.dtdx.entity.HealthDataThreshold;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HealthDataThresholdDaoTest {

    @Autowired
    private HealthDataThresholdDao healthDataThresholdDao;

    @Test
    public void getOneTest() {
        HealthDataThreshold healthDataThreshold = healthDataThresholdDao.getOne();
        System.out.println(healthDataThreshold);
    }

    @Test
    public void update() {
        HealthDataThreshold healthDataThreshold = healthDataThresholdDao.getOne();
        healthDataThreshold.setSpo2(200);
        healthDataThreshold.setWeight(6.0);
        healthDataThresholdDao.update(healthDataThreshold);

    }

}
