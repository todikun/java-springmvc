package com.darknight.webmvc.controllers;

import com.darknight.webmvc.repos.FakultasRepo;
import com.darknight.webmvc.repos.JurusanRepo;
import com.darknight.webmvc.repos.MahasiswaRepo;
import com.darknight.webmvc.repos.MataKuliahRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class HomeController {

    private MahasiswaRepo mahasiswaRepo;
    private JurusanRepo jurusanRepo;
    private FakultasRepo fakultasRepo;
    private MataKuliahRepo mataKuliahRepo;

    @Autowired
    public HomeController(MahasiswaRepo mahasiswaRepo, JurusanRepo jurusanRepo, FakultasRepo fakultasRepo, MataKuliahRepo mataKuliahRepo) {
        this.mahasiswaRepo = mahasiswaRepo;
        this.jurusanRepo = jurusanRepo;
        this.fakultasRepo = fakultasRepo;
        this.mataKuliahRepo = mataKuliahRepo;
    }

    @GetMapping
    public ModelAndView index(Model model) {
        ModelAndView view = new ModelAndView("pages/index.html");

        // count data
        int mahasiswaCount = (int) mahasiswaRepo.count();
        int jurusanCount = (int) jurusanRepo.count();
        int fakultasCount = (int) fakultasRepo.count();
        int mataKuliahCount = (int) mataKuliahRepo.count();

        model.addAttribute("activePage", "dashboard");
        model.addAttribute("title", "Dashboard");

        view.addObject("mahasiswa", mahasiswaCount);
        view.addObject("jurusan", jurusanCount);
        view.addObject("fakultas", fakultasCount);
        view.addObject("matakuliah", mataKuliahCount);
        return view;
    }
}
