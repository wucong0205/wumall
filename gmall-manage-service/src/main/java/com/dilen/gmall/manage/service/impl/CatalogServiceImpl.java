package com.dilen.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dilen.gmall.bean.PmsBaseCatalog1;
import com.dilen.gmall.bean.PmsBaseCatalog2;
import com.dilen.gmall.bean.PmsBaseCatalog3;
import com.dilen.gmall.manage.mapper.Catalog1Mapper;
import com.dilen.gmall.manage.mapper.Catalog2Mapper;
import com.dilen.gmall.manage.mapper.Catalog3Mapper;
import com.dilen.gmall.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wuc
 * @date 2019-12-09
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private Catalog1Mapper catalog1Mapper;
    @Autowired
    private Catalog2Mapper catalog2Mapper;
    @Autowired
    private Catalog3Mapper catalog3Mapper;

    @Override
    public List<PmsBaseCatalog1> getCatalog1s() {
        return catalog1Mapper.selectAll();
    }

    @Override
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id) {
        PmsBaseCatalog2 pmsBaseCatalog2 = new PmsBaseCatalog2();
        pmsBaseCatalog2.setCatalog1Id(catalog1Id);
//        return catalog2Mapper.select(pmsBaseCatalog2);
        return catalog2Mapper.selectCatalog2(catalog1Id);
    }

    @Override
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id) {
        PmsBaseCatalog3 pmsBaseCatalog3 = new PmsBaseCatalog3();
        pmsBaseCatalog3.setCatalog2Id(catalog2Id);
        return catalog3Mapper.select(pmsBaseCatalog3);
    }

}
