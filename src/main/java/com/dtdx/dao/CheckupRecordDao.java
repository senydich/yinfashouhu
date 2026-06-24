package com.dtdx.dao;

import com.dtdx.entity.CheckupRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CheckupRecordDao {
    List<CheckupRecord> checkupRecordList(CheckupRecord checkupRecord);

    CheckupRecord getById(@Param("id") Integer id);

    void add(CheckupRecord checkupRecord);

    void update(CheckupRecord checkupRecord);

    void delete(@Param("id") Integer id);
}
