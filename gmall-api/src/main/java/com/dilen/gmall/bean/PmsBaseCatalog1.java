package com.dilen.gmall.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @param
 * @return
 * 一级分类表
 */
public class PmsBaseCatalog1 implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String name;    //分类名称

    @Transient
    private List<PmsBaseCatalog2> catalog2s;

    public List<PmsBaseCatalog2> getCatalog2s() {
        return catalog2s;
    }

    public void setCatalog2s(List<PmsBaseCatalog2> catalog2s) {
        this.catalog2s = catalog2s;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

