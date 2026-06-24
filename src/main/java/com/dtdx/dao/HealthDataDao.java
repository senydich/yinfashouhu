package com.dtdx.dao;

import com.dtdx.entity.HealthData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HealthDataDao {
    List<HealthData> healthDataList(HealthData healthData);

    List<HealthData> listByElderIdPage(@Param("elderId") Integer elderId,
                                        @Param("offset") Integer offset,
                                        @Param("pageSize") Integer pageSize);

    Integer count(HealthData healthData);

    HealthData getById(@Param("id") Integer id);

    void add(HealthData healthData);

    void update(HealthData healthData);

    void delete(@Param("id") Integer id);
}
