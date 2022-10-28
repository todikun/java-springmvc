package com.darknight.webmvc.services;

import com.darknight.webmvc.entities.GedungEntity;
import com.darknight.webmvc.entities.RuangEntity;
import com.darknight.webmvc.models.RuangModel;
import com.darknight.webmvc.repos.RuangRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RuangServiceImpl implements RuangService {

    private RuangRepo repo;

    @Autowired
    public RuangServiceImpl(RuangRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<RuangModel> getAll() {
        return this.repo.findAll().stream().map(RuangModel::new).collect(Collectors.toList());
    }

    @Override
    public RuangModel getById(String id) {
        return this.repo.findById(id).map(RuangModel::new).orElse(new RuangModel());
    }

    @Override
    public Optional<RuangModel> save(RuangModel request) {
        if (request == null) {
            return Optional.empty();
        }

        RuangEntity result = new RuangEntity(request);
        try {
            this.repo.save(result);
            return Optional.of(new RuangModel(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<RuangModel> update(String id, RuangModel request) {
        Optional<RuangEntity> result = this.repo.findById(id);
        if (result.isEmpty()) {
            return Optional.empty();
        }

        RuangEntity data = result.get();
        BeanUtils.copyProperties(request, data);
        GedungEntity gedung = new GedungEntity(request.getGedung().getId());
        data.setId(id);
        data.setGedung(gedung);
        data.setUpdatedAt(LocalDateTime.now());

        try {
            this.repo.save(data);
            return Optional.of(new RuangModel(data));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<RuangModel> delete(String id) {
        Optional<RuangEntity> result = this.repo.findById(id);
        if (result.isEmpty()) {
            return Optional.empty();
        }
        try {
            RuangEntity data = result.get();
            this.repo.delete(data);
            return Optional.of(new RuangModel(data));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
