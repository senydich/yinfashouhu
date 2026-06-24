package com.dtdx.dao;

import com.dtdx.entity.EventRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventRecordDao {
    List<EventRecord> eventRecordList(EventRecord eventRecord);
}
