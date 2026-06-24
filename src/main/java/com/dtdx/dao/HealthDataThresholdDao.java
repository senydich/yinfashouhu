package com.dtdx.dao;

import com.dtdx.entity.HealthDataThreshold;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HealthDataThresholdDao {

    public HealthDataThreshold getOne();

    public void update(HealthDataThreshold healthDataThreshold);

}
