package com.dilen.gmall.service;

import com.dilen.gmall.bean.PmsProductImage;
import com.dilen.gmall.bean.PmsProductInfo;
import com.dilen.gmall.bean.PmsProductSaleAttr;

import java.util.List;

/**
 * @author wuc
 * @date 2019-12-10
 */
public interface SpuService {

    /**
     * 根据三级分类ID查询商品SPU信息
     * @param catalog3Id
     * @return
     */
    List<PmsProductInfo> getSpuList(String catalog3Id);

    String saveSpuInfo(PmsProductInfo pmsProductInfo);

    /**
     * 根据spuId查询销售属性信息
     * @param spuId
     * @return
     */
    List<PmsProductSaleAttr> getSpuSaleAttrList(String spuId);

    /**
     * 根据spuId查询spu图片信息
     * @param spuId
     * @return
     */
    List<PmsProductImage> getSpuImageList(String spuId);

    /**
     * 根据productId查询销售spu销售属性
     * @param productId
     * @return
     */
    List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(String productId, String skuId);
}
