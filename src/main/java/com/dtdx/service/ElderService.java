package com.dtdx.service;

import com.dtdx.dao.ElderDao;
import com.dtdx.entity.Elder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElderService {

    @Autowired
    private ElderDao elderDao;

    public List<Elder> elderList(Elder elder) {
        return elderDao.elderList(elder);
    }

    public void add(Elder elder) {
        validate(elder);
        elderDao.add(elder);
    }

    public void del(Integer id) {
        elderDao.delete(id);
    }

    public void update(Elder elder) {
        if (elder == null || elder.getId() == null) {
            throw new RuntimeException("Elder id is required.");
        }
        validate(elder);
        elderDao.update(elder);
    }

    public Elder getById(Integer id) {
        return elderDao.getById(id);
    }

    private void validate(Elder elder) {
        if (elder == null) {
            throw new RuntimeException("Elder information is required.");
        }
        elder.setName(trim(elder.getName()));
        elder.setGender(trim(elder.getGender()));
        elder.setHealthStatus(trim(elder.getHealthStatus()));
        elder.setEmergencyContact(trim(elder.getEmergencyContact()));
        elder.setRelation(trim(elder.getRelation()));
        elder.setContactNumber(trim(elder.getContactNumber()));

        if (isBlank(elder.getName())) {
            throw new RuntimeException("Name is required.");
        }
        if (elder.getBirthday() == null) {
            throw new RuntimeException("Please choose birthday.");
        }
        if (isBlank(elder.getGender())) {
            throw new RuntimeException("Gender is required.");
        }
        if (elder.getName().length() > 16) {
            throw new RuntimeException("Name cannot exceed 16 characters.");
        }
        if (elder.getHealthStatus() != null && elder.getHealthStatus().length() > 100) {
            throw new RuntimeException("Health status cannot exceed 100 characters.");
        }
        if (elder.getEmergencyContact() != null && elder.getEmergencyContact().length() > 16) {
            throw new RuntimeException("Emergency contact cannot exceed 16 characters.");
        }
        if (elder.getRelation() != null && elder.getRelation().length() > 10) {
            throw new RuntimeException("Relation cannot exceed 10 characters.");
        }
        if (elder.getContactNumber() != null && elder.getContactNumber().length() > 14) {
            throw new RuntimeException("Contact number cannot exceed 14 characters.");
        }
    }

    private boolean isBlank(String value) {
        return value == null || "".equals(value.trim());
    }

    private String trim(String value) {
        return value == null ? null : value.trim();
    }
}
