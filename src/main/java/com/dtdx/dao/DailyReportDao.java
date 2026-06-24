package com.dtdx.dao;

import com.dtdx.entity.DailyReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface DailyReportDao {
    List<DailyReport> dailyReportList(DailyReport dailyReport);

    DailyReport getById(@Param("id") Integer id);

    DailyReport getByElderAndDate(@Param("elderId") Integer elderId, @Param("reportDate") Date reportDate);

    void add(DailyReport dailyReport);

    void update(DailyReport dailyReport);

    void delete(@Param("id") Integer id);
}
