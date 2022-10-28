package com.darknight.webmvc.services;

import com.darknight.webmvc.entities.FakultasEntity;
import com.darknight.webmvc.models.FakultasModel;
import com.darknight.webmvc.repos.FakultasRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FakultasServiceImpl implements FakultasService{

    private FakultasRepo repo;

    @Autowired
    public FakultasServiceImpl(FakultasRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<FakultasModel> getAll() {
        return this.repo.findAll().stream().map(FakultasModel::new).collect(Collectors.toList());
    }

    @Override
    public FakultasModel getById(String id) {
        return this.repo.findById(id).map(FakultasModel::new).orElse(new FakultasModel());
    }

    @Override
    public Optional<FakultasModel> save(FakultasModel request) {
        if (request == null) {
            return Optional.empty();
        }

        FakultasEntity result = new FakultasEntity(request);
        try {
            this.repo.save(result);
            return Optional.of(new FakultasModel(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<FakultasModel> update(String id, FakultasModel request) {
        Optional<FakultasEntity> result = this.repo.findById(id);
        if (result.isEmpty()) {
            return Optional.empty();
        }

        FakultasEntity data = result.get();
        BeanUtils.copyProperties(request, data);
        data.setId(id);
        data.setUpdatedAt(LocalDateTime.now());
        try {
            this.repo.save(data);
            return Optional.of(new FakultasModel(data));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<FakultasModel> delete(String id) {
        Optional<FakultasEntity> result = this.repo.findById(id);
        if (result.isEmpty()) {
            return Optional.empty();
        }

        try {
            FakultasEntity data = result.get();
            this.repo.delete(data);
            return Optional.of(new FakultasModel(data));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
