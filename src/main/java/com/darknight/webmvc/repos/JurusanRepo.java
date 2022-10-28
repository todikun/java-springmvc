package com.darknight.webmvc.repos;

import com.darknight.webmvc.entities.JurusanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JurusanRepo extends JpaRepository<JurusanEntity, String> {
}
