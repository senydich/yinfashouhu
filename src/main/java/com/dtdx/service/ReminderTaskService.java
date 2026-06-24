package com.dtdx.service;

import com.dtdx.dao.ReminderTaskDao;
import com.dtdx.entity.ReminderTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReminderTaskService {
    @Autowired
    private ReminderTaskDao reminderTaskDao;

    public List<ReminderTask> reminderTaskList(ReminderTask reminderTask) {
        return reminderTaskDao.reminderTaskList(reminderTask);
    }

    public ReminderTask getById(Integer id) {
        if (id == null) {
            throw new RuntimeException("提醒任务ID不能为空");
        }
        ReminderTask reminderTask = reminderTaskDao.getById(id);
        if (reminderTask == null) {
            throw new RuntimeException("提醒任务不存在");
        }
        return reminderTask;
    }

    public void add(ReminderTask reminderTask) {
        prepareAndValidate(reminderTask);
        reminderTaskDao.add(reminderTask);
    }

    public void update(ReminderTask reminderTask) {
        if (reminderTask.getId() == null) {
            throw new RuntimeException("提醒任务ID不能为空");
        }
        getById(reminderTask.getId());
        prepareAndValidate(reminderTask);
        reminderTaskDao.update(reminderTask);
    }

    public void delete(Integer id) {
        getById(id);
        reminderTaskDao.delete(id);
    }

    private void prepareAndValidate(ReminderTask reminderTask) {
        if (reminderTask == null) {
            throw new RuntimeException("提醒任务不能为空");
        }
        if (reminderTask.getElderId() == null) {
            throw new RuntimeException("请选择老人");
        }
        reminderTask.setTaskTitle(trim(reminderTask.getTaskTitle()));
        reminderTask.setTaskType(trim(reminderTask.getTaskType()));
        reminderTask.setRepeatRule(trim(reminderTask.getRepeatRule()));
        reminderTask.setStatus(trim(reminderTask.getStatus()));
        reminderTask.setRemark(trim(reminderTask.getRemark()));
        if (isBlank(reminderTask.getTaskTitle())) {
            throw new RuntimeException("任务标题不能为空");
        }
        if (isBlank(reminderTask.getTaskType())) {
            throw new RuntimeException("任务类型不能为空");
        }
        if (reminderTask.getRemindTime() == null) {
            throw new RuntimeException("提醒时间不能为空");
        }
        if (isBlank(reminderTask.getRepeatRule())) {
            reminderTask.setRepeatRule("ONCE");
        }
        if (isBlank(reminderTask.getStatus())) {
            reminderTask.setStatus("PENDING");
        }
    }

    private boolean isBlank(String value) {
        return value == null || "".equals(value.trim());
    }

    private String trim(String value) {
        return value == null ? null : value.trim();
    }
}
