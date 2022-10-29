package com.darknight.webmvc.controllers;

import com.darknight.webmvc.models.MataKuliahModel;
import com.darknight.webmvc.services.MataKuliahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/matakuliah")
public class MataKuliahController {

    private MataKuliahService service;

    @Autowired
    public MataKuliahController(MataKuliahService service) {
        this.service = service;
    }

    @GetMapping
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("matakuliah/index.html");
        List<MataKuliahModel> mataKuliah = service.getAll();
        view.addObject("dataList", mataKuliah);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        return new ModelAndView("matakuliah/add.html");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute MataKuliahModel request) {
        this.service.save(request);
        return new ModelAndView("redirect:/matakuliah");
    }

}
