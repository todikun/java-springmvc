package com.darknight.webmvc.repos;

import com.darknight.webmvc.entities.GedungEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GedungRepo extends JpaRepository<GedungEntity, String> {
}
