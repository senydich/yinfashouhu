package com.dtdx.controller;

import com.dtdx.entity.Elder;
import com.dtdx.entity.VoiceTerminal;
import com.dtdx.service.ElderService;
import com.dtdx.service.OperationLogService;
import com.dtdx.service.VoiceTerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/record")
public class VoiceTerminalController {
    @Autowired
    private VoiceTerminalService voiceTerminalService;

    @Autowired
    private ElderService elderService;

    @Autowired
    private OperationLogService operationLogService;

    @RequestMapping("/voiceTerminalList")
    public String voiceTerminalList(Model model, VoiceTerminal voiceTerminal) {
        model.addAttribute("voiceTerminalList", voiceTerminalService.voiceTerminalList(voiceTerminal));
        model.addAttribute("query", voiceTerminal);
        return "pages/voice-terminal-info";
    }

    @RequestMapping("/voiceTerminalAddView")
    public String voiceTerminalAddView(Model model) {
        VoiceTerminal voiceTerminal = new VoiceTerminal();
        voiceTerminal.setStatus("ONLINE");
        voiceTerminal.setVolume(60);
        model.addAttribute("voiceTerminal", voiceTerminal);
        model.addAttribute("elderList", elderService.elderList(new Elder()));
        model.addAttribute("formAction", "/record/voiceTerminalAdd");
        return "pages/voice-terminal-form";
    }

    @RequestMapping("/voiceTerminalAdd")
    public String voiceTerminalAdd(VoiceTerminal voiceTerminal, Model model, HttpServletRequest request) {
        try {
            voiceTerminalService.add(voiceTerminal);
            operationLogService.record("voice terminal", "add", "add voice terminal: " + voiceTerminal.getTerminalCode(), request);
            return "redirect:/record/voiceTerminalList";
        } catch (RuntimeException e) {
            model.addAttribute("voiceTerminal", voiceTerminal);
            model.addAttribute("elderList", elderService.elderList(new Elder()));
            model.addAttribute("formAction", "/record/voiceTerminalAdd");
            model.addAttribute("errMsg", e.getMessage());
            return "pages/voice-terminal-form";
        }
    }

    @RequestMapping("/voiceTerminalUpdateView")
    public String voiceTerminalUpdateView(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("voiceTerminal", voiceTerminalService.getById(id));
        model.addAttribute("elderList", elderService.elderList(new Elder()));
        model.addAttribute("formAction", "/record/voiceTerminalUpdate");
        return "pages/voice-terminal-form";
    }

    @RequestMapping("/voiceTerminalUpdate")
    public String voiceTerminalUpdate(VoiceTerminal voiceTerminal, Model model, HttpServletRequest request) {
        try {
            voiceTerminalService.update(voiceTerminal);
            operationLogService.record("voice terminal", "update", "update voice terminal: " + voiceTerminal.getTerminalCode(), request);
            return "redirect:/record/voiceTerminalList";
        } catch (RuntimeException e) {
            model.addAttribute("voiceTerminal", voiceTerminal);
            model.addAttribute("elderList", elderService.elderList(new Elder()));
            model.addAttribute("formAction", "/record/voiceTerminalUpdate");
            model.addAttribute("errMsg", e.getMessage());
            return "pages/voice-terminal-form";
        }
    }

    @RequestMapping("/voiceTerminalDel")
    public String voiceTerminalDel(@RequestParam("id") Integer id, HttpServletRequest request) {
        VoiceTerminal voiceTerminal = voiceTerminalService.getById(id);
        voiceTerminalService.delete(id);
        operationLogService.record("voice terminal", "delete", "delete voice terminal: " + voiceTerminal.getTerminalCode(), request);
        return "redirect:/record/voiceTerminalList";
    }
}
