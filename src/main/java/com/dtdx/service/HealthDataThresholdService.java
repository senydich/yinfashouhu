package com.dtdx.service;

import com.dtdx.dao.HealthDataThresholdDao;
import com.dtdx.entity.HealthDataThreshold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthDataThresholdService {
    @Autowired
    private HealthDataThresholdDao healthDataThresholdDao;



    public HealthDataThreshold getOne(){
        return healthDataThresholdDao.getOne();
    }

    public void update(HealthDataThreshold healthDataThreshold){
        healthDataThresholdDao.update(healthDataThreshold);
    }
}
