package com.dtdx.service;

import com.dtdx.dao.AccompanyRecordDao;
import com.dtdx.entity.AccompanyRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccompanyRecordService {
    @Autowired
    private AccompanyRecordDao accompanyRecordDao;

    public List<AccompanyRecord> accompanyRecordList(AccompanyRecord accompanyRecord) {
        return accompanyRecordDao.accompanyRecordList(accompanyRecord);
    }

    public AccompanyRecord getById(Integer id) {
        if (id == null) {
            throw new RuntimeException("陪伴记录ID不能为空");
        }
        AccompanyRecord accompanyRecord = accompanyRecordDao.getById(id);
        if (accompanyRecord == null) {
            throw new RuntimeException("陪伴记录不存在");
        }
        return accompanyRecord;
    }

    public void add(AccompanyRecord accompanyRecord) {
        prepareAndValidate(accompanyRecord);
        accompanyRecordDao.add(accompanyRecord);
    }

    public void update(AccompanyRecord accompanyRecord) {
        if (accompanyRecord.getId() == null) {
            throw new RuntimeException("陪伴记录ID不能为空");
        }
        getById(accompanyRecord.getId());
        prepareAndValidate(accompanyRecord);
        accompanyRecordDao.update(accompanyRecord);
    }

    public void delete(Integer id) {
        getById(id);
        accompanyRecordDao.delete(id);
    }

    private void prepareAndValidate(AccompanyRecord accompanyRecord) {
        if (accompanyRecord == null) {
            throw new RuntimeException("陪伴记录不能为空");
        }
        if (accompanyRecord.getElderId() == null) {
            throw new RuntimeException("请选择老人");
        }
        accompanyRecord.setAccompanyType(trim(accompanyRecord.getAccompanyType()));
        accompanyRecord.setCompanion(trim(accompanyRecord.getCompanion()));
        accompanyRecord.setContent(trim(accompanyRecord.getContent()));
        accompanyRecord.setEmotionResult(trim(accompanyRecord.getEmotionResult()));
        accompanyRecord.setRemark(trim(accompanyRecord.getRemark()));
        if (isBlank(accompanyRecord.getAccompanyType())) {
            throw new RuntimeException("陪伴类型不能为空");
        }
        if (accompanyRecord.getStartTime() == null) {
            throw new RuntimeException("开始时间不能为空");
        }
        if (isBlank(accompanyRecord.getCompanion())) {
            throw new RuntimeException("陪伴人员不能为空");
        }
        if (isBlank(accompanyRecord.getContent())) {
            throw new RuntimeException("陪伴内容不能为空");
        }
        if (accompanyRecord.getDurationMinutes() == null) {
            accompanyRecord.setDurationMinutes(calcDuration(accompanyRecord));
        }
        if (accompanyRecord.getDurationMinutes() == null || accompanyRecord.getDurationMinutes() < 0) {
            accompanyRecord.setDurationMinutes(0);
        }
        if (isBlank(accompanyRecord.getEmotionResult())) {
            accompanyRecord.setEmotionResult("STABLE");
        }
    }

    private Integer calcDuration(AccompanyRecord accompanyRecord) {
        if (accompanyRecord.getStartTime() != null && accompanyRecord.getEndTime() != null) {
            long millis = accompanyRecord.getEndTime().getTime() - accompanyRecord.getStartTime().getTime();
            return millis > 0 ? (int) (millis / 60000) : 0;
        }
        return 0;
    }

    private boolean isBlank(String value) {
        return value == null || "".equals(value.trim());
    }

    private String trim(String value) {
        return value == null ? null : value.trim();
    }
}
