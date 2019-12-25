package com.dilen.gmall.service;

import com.dilen.gmall.bean.PmsSkuInfo;

import java.util.List;

/**
 * @author wuc
 * @date 2019-12-15
 */
public interface SkuService {

    /**
     * 保存Sku信息
     * @param pmsSkuInfo
     * @return
     */
    String saveSkuInfo(PmsSkuInfo pmsSkuInfo);

    /**
     * 根据skuId查询商品sku信息
     * @param skuId
     * @return
     */
    PmsSkuInfo getBySkuId(String skuId);

    /**
     * 查询当前sku的spu的其他sku的集合的hash表
     * @param productId     商品Id
     * @return
     */
    List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId);
}
