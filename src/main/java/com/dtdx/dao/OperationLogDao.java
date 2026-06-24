package com.dtdx.dao;

import com.dtdx.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OperationLogDao {
    List<OperationLog> operationLogList(OperationLog operationLog);

    OperationLog getById(@Param("id") Integer id);

    void add(OperationLog operationLog);

    void update(OperationLog operationLog);

    void delete(@Param("id") Integer id);
}
