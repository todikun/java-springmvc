package com.darknight.webmvc.controllers;

import com.darknight.webmvc.models.FakultasModel;
import com.darknight.webmvc.services.FakultasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/fakultas")
public class FakultasController {

    private FakultasService service;

    @Autowired
    public FakultasController(FakultasService service) {
        this.service = service;
    }

    @GetMapping
    public ModelAndView index(Model model) {
        model.addAttribute("activePage", "fakultas");
        model.addAttribute("title", "Fakultas");

        ModelAndView view = new ModelAndView("pages/fakultas/index.html");
        List<FakultasModel> result = service.getAll();
        view.addObject("dataList", result);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(Model model) {
        model.addAttribute("activePage", "fakultas");
        model.addAttribute("title", "Fakultas");

        return new ModelAndView("pages/fakultas/add.html");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute FakultasModel request) {
        this.service.save(request);
        return new ModelAndView("redirect:/fakultas");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id, Model model) {
        FakultasModel fakultas = this.service.getById(id);
        if (fakultas == null) {
            return new ModelAndView("redirect:/fakultas");
        }

        model.addAttribute("activePage", "fakultas");
        model.addAttribute("title", "Fakultas");

        ModelAndView view = new ModelAndView("pages/fakultas/edit.html");
        view.addObject("data", fakultas);
        return view;
    }

    @PostMapping("/update")
    private ModelAndView update(@ModelAttribute FakultasModel request) {
        this.service.update(request.getId(), request);
        return new ModelAndView("redirect:/fakultas");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id, Model model) {
        FakultasModel fakultas = service.getById(id);
        if (fakultas == null) {
            return new ModelAndView("redirect:/fakultas");
        }
        model.addAttribute("activePage", "fakultas");
        model.addAttribute("title", "Fakultas");

        ModelAndView view = new ModelAndView("pages/fakultas/detail.html");
        view.addObject("data", fakultas);
        return view;
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute FakultasModel request) {
        FakultasModel fakultas = service.getById(request.getId());
        if (fakultas == null) {
            return new ModelAndView("redirect:/fakultas");
        }

        this.service.delete(request.getId());
        return new ModelAndView("redirect:/fakultas");
    }
}
