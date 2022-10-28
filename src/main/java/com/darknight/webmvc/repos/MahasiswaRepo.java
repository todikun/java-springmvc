package com.darknight.webmvc.repos;

import com.darknight.webmvc.entities.MahasiswaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MahasiswaRepo extends JpaRepository<MahasiswaEntity, String> {
}
