package com.dtdx.service;

import com.dtdx.dao.HealthDataDao;
import com.dtdx.entity.HealthData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthDataService {
    @Autowired
    private HealthDataDao healthDataDao;

    public List<HealthData> healthDataList(HealthData healthData) {
        preparePage(healthData);
        trimQuery(healthData);
        return healthDataDao.healthDataList(healthData);
    }

    public List<HealthData> listByElderIdPage(Integer elderId, Integer pageNum, Integer pageSize) {
        if (elderId == null) {
            throw new RuntimeException("老人ID不能为空");
        }
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }
        return healthDataDao.listByElderIdPage(elderId, (pageNum - 1) * pageSize, pageSize);
    }

    public Integer count(HealthData healthData) {
        trimQuery(healthData);
        Integer total = healthDataDao.count(healthData);
        return total == null ? 0 : total;
    }

    public HealthData getById(Integer id) {
        if (id == null) {
            throw new RuntimeException("健康数据ID不能为空");
        }
        HealthData healthData = healthDataDao.getById(id);
        if (healthData == null) {
            throw new RuntimeException("健康数据记录不存在");
        }
        return healthData;
    }

    public void add(HealthData healthData) {
        prepareAndValidate(healthData);
        healthDataDao.add(healthData);
    }

    public void update(HealthData healthData) {
        if (healthData.getId() == null) {
            throw new RuntimeException("健康数据ID不能为空");
        }
        getById(healthData.getId());
        prepareAndValidate(healthData);
        healthDataDao.update(healthData);
    }

    public void delete(Integer id) {
        getById(id);
        healthDataDao.delete(id);
    }

    public int getTotalPages(Integer total, Integer pageSize) {
        if (total == null || total <= 0) {
            return 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }
        return (total + pageSize - 1) / pageSize;
    }

    private void preparePage(HealthData healthData) {
        if (healthData.getPageNum() == null || healthData.getPageNum() < 1) {
            healthData.setPageNum(1);
        }
        if (healthData.getPageSize() == null || healthData.getPageSize() < 1) {
            healthData.setPageSize(10);
        }
        healthData.setOffset((healthData.getPageNum() - 1) * healthData.getPageSize());
    }

    private void prepareAndValidate(HealthData healthData) {
        if (healthData == null) {
            throw new RuntimeException("健康数据不能为空");
        }
        healthData.setBloodPressure(trim(healthData.getBloodPressure()));

        if (healthData.getElderId() == null) {
            throw new RuntimeException("请选择老人");
        }
        if (healthData.getRecordTime() == null) {
            throw new RuntimeException("记录时间不能为空");
        }
        if (isBlank(healthData.getBloodPressure())) {
            throw new RuntimeException("血压不能为空");
        }
        if (healthData.getBloodGlucose() == null) {
            throw new RuntimeException("血糖不能为空");
        }
        if (healthData.getHeartRate() == null) {
            throw new RuntimeException("心率不能为空");
        }
        if (healthData.getSpo2() == null) {
            throw new RuntimeException("血氧不能为空");
        }
        if (healthData.getTemperature() == null) {
            throw new RuntimeException("体温不能为空");
        }
        if (healthData.getWeight() == null) {
            throw new RuntimeException("体重不能为空");
        }
    }

    private void trimQuery(HealthData healthData) {
        if (healthData == null) {
            return;
        }
        healthData.setElderName(trim(healthData.getElderName()));
    }

    private boolean isBlank(String value) {
        return value == null || "".equals(value.trim());
    }

    private String trim(String value) {
        return value == null ? null : value.trim();
    }
}
