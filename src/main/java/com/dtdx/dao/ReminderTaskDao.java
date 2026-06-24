package com.dtdx.dao;

import com.dtdx.entity.ReminderTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReminderTaskDao {
    List<ReminderTask> reminderTaskList(ReminderTask reminderTask);

    ReminderTask getById(@Param("id") Integer id);

    void add(ReminderTask reminderTask);

    void update(ReminderTask reminderTask);

    void delete(@Param("id") Integer id);
}
