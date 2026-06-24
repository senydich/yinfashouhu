package com.dtdx.dao;

import com.dtdx.entity.AccompanyRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AccompanyRecordDao {
    List<AccompanyRecord> accompanyRecordList(AccompanyRecord accompanyRecord);

    AccompanyRecord getById(@Param("id") Integer id);

    void add(AccompanyRecord accompanyRecord);

    void update(AccompanyRecord accompanyRecord);

    void delete(@Param("id") Integer id);
}
