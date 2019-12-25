package com.dilen.gmall.service;

import com.dilen.gmall.bean.PmsBaseAttrInfo;
import com.dilen.gmall.bean.PmsBaseAttrValue;
import com.dilen.gmall.bean.PmsBaseSaleAttr;

import java.util.List;

/**
 * @author wuc
 * @date 2019-12-09
 */
public interface AttrInfoService {

    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);

    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    List<PmsBaseAttrValue> getAttrValueList(String attrId);

    /**
     * 获取基本销售属性
     * @return
     */
    List<PmsBaseSaleAttr> getBaseSaleAttrList();
}
