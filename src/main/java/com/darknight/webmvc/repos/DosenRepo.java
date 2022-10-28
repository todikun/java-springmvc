package com.darknight.webmvc.repos;

import com.darknight.webmvc.entities.DosenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DosenRepo extends JpaRepository<DosenEntity, String> {
}
