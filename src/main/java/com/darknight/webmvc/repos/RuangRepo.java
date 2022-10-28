package com.darknight.webmvc.repos;

import com.darknight.webmvc.entities.RuangEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuangRepo extends JpaRepository<RuangEntity, String> {
}
