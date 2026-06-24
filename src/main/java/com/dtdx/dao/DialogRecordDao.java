package com.dtdx.dao;

import com.dtdx.entity.DialogRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DialogRecordDao {
    List<DialogRecord> dialogRecordList(DialogRecord dialogRecord);
}
