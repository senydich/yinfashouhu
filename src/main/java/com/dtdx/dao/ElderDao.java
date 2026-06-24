package com.dtdx.dao;

import com.dtdx.entity.Elder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ElderDao {

    public List<Elder> elderList(Elder elder);

    public Elder getById(@Param("id") Integer id);

    public void add(Elder elder);

    public void update(Elder elder);

    public void delete(@Param("id") Integer id);
}
