package com.dilen.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dilen.gmall.bean.PmsBaseAttrInfo;
import com.dilen.gmall.bean.PmsBaseAttrValue;
import com.dilen.gmall.bean.PmsBaseSaleAttr;
import com.dilen.gmall.manage.mapper.AttrInfoMapper;
import com.dilen.gmall.manage.mapper.AttrValueMapper;
import com.dilen.gmall.manage.mapper.BaseSaleAttrMapper;
import com.dilen.gmall.service.AttrInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author wuc
 * @date 2019-12-09
 */
@Service
public class AttrInfoServiceImpl implements AttrInfoService {

    @Autowired
    private AttrInfoMapper attrInfoMapper;
    @Autowired
    private AttrValueMapper attrValueMapper;
    @Autowired
    private BaseSaleAttrMapper baseSaleAttrMapper;

    @Override
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setCatalog3Id(catalog3Id);
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = attrInfoMapper.select(pmsBaseAttrInfo);
        for (PmsBaseAttrInfo baseAttrInfo : pmsBaseAttrInfos) {
            PmsBaseAttrValue baseAttrValue = new PmsBaseAttrValue();
            baseAttrValue.setAttrId(baseAttrInfo.getId());
            List<PmsBaseAttrValue> baseAttrValues = attrValueMapper.select(baseAttrValue);
            baseAttrInfo.setAttrValueList(baseAttrValues);
        }
        return pmsBaseAttrInfos;
    }

    @Override
    public String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {
        String msg = "success";

        if(StringUtils.isBlank(pmsBaseAttrInfo.getId())){
            //保存
            //1.保存属性
            attrInfoMapper.insertSelective(pmsBaseAttrInfo);//insert将null插入数据库 insertSelective不将null插入数据库
            //2.保存属性值
            for (int i = 0; i < pmsBaseAttrInfo.getAttrValueList().size(); i++) {
                PmsBaseAttrValue pmsBaseAttrValue = pmsBaseAttrInfo.getAttrValueList().get(i);
                pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                attrValueMapper.insertSelective(pmsBaseAttrValue);
            }
        } else {
            //修改
            //1.属性修改
            Example example = new Example(PmsBaseAttrInfo.class);
            example.createCriteria().andEqualTo("id", pmsBaseAttrInfo.getId());
            attrInfoMapper.updateByExampleSelective(pmsBaseAttrInfo, example);
            //2.属性值修改
                //2.1按照属性id删除所有属性值
            PmsBaseAttrValue pmsBaseAttrValueDel = new PmsBaseAttrValue();
            pmsBaseAttrValueDel.setAttrId(pmsBaseAttrInfo.getId());
            attrValueMapper.delete(pmsBaseAttrValueDel);
                //2.2删除后,将新的属性值插入
            List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                attrValueMapper.insertSelective(pmsBaseAttrValue);
            }
        }

        return msg;
    }

    @Override
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
        PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
        pmsBaseAttrValue.setAttrId(attrId);
        return attrValueMapper.select(pmsBaseAttrValue);
    }

    /**
     * 获取基本销售属性信息
     * @return
     */
    @Override
    public List<PmsBaseSaleAttr> getBaseSaleAttrList() {
        return baseSaleAttrMapper.selectAll();
    }
}
