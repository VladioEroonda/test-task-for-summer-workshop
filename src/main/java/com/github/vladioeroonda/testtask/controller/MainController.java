package com.github.vladioeroonda.testtask.controller;

import com.github.vladioeroonda.testtask.model.TargetPage;
import com.github.vladioeroonda.testtask.service.TargetPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MainController {
    @Value("${savingfile.name}")
    private String savingName;
    @Value("${savefolder.path}")
    private String folderPath;

    private final TargetPageService targetPageService;

    @Autowired
    public MainController(TargetPageService targetPageService) {
        this.targetPageService = targetPageService;
    }

    @GetMapping
    public String openHomePage(Model model){
        model.addAttribute("targetPage", new TargetPage());
        model.addAttribute("savingName", savingName);
        model.addAttribute("folderPath",folderPath);

        return "index";
    }

    @PostMapping
    public String returnResult(@Valid @ModelAttribute("targetPage") TargetPage targetPage,
                               BindingResult bindingResult, Model model){

        TargetPage result = targetPageService.getResult(targetPage.address);

        if(bindingResult.hasErrors()) {
            return "index";
        }
        model.addAttribute("savingName", savingName);
        model.addAttribute("folderPath",folderPath);
        model.addAttribute("targetPage", result);

        return "index";
    }
}
