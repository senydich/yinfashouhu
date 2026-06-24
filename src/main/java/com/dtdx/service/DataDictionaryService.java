package com.dtdx.service;

import com.dtdx.dao.DataDictionaryDao;
import com.dtdx.entity.DataDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataDictionaryService {

    @Autowired
    private DataDictionaryDao dataDictionaryDao;

    public List<DataDictionary> dictionaryList(DataDictionary dataDictionary) {
        return dataDictionaryDao.dictionaryList(dataDictionary);
    }

    public DataDictionary getById(Integer id) {
        if (id == null) {
            throw new RuntimeException("dictionary id is required");
        }
        DataDictionary dataDictionary = dataDictionaryDao.getById(id);
        if (dataDictionary == null) {
            throw new RuntimeException("dictionary item not found");
        }
        return dataDictionary;
    }

    public void add(DataDictionary dataDictionary) {
        prepareAndValidate(dataDictionary);
        DataDictionary exist = dataDictionaryDao.getByTypeAndCode(dataDictionary.getDictType(), dataDictionary.getDictCode());
        if (exist != null) {
            throw new RuntimeException("dictionary type and code already exist");
        }
        dataDictionaryDao.add(dataDictionary);
    }

    public void update(DataDictionary dataDictionary) {
        if (dataDictionary.getId() == null) {
            throw new RuntimeException("dictionary id is required");
        }
        DataDictionary old = dataDictionaryDao.getById(dataDictionary.getId());
        if (old == null) {
            throw new RuntimeException("dictionary item not found");
        }
        prepareAndValidate(dataDictionary);
        DataDictionary exist = dataDictionaryDao.getByTypeAndCode(dataDictionary.getDictType(), dataDictionary.getDictCode());
        if (exist != null && !exist.getId().equals(dataDictionary.getId())) {
            throw new RuntimeException("dictionary type and code already exist");
        }
        dataDictionaryDao.update(dataDictionary);
    }

    public void delete(Integer id) {
        DataDictionary dataDictionary = getById(id);
        dataDictionaryDao.delete(dataDictionary.getId());
    }

    private void prepareAndValidate(DataDictionary dataDictionary) {
        if (dataDictionary == null) {
            throw new RuntimeException("dictionary item is required");
        }
        dataDictionary.setDictType(trim(dataDictionary.getDictType()));
        dataDictionary.setDictCode(trim(dataDictionary.getDictCode()));
        dataDictionary.setDictName(trim(dataDictionary.getDictName()));
        dataDictionary.setDictValue(trim(dataDictionary.getDictValue()));
        dataDictionary.setRemark(trim(dataDictionary.getRemark()));

        if (isBlank(dataDictionary.getDictType())) {
            throw new RuntimeException("dictionary type is required");
        }
        if (isBlank(dataDictionary.getDictCode())) {
            throw new RuntimeException("dictionary code is required");
        }
        if (isBlank(dataDictionary.getDictName())) {
            throw new RuntimeException("dictionary name is required");
        }
        if (dataDictionary.getSortNo() == null) {
            dataDictionary.setSortNo(0);
        }
        if (dataDictionary.getStatus() == null) {
            dataDictionary.setStatus(1);
        }
    }

    private boolean isBlank(String value) {
        return value == null || "".equals(value.trim());
    }

    private String trim(String value) {
        return value == null ? null : value.trim();
    }
}
