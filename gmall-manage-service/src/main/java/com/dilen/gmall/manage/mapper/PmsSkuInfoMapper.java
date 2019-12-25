package com.dilen.gmall.manage.mapper;

import com.dilen.gmall.bean.PmsSkuInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author wuc
 * @date 2019-12-15
 */
public interface PmsSkuInfoMapper extends Mapper<PmsSkuInfo> {

    /**
     * 查询当前sku的spu的其他sku的集合的hash表
     * @param productId     商品Id
     * @return
     */
    List<PmsSkuInfo> selectSkuSaleAttrValueListBySpu(String productId);
}
