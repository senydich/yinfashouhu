package com.dtdx.service;

import com.dtdx.dao.DialogRecordDao;
import com.dtdx.entity.DialogRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DialogRecordService {
    @Autowired
    private DialogRecordDao dialogRecordDao;

    public List<DialogRecord> dialogRecordList(DialogRecord dialogRecord) {
        return dialogRecordDao.dialogRecordList(dialogRecord);
    }
}
