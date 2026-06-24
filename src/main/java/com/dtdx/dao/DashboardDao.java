package com.dtdx.dao;

import com.dtdx.entity.DashboardMedicationStat;
import com.dtdx.entity.DashboardOverview;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DashboardDao {
    DashboardOverview getOverview();

    List<DashboardMedicationStat> listMedicationStats();
}
