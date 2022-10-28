package com.darknight.webmvc.controllers;

import com.darknight.webmvc.models.JurusanModel;
import com.darknight.webmvc.models.MahasiswaModel;
import com.darknight.webmvc.services.JurusanService;
import com.darknight.webmvc.services.MahasiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/mahasiswa")
public class MahasiswaController {

    private MahasiswaService mahasiswaService;
    private JurusanService jurusanService;

    @Autowired
    public MahasiswaController(MahasiswaService mahasiswaService, JurusanService jurusanService) {
        this.mahasiswaService = mahasiswaService;
        this.jurusanService = jurusanService;
    }

    @GetMapping
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("mahasiswa/index.html");
        List<MahasiswaModel> result = mahasiswaService.getAll();
        view.addObject("dataList", result);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("mahasiswa/add.html");
        List<JurusanModel> result = jurusanService.getAll();
        view.addObject("jurusanList", result);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute MahasiswaModel request) {
        this.mahasiswaService.save(request);
        return new ModelAndView("redirect:/mahasiswa");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id) {
        MahasiswaModel mahasiswa = mahasiswaService.getById(id);
        if (mahasiswa == null) {
            return new ModelAndView("redirect:/mahasiswa");
        }

        List<JurusanModel> jurusan = jurusanService.getAll();

        ModelAndView view = new ModelAndView("mahasiswa/edit.html");
        view.addObject("data", mahasiswa);
        view.addObject("jurusanList", jurusan);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute MahasiswaModel request) {
        this.mahasiswaService.update(request.getId(), request);
        return new ModelAndView("redirect:/mahasiswa");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id) {
        MahasiswaModel mahasiswa = mahasiswaService.getById(id);
        if (mahasiswa == null) {
            return new ModelAndView("redirect:/mahasiswa");
        }

        ModelAndView view = new ModelAndView("mahasiswa/detail.html");
        view.addObject("data", mahasiswa);
        return view;
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute MahasiswaModel request) {
        MahasiswaModel mahasiswa = mahasiswaService.getById(request.getId());
        if (mahasiswa == null) {
            return new ModelAndView("redirect:/mahasiswa");
        }

        this.mahasiswaService.delete(request.getId());
        return new ModelAndView("redirect:/mahasiswa");
    }
}
