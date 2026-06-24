package com.dtdx.service;

import com.dtdx.dao.EventRecordDao;
import com.dtdx.entity.EventRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventRecordService {
    @Autowired
    private EventRecordDao eventRecordDao;

    public List<EventRecord> eventRecordList(EventRecord eventRecord) {
        return eventRecordDao.eventRecordList(eventRecord);
    }
}
