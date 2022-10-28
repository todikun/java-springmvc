package com.darknight.webmvc.repos;

import com.darknight.webmvc.entities.MataKuliahEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MataKuliahRepo extends JpaRepository<MataKuliahEntity, String> {
}
