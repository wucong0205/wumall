package com.dilen.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dilen.gmall.bean.PmsProductImage;
import com.dilen.gmall.bean.PmsProductInfo;
import com.dilen.gmall.bean.PmsProductSaleAttr;
import com.dilen.gmall.manage.util.PmsUploadUtil;
import com.dilen.gmall.service.SpuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author wuc
 * @date 2019-12-10
 */
@Controller
@CrossOrigin
public class SpuController {

    @Reference
    private SpuService spuService;

    /**
     * 根据三级分类ID查询商品SPU信息
     * @param catalog3Id
     * @return
     */
    @RequestMapping("spuList")
    @ResponseBody
    public List<PmsProductInfo> spuList(String catalog3Id){
        List<PmsProductInfo> pmsProductInfos = spuService.getSpuList(catalog3Id);
        return pmsProductInfos;
    }

    /**
     * 保存商品spu信息
     * @return
     */
    @RequestMapping("saveSpuInfo")
    @ResponseBody
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo){
        pmsProductInfo.setProductName(pmsProductInfo.getSpuName());
        String message = spuService.saveSpuInfo(pmsProductInfo);
        return message;
    }

    /**
     * 商品图片上传
     * @return
     */
    @RequestMapping("fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file")MultipartFile multipartFile){
        //1.将图片或音频视频上传到分布式文件存储服务器上fastdfs
        //2.将图片的存储路径返回给页面
        String imgUrl = PmsUploadUtil.uploadImg(multipartFile);
        System.out.println(imgUrl);
        return imgUrl;
    }

    //根据spuId查询spu销售属性信息
    @RequestMapping("spuSaleAttrList")
    @ResponseBody
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId){
        List<PmsProductSaleAttr> pmsProductSaleAttrs = spuService.getSpuSaleAttrList(spuId);
        return pmsProductSaleAttrs;
    }

    //根据spuId查询spu图片信息
    @RequestMapping("spuImageList")
    @ResponseBody
    public List<PmsProductImage> spuImageList(String spuId){
        List<PmsProductImage> pmsProductImages = spuService.getSpuImageList(spuId);
        return pmsProductImages;
    }


}
