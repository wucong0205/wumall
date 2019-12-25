package com.dilen.gmall.service;

import com.dilen.gmall.bean.PmsBaseCatalog1;
import com.dilen.gmall.bean.PmsBaseCatalog2;
import com.dilen.gmall.bean.PmsBaseCatalog3;

import java.util.List;

/**
 * @author wuc
 * @date 2019-12-09
 */
public interface CatalogService {

    List<PmsBaseCatalog1> getCatalog1s();

    List<PmsBaseCatalog2> getCatalog2(String catalog1Id);

    List<PmsBaseCatalog3> getCatalog3(String catalog2Id);
}
