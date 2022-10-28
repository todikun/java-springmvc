package com.darknight.webmvc.services;


import com.darknight.webmvc.models.MahasiswaModel;

import java.util.List;
import java.util.Optional;

public interface MahasiswaService {
    public List<MahasiswaModel> getAll();
    public MahasiswaModel getById(String id);
    public Optional<MahasiswaModel> save(MahasiswaModel request);
    public Optional<MahasiswaModel> update(String id, MahasiswaModel request);
    public Optional<MahasiswaModel> delete(String id);
}
