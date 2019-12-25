package com.dilen.gmall.bean;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @param
 * @return
 * sku销售属性值
 */
public class PmsSkuSaleAttrValue implements Serializable {

    @Id
    @Column
    String id;

    @Column
    String skuId;               //skuId

    @Column
    String saleAttrId;          //销售属性id--平台销售属性Id(pms_base_sale_attr的Id)

    @Column
    String saleAttrValueId;     //销售属性值id--spu销售属性值Id(pms_product_sale_attr_value的Id)

    @Column
    String saleAttrName;        //销售属性名称--销售属性名称(冗余--pms_base_sale_attr的name)

    @Column
    String saleAttrValueName;   //销售属性值名称--销售属性值名称(冗余--pms_product_sale_attr_value的sale_attr_value_name)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSaleAttrId() {
        return saleAttrId;
    }

    public void setSaleAttrId(String saleAttrId) {
        this.saleAttrId = saleAttrId;
    }

    public String getSaleAttrValueId() {
        return saleAttrValueId;
    }

    public void setSaleAttrValueId(String saleAttrValueId) {
        this.saleAttrValueId = saleAttrValueId;
    }

    public String getSaleAttrName() {
        return saleAttrName;
    }

    public void setSaleAttrName(String saleAttrName) {
        this.saleAttrName = saleAttrName;
    }

    public String getSaleAttrValueName() {
        return saleAttrValueName;
    }

    public void setSaleAttrValueName(String saleAttrValueName) {
        this.saleAttrValueName = saleAttrValueName;
    }
}
