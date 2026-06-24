package com.dtdx.dao;

import com.dtdx.entity.FallRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FallRecordDao {
    List<FallRecord> fallRecordList(FallRecord fallRecord);
}
