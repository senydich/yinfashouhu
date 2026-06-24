package com.dtdx.service;

import com.dtdx.dao.FallRecordDao;
import com.dtdx.entity.FallRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FallRecordService {
    @Autowired
    private FallRecordDao fallRecordDao;

    public List<FallRecord> fallRecordList(FallRecord fallRecord) {
        return fallRecordDao.fallRecordList(fallRecord);
    }
}
