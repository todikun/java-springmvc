package com.darknight.webmvc.models;

import com.darknight.webmvc.entities.FakultasEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class FakultasModel {
    private String id;
    private String code;
    private String name;
    private String alamat;

    public FakultasModel() {

    }

    public FakultasModel (FakultasEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }

}
