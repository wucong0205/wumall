package com.dilen.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dilen.gmall.bean.PmsBaseAttrInfo;
import com.dilen.gmall.bean.PmsBaseAttrValue;
import com.dilen.gmall.bean.PmsBaseSaleAttr;
import com.dilen.gmall.service.AttrInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author wuc
 * @date 2019-12-09
 */
@Controller
@CrossOrigin//解决跨域问题
public class AttrInfoController {

    @Reference
    private AttrInfoService attrInfoService;

    @RequestMapping("attrInfoList")
    @ResponseBody
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id){
        List<PmsBaseAttrInfo> attrInfos = attrInfoService.attrInfoList(catalog3Id);
        return attrInfos;
    }

    @RequestMapping("saveAttrInfo")
    @ResponseBody
    public String saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo){
        String message = attrInfoService.saveAttrInfo(pmsBaseAttrInfo);
        return message;
    }

    @RequestMapping("getAttrValueList")
    @ResponseBody
    public List<PmsBaseAttrValue> getAttrValueList(String attrId){
        List<PmsBaseAttrValue> pmsBaseAttrValues = attrInfoService.getAttrValueList(attrId);
        return pmsBaseAttrValues;
    }

    /**
     * 获取基本销售属性
     * @return      List<PmsBaseSaleAttr>
     */
    @RequestMapping("baseSaleAttrList")
    @ResponseBody
    public List<PmsBaseSaleAttr> baseSaleAttrList(){
        List<PmsBaseSaleAttr> pmsBaseSaleAttrs = attrInfoService.getBaseSaleAttrList();
        return pmsBaseSaleAttrs;
    }

}
