package com.dtdx.controller;

import com.dtdx.entity.DataDictionary;
import com.dtdx.service.DataDictionaryService;
import com.dtdx.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/dictionary")
public class DataDictionaryController {

    @Autowired
    private DataDictionaryService dataDictionaryService;

    @Autowired
    private OperationLogService operationLogService;

    @RequestMapping("/dictionaryList")
    public String dictionaryList(Model model, DataDictionary dataDictionary) {
        List<DataDictionary> dictionaryList = dataDictionaryService.dictionaryList(dataDictionary);
        model.addAttribute("dictionaryList", dictionaryList);
        return "pages/data-dictionary-info";
    }

    @RequestMapping("/addView")
    public String addView(Model model) {
        DataDictionary dataDictionary = new DataDictionary();
        dataDictionary.setStatus(1);
        dataDictionary.setSortNo(0);
        model.addAttribute("dataDictionary", dataDictionary);
        model.addAttribute("formAction", "/dictionary/add");
        return "pages/data-dictionary-form";
    }

    @RequestMapping("/add")
    public String add(DataDictionary dataDictionary, Model model, HttpServletRequest request) {
        try {
            dataDictionaryService.add(dataDictionary);
            operationLogService.record("dictionary", "add", "add dictionary: " + dataDictionary.getDictType() + "." + dataDictionary.getDictCode(), request);
            return "redirect:/dictionary/dictionaryList";
        } catch (RuntimeException e) {
            model.addAttribute("dataDictionary", dataDictionary);
            model.addAttribute("formAction", "/dictionary/add");
            model.addAttribute("errMsg", e.getMessage());
            return "pages/data-dictionary-form";
        }
    }

    @RequestMapping("/updateView")
    public String updateView(@RequestParam("id") Integer id, Model model) {
        DataDictionary dataDictionary = dataDictionaryService.getById(id);
        model.addAttribute("dataDictionary", dataDictionary);
        model.addAttribute("formAction", "/dictionary/update");
        return "pages/data-dictionary-form";
    }

    @RequestMapping("/update")
    public String update(DataDictionary dataDictionary, Model model, HttpServletRequest request) {
        try {
            dataDictionaryService.update(dataDictionary);
            operationLogService.record("dictionary", "update", "update dictionary: " + dataDictionary.getDictType() + "." + dataDictionary.getDictCode(), request);
            return "redirect:/dictionary/dictionaryList";
        } catch (RuntimeException e) {
            model.addAttribute("dataDictionary", dataDictionary);
            model.addAttribute("formAction", "/dictionary/update");
            model.addAttribute("errMsg", e.getMessage());
            return "pages/data-dictionary-form";
        }
    }

    @RequestMapping("/del")
    public String del(@RequestParam("id") Integer id, HttpServletRequest request) {
        DataDictionary dataDictionary = dataDictionaryService.getById(id);
        dataDictionaryService.delete(id);
        operationLogService.record("dictionary", "delete", "delete dictionary: " + dataDictionary.getDictType() + "." + dataDictionary.getDictCode(), request);
        return "redirect:/dictionary/dictionaryList";
    }
}
