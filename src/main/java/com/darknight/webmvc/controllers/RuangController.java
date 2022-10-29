package com.darknight.webmvc.controllers;

import com.darknight.webmvc.models.GedungModel;
import com.darknight.webmvc.models.JurusanModel;
import com.darknight.webmvc.models.RuangModel;
import com.darknight.webmvc.services.GedungService;
import com.darknight.webmvc.services.RuangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/ruang")
public class RuangController {

    private RuangService ruangService;
    private GedungService gedungService;

    @Autowired
    public RuangController(RuangService ruangService, GedungService gedungService) {
        this.ruangService = ruangService;
        this.gedungService = gedungService;
    }

    @GetMapping
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("ruang/index.html");
        List<RuangModel> ruang = ruangService.getAll();
        view.addObject("dataList", ruang);
        return view;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id) {
        RuangModel ruang = ruangService.getById(id);
        if (ruang == null) {
            return new ModelAndView("redirect:/ruang");
        }

        ModelAndView view = new ModelAndView("ruang/detail.html");
        view.addObject("data", ruang);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(@ModelAttribute RuangModel request) {
        ModelAndView view = new ModelAndView("ruang/add.html");
        List<GedungModel> gedung = gedungService.getAll();
        view.addObject("gedungList", gedung);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute RuangModel request) {
        this.ruangService.save(request);
        return new ModelAndView("redirect:/ruang");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id) {
        RuangModel ruang = ruangService.getById(id);
        if (ruang == null) {
            return new ModelAndView("redirect:/ruang");
        }

        List<GedungModel> gedung = gedungService.getAll();

        ModelAndView view = new ModelAndView("ruang/edit.html");
        view.addObject("data", ruang);
        view.addObject("gedungList", gedung);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute RuangModel request) {
        this.ruangService.update(request.getId(), request);
        return new ModelAndView("redirect:/ruang");
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute RuangModel request) {
        RuangModel ruang = ruangService.getById(request.getId());
        if (ruang == null) {
            return new ModelAndView("redirect:/ruang");
        }

        this.ruangService.delete(request.getId());
        return new ModelAndView("redirect:/ruang");
    }

}
