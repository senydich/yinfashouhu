package com.dtdx.dao;

import com.dtdx.entity.DataDictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DataDictionaryDao {
    List<DataDictionary> dictionaryList(DataDictionary dataDictionary);

    DataDictionary getById(@Param("id") Integer id);

    DataDictionary getByTypeAndCode(@Param("dictType") String dictType, @Param("dictCode") String dictCode);

    void add(DataDictionary dataDictionary);

    void update(DataDictionary dataDictionary);

    void delete(@Param("id") Integer id);
}
