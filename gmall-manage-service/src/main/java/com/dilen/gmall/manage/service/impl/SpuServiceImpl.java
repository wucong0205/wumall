package com.dilen.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dilen.gmall.bean.PmsProductImage;
import com.dilen.gmall.bean.PmsProductInfo;
import com.dilen.gmall.bean.PmsProductSaleAttr;
import com.dilen.gmall.bean.PmsProductSaleAttrValue;
import com.dilen.gmall.manage.mapper.PmsProductImageMapper;
import com.dilen.gmall.manage.mapper.PmsProductInfoMapper;
import com.dilen.gmall.manage.mapper.PmsProductSaleAttrMapper;
import com.dilen.gmall.manage.mapper.PmsProductSaleAttrValueMapper;
import com.dilen.gmall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wuc
 * @date 2019-12-10
 */
@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    private PmsProductInfoMapper pmsProductInfoMapper;
    @Autowired
    private PmsProductSaleAttrMapper pmsProductSaleAttrMapper;
    @Autowired
    private PmsProductImageMapper pmsProductImageMapper;
    @Autowired
    private PmsProductSaleAttrValueMapper pmsProductSaleAttrValueMapper;

    /**
     * 根据三级分类ID查询商品SPU信息
     * @param catalog3Id
     * @return
     */
    @Override
    public List<PmsProductInfo> getSpuList(String catalog3Id) {
        PmsProductInfo pmsProductInfo = new PmsProductInfo();
        pmsProductInfo.setCatalog3Id(catalog3Id);
        return pmsProductInfoMapper.select(pmsProductInfo);
    }

    /**
     * 保存Spu信息
     * @param pmsProductInfo
     * @return
     */
    @Override
    public String saveSpuInfo(PmsProductInfo pmsProductInfo) {

        //保存商品Spu
        pmsProductInfoMapper.insertSelective(pmsProductInfo);
        //保存商品Spu的销售属性
        List<PmsProductSaleAttr> spuSaleAttrList = pmsProductInfo.getSpuSaleAttrList();
        for (PmsProductSaleAttr pmsProductSaleAttr : spuSaleAttrList) {
            pmsProductSaleAttr.setProductId(pmsProductInfo.getId());
            pmsProductSaleAttrMapper.insertSelective(pmsProductSaleAttr);
            //保存销售属性值
            List<PmsProductSaleAttrValue> spuSaleAttrValueList = pmsProductSaleAttr.getSpuSaleAttrValueList();
            for (PmsProductSaleAttrValue pmsProductSaleAttrValue : spuSaleAttrValueList) {
                pmsProductSaleAttrValue.setProductId(pmsProductInfo.getId());
                pmsProductSaleAttrValueMapper.insertSelective(pmsProductSaleAttrValue);
            }
        }
        //保存商品Spu的图片信息
        List<PmsProductImage> spuImageList = pmsProductInfo.getSpuImageList();
        for (PmsProductImage pmsProductImage : spuImageList) {
            pmsProductImage.setProductId(pmsProductInfo.getId());
            pmsProductImageMapper.insertSelective(pmsProductImage);
        }
        return "success";
    }

    /**
     * 根据spuId查询销售属性信息
     * @param spuId
     * @return
     */
    @Override
    public List<PmsProductSaleAttr> getSpuSaleAttrList(String spuId) {

        PmsProductSaleAttr pmsProductSaleAttr = new PmsProductSaleAttr();
        pmsProductSaleAttr.setProductId(spuId);
        List<PmsProductSaleAttr> pmsProductSaleAttrs = pmsProductSaleAttrMapper.select(pmsProductSaleAttr);
        //封装Spu销售属性值
        for (PmsProductSaleAttr productSaleAttr : pmsProductSaleAttrs) {
            PmsProductSaleAttrValue pmsProductSaleAttrValue = new PmsProductSaleAttrValue();
            pmsProductSaleAttrValue.setProductId(spuId);
            pmsProductSaleAttrValue.setSaleAttrId(productSaleAttr.getSaleAttrId());
            List<PmsProductSaleAttrValue> productSaleAttrValues = pmsProductSaleAttrValueMapper.select(pmsProductSaleAttrValue);
            productSaleAttr.setSpuSaleAttrValueList(productSaleAttrValues);
        }

        return pmsProductSaleAttrs;
    }

    /**
     * 根据spuId查询spu图片信息
     * @param spuId
     * @return
     */
    @Override
    public List<PmsProductImage> getSpuImageList(String spuId) {
        PmsProductImage pmsProductImage = new PmsProductImage();
        pmsProductImage.setProductId(spuId);
        List<PmsProductImage> pmsProductImages = pmsProductImageMapper.select(pmsProductImage);
        return pmsProductImages;
    }

    /**
     * 根据productId查询销售spu销售属性
     *
     * @param productId
     * @return
     */
    @Override
    public List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(String productId, String skuId) {
//        PmsProductSaleAttr pmsProductSaleAttr = new PmsProductSaleAttr();
//        pmsProductSaleAttr.setProductId(productId);
//        List<PmsProductSaleAttr> pmsProductSaleAttrs = pmsProductSaleAttrMapper.select(pmsProductSaleAttr);
//        for (PmsProductSaleAttr productSaleAttr : pmsProductSaleAttrs) {
//            PmsProductSaleAttrValue pmsProductSaleAttrValue = new PmsProductSaleAttrValue();
//            pmsProductSaleAttrValue.setSaleAttrId(productSaleAttr.getSaleAttrId());
//            pmsProductSaleAttrValue.setProductId(productId);
//            List<PmsProductSaleAttrValue> pmsProductSaleAttrValues = pmsProductSaleAttrValueMapper.select(pmsProductSaleAttrValue);
//            productSaleAttr.setSpuSaleAttrValueList(pmsProductSaleAttrValues);
//        }
//        return pmsProductSaleAttrs;

        List<PmsProductSaleAttr> pmsProductSaleAttrs = pmsProductSaleAttrMapper.selectSpuSaleAttrListCheckBySku(productId, skuId);
        return pmsProductSaleAttrs;
    }
}
