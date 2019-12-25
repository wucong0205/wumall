package com.dilen.gmall.item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.dilen.gmall.bean.PmsProductSaleAttr;
import com.dilen.gmall.bean.PmsSkuInfo;
import com.dilen.gmall.bean.PmsSkuSaleAttrValue;
import com.dilen.gmall.service.SkuService;
import com.dilen.gmall.service.SpuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuc
 * @date 2019-12-12
 */
@Controller
@CrossOrigin
public class ItemController {

    @Reference
    private SkuService skuService;
    @Reference
    private SpuService spuService;

    @RequestMapping("{skuId}.html")
    public String item(@PathVariable String skuId, ModelMap map){
        //sku对象
        PmsSkuInfo pmsSkuInfo = skuService.getBySkuId(skuId);
        map.put("skuInfo", pmsSkuInfo);
        //销售属性列表
        List<PmsProductSaleAttr> pmsProductSaleAttrs = spuService.spuSaleAttrListCheckBySku(pmsSkuInfo.getProductId(), skuId);
        map.put("spuSaleAttrListCheckBySku", pmsProductSaleAttrs);

        //查询当前sku的spu的其他sku的集合的hash表  如：k:239|243	v:106
        Map<String, String> skuSaleAttrHash = new HashMap<>();
        List<PmsSkuInfo> pmsSkuInfos = skuService.getSkuSaleAttrValueListBySpu(pmsSkuInfo.getProductId());
        for (PmsSkuInfo skuInfo : pmsSkuInfos) {
            String k = "";
            String v = skuInfo.getId();
            List<PmsSkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
            for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
                k = k + pmsSkuSaleAttrValue.getId() + "|";  // "464|465"
            }
            skuSaleAttrHash.put(k,v);
        }

        // 将sku的销售属性hash表放到页面
        String skuSaleAttrHashJsonStr = JSON.toJSONString(skuSaleAttrHash);
        map.put("skuSaleAttrHashJsonStr",skuSaleAttrHashJsonStr);

        return "item";
    }

    @RequestMapping("index")
    public String index(ModelMap modelMap){
        modelMap.put("hello", "hello thymeleaf");
        return "index";
    }
     
    //test

}
