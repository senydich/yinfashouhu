package com.dtdx.service;

import com.dtdx.dao.CheckupRecordDao;
import com.dtdx.entity.CheckupRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckupRecordService {
    @Autowired
    private CheckupRecordDao checkupRecordDao;

    public List<CheckupRecord> checkupRecordList(CheckupRecord checkupRecord) {
        return checkupRecordDao.checkupRecordList(checkupRecord);
    }

    public CheckupRecord getById(Integer id) {
        if (id == null) {
            throw new RuntimeException("体检记录ID不能为空");
        }
        CheckupRecord record = checkupRecordDao.getById(id);
        if (record == null) {
            throw new RuntimeException("体检记录不存在");
        }
        return record;
    }

    public void add(CheckupRecord checkupRecord) {
        prepareAndValidate(checkupRecord);
        checkupRecordDao.add(checkupRecord);
    }

    public void update(CheckupRecord checkupRecord) {
        if (checkupRecord.getId() == null) {
            throw new RuntimeException("体检记录ID不能为空");
        }
        getById(checkupRecord.getId());
        prepareAndValidate(checkupRecord);
        checkupRecordDao.update(checkupRecord);
    }

    public void delete(Integer id) {
        getById(id);
        checkupRecordDao.delete(id);
    }

    private void prepareAndValidate(CheckupRecord checkupRecord) {
        if (checkupRecord == null) {
            throw new RuntimeException("体检记录不能为空");
        }
        checkupRecord.setCheckupType(trim(checkupRecord.getCheckupType()));
        checkupRecord.setHospitalName(trim(checkupRecord.getHospitalName()));
        checkupRecord.setResultSummary(trim(checkupRecord.getResultSummary()));
        checkupRecord.setRiskLevel(trim(checkupRecord.getRiskLevel()));
        checkupRecord.setFollowUpSuggestion(trim(checkupRecord.getFollowUpSuggestion()));
        checkupRecord.setStatus(trim(checkupRecord.getStatus()));
        checkupRecord.setRemark(trim(checkupRecord.getRemark()));

        if (checkupRecord.getElderId() == null) {
            throw new RuntimeException("请选择老人");
        }
        if (checkupRecord.getCheckupDate() == null) {
            throw new RuntimeException("体检日期不能为空");
        }
        if (isBlank(checkupRecord.getCheckupType())) {
            throw new RuntimeException("体检类型不能为空");
        }
        if (isBlank(checkupRecord.getHospitalName())) {
            throw new RuntimeException("体检机构不能为空");
        }
        if (isBlank(checkupRecord.getResultSummary())) {
            throw new RuntimeException("结果摘要不能为空");
        }
        if (isBlank(checkupRecord.getRiskLevel())) {
            checkupRecord.setRiskLevel("LOW");
        }
        if (isBlank(checkupRecord.getStatus())) {
            checkupRecord.setStatus("NORMAL");
        }
    }

    private boolean isBlank(String value) {
        return value == null || "".equals(value.trim());
    }

    private String trim(String value) {
        return value == null ? null : value.trim();
    }
}
