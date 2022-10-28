package com.darknight.webmvc.services;


import com.darknight.webmvc.models.DosenModel;

import java.util.List;
import java.util.Optional;

public interface DosenService {
    public List<DosenModel> getAll();
    public DosenModel getById(String id);
    public Optional<DosenModel> save(DosenModel request);
    public Optional<DosenModel> update(String id, DosenModel request);
    public Optional<DosenModel> delete(String id);
}
