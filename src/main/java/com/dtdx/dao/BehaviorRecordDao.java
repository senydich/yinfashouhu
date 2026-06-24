package com.dtdx.dao;

import com.dtdx.entity.BehaviorRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface BehaviorRecordDao {
    List<BehaviorRecord> behaviorRecordList(BehaviorRecord behaviorRecord);

    BehaviorRecord getById(@Param("id") Integer id);

    void add(BehaviorRecord behaviorRecord);

    void update(BehaviorRecord behaviorRecord);

    void delete(@Param("id") Integer id);

    List<BehaviorRecord> findByElderAndDate(@Param("elderId") Integer elderId, @Param("reportDate") Date reportDate);

    int countDuplicate(BehaviorRecord behaviorRecord);
}
