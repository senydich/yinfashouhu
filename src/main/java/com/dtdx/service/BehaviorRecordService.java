package com.dtdx.service;

import com.dtdx.dao.BehaviorRecordDao;
import com.dtdx.entity.BehaviorRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BehaviorRecordService {

    @Autowired
    private BehaviorRecordDao behaviorRecordDao;

    public List<BehaviorRecord> behaviorRecordList(BehaviorRecord behaviorRecord) {
        return behaviorRecordDao.behaviorRecordList(behaviorRecord);
    }

    public BehaviorRecord getById(Integer id) {
        if (id == null) {
            throw new RuntimeException("行为记录ID不能为空");
        }
        BehaviorRecord record = behaviorRecordDao.getById(id);
        if (record == null) {
            throw new RuntimeException("行为记录不存在");
        }
        return record;
    }

    public List<BehaviorRecord> findByElderAndDate(Integer elderId, Date reportDate) {
        return behaviorRecordDao.findByElderAndDate(elderId, reportDate);
    }

    public void add(BehaviorRecord behaviorRecord) {
        prepareAndValidate(behaviorRecord);
        checkDuplicate(behaviorRecord);
        behaviorRecordDao.add(behaviorRecord);
    }

    public void update(BehaviorRecord behaviorRecord) {
        if (behaviorRecord.getId() == null) {
            throw new RuntimeException("行为记录ID不能为空");
        }
        getById(behaviorRecord.getId());
        prepareAndValidate(behaviorRecord);
        checkDuplicate(behaviorRecord);
        behaviorRecordDao.update(behaviorRecord);
    }

    public void delete(Integer id) {
        getById(id);
        behaviorRecordDao.delete(id);
    }

    private void prepareAndValidate(BehaviorRecord record) {
        if (record == null) {
            throw new RuntimeException("行为记录不能为空");
        }
        if (record.getElderId() == null) {
            throw new RuntimeException("请选择老人");
        }
        record.setBehaviorType(trim(record.getBehaviorType()));
        record.setBehaviorName(trim(record.getBehaviorName()));
        record.setLocation(trim(record.getLocation()));
        record.setRiskLevel(trim(record.getRiskLevel()));
        record.setSourceDevice(trim(record.getSourceDevice()));
        record.setRemark(trim(record.getRemark()));
        if (isBlank(record.getBehaviorType())) {
            throw new RuntimeException("行为类型不能为空");
        }
        if (isBlank(record.getBehaviorName())) {
            throw new RuntimeException("行为名称不能为空");
        }
        if (record.getStartTime() == null) {
            throw new RuntimeException("开始时间不能为空");
        }
        if (record.getDurationMinutes() == null) {
            record.setDurationMinutes(calcDuration(record));
        }
        if (record.getDurationMinutes() == null || record.getDurationMinutes() < 0) {
            record.setDurationMinutes(0);
        }
        if (isBlank(record.getRiskLevel())) {
            record.setRiskLevel("LOW");
        }
    }

    private Integer calcDuration(BehaviorRecord record) {
        if (record.getStartTime() != null && record.getEndTime() != null) {
            long millis = record.getEndTime().getTime() - record.getStartTime().getTime();
            return millis > 0 ? (int) (millis / 60000) : 0;
        }
        return 0;
    }

    private void checkDuplicate(BehaviorRecord record) {
        if (behaviorRecordDao.countDuplicate(record) > 0) {
            throw new RuntimeException("同一老人、同一时间、同一行为已存在，请勿重复录入");
        }
    }

    private boolean isBlank(String value) {
        return value == null || "".equals(value.trim());
    }

    private String trim(String value) {
        return value == null ? null : value.trim();
    }
}
