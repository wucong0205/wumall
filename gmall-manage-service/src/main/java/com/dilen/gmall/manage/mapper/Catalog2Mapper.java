package com.dilen.gmall.manage.mapper;

import com.dilen.gmall.bean.PmsBaseCatalog2;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author wuc
 * @date 2019-12-09
 */
public interface Catalog2Mapper extends Mapper<PmsBaseCatalog2> {

    List<PmsBaseCatalog2> selectCatalog2(String catalog1Id);

}
