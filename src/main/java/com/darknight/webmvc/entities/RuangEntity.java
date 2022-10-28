package com.darknight.webmvc.entities;

import com.darknight.webmvc.models.RuangModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tabel_ruang")
public class RuangEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "kode_ruang", length = 20, unique = true)
    private String code;

    @Column(name = "nama_ruang", length = 225)
    private String name;

    @Column(name = "gedung_id", length = 36, insertable = false, updatable = false)
    private String gedungId;

    @Column(name = "lantai_ke")
    private Integer lantaiKe;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by", length = 20)
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", length = 20)
    private String updatedBy;

    // relationship
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gedung_id", nullable = false)
    private GedungEntity gedung;

    public RuangEntity() {

    }

    public RuangEntity(String id) {
        this.id = id;
    }

    public RuangEntity(String code, String name, Integer lantaiKe) {
        this.code = code;
        this.name = name;
        this.lantaiKe = lantaiKe;
    }

    public RuangEntity(RuangModel model) {
        BeanUtils.copyProperties(model, this);
        this.id = UUID.randomUUID().toString();
        this.code = model.getCode();
        this.name = model.getName();
        this.lantaiKe = model.getLantaiKe();

        if (model.getGedung() != null) {
            GedungEntity gedungEntity = new GedungEntity();
            gedungEntity.setId(model.getGedung().getId());
            this.gedung = gedungEntity;
        }

        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = "SYSTEM";
    }

    @PrePersist
    public void onCreated() {
        this.id = UUID.randomUUID().toString();
    }

}
