package com.darknight.webmvc.models;

import com.darknight.webmvc.entities.GedungEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class GedungModel {
    private String id;
    private String code;
    private String name;
    private Integer jmlLantai;

    public GedungModel() {

    }

    public GedungModel(GedungEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }

}
