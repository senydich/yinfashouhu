package com.dtdx.dao;

import com.dtdx.entity.VoiceTerminal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VoiceTerminalDao {
    List<VoiceTerminal> voiceTerminalList(VoiceTerminal voiceTerminal);

    VoiceTerminal getById(@Param("id") Integer id);

    VoiceTerminal getByTerminalCode(@Param("terminalCode") String terminalCode);

    void add(VoiceTerminal voiceTerminal);

    void update(VoiceTerminal voiceTerminal);

    void delete(@Param("id") Integer id);
}
