package com.dtdx.service;

import com.dtdx.dao.VoiceTerminalDao;
import com.dtdx.entity.VoiceTerminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoiceTerminalService {
    @Autowired
    private VoiceTerminalDao voiceTerminalDao;

    public List<VoiceTerminal> voiceTerminalList(VoiceTerminal voiceTerminal) {
        return voiceTerminalDao.voiceTerminalList(voiceTerminal);
    }

    public VoiceTerminal getById(Integer id) {
        if (id == null) {
            throw new RuntimeException("语音终端ID不能为空");
        }
        VoiceTerminal voiceTerminal = voiceTerminalDao.getById(id);
        if (voiceTerminal == null) {
            throw new RuntimeException("语音终端不存在");
        }
        return voiceTerminal;
    }

    public void add(VoiceTerminal voiceTerminal) {
        prepareAndValidate(voiceTerminal);
        checkTerminalCodeUnique(voiceTerminal);
        voiceTerminalDao.add(voiceTerminal);
    }

    public void update(VoiceTerminal voiceTerminal) {
        if (voiceTerminal.getId() == null) {
            throw new RuntimeException("语音终端ID不能为空");
        }
        getById(voiceTerminal.getId());
        prepareAndValidate(voiceTerminal);
        checkTerminalCodeUnique(voiceTerminal);
        voiceTerminalDao.update(voiceTerminal);
    }

    public void delete(Integer id) {
        getById(id);
        voiceTerminalDao.delete(id);
    }

    private void prepareAndValidate(VoiceTerminal voiceTerminal) {
        if (voiceTerminal == null) {
            throw new RuntimeException("语音终端不能为空");
        }
        voiceTerminal.setTerminalCode(trim(voiceTerminal.getTerminalCode()));
        voiceTerminal.setTerminalName(trim(voiceTerminal.getTerminalName()));
        voiceTerminal.setInstallLocation(trim(voiceTerminal.getInstallLocation()));
        voiceTerminal.setDeviceIp(trim(voiceTerminal.getDeviceIp()));
        voiceTerminal.setWakeWord(trim(voiceTerminal.getWakeWord()));
        voiceTerminal.setFirmwareVersion(trim(voiceTerminal.getFirmwareVersion()));
        voiceTerminal.setStatus(trim(voiceTerminal.getStatus()));
        voiceTerminal.setRemark(trim(voiceTerminal.getRemark()));

        if (isBlank(voiceTerminal.getTerminalCode())) {
            throw new RuntimeException("终端编号不能为空");
        }
        if (isBlank(voiceTerminal.getTerminalName())) {
            throw new RuntimeException("终端名称不能为空");
        }
        if (isBlank(voiceTerminal.getInstallLocation())) {
            throw new RuntimeException("安装位置不能为空");
        }
        if (voiceTerminal.getVolume() == null) {
            voiceTerminal.setVolume(60);
        }
        if (voiceTerminal.getVolume() < 0 || voiceTerminal.getVolume() > 100) {
            throw new RuntimeException("音量范围必须在0到100之间");
        }
        if (isBlank(voiceTerminal.getStatus())) {
            voiceTerminal.setStatus("ONLINE");
        }
    }

    private void checkTerminalCodeUnique(VoiceTerminal voiceTerminal) {
        VoiceTerminal exists = voiceTerminalDao.getByTerminalCode(voiceTerminal.getTerminalCode());
        if (exists != null && (voiceTerminal.getId() == null || !voiceTerminal.getId().equals(exists.getId()))) {
            throw new RuntimeException("终端编号已存在");
        }
    }

    private boolean isBlank(String value) {
        return value == null || "".equals(value.trim());
    }

    private String trim(String value) {
        return value == null ? null : value.trim();
    }
}
