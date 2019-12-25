package com.dilen.gmall.bean;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * 商品Spu销售属性值表
 */
public class PmsProductSaleAttrValue implements Serializable {

    @Id
    @Column
    String id ;

    @Column
    String productId;           //商品id

    @Column
    String saleAttrId;          //销售属性ID--对应平台销售属性中的ID即PmsBaseSaleAttr的ID

    @Column
    String saleAttrValueName;   //销售属性值名称(冗余字段)

    @Transient
    String isChecked;           //是否选中

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSaleAttrId() {
        return saleAttrId;
    }

    public void setSaleAttrId(String saleAttrId) {
        this.saleAttrId = saleAttrId;
    }

    public String getSaleAttrValueName() {
        return saleAttrValueName;
    }

    public void setSaleAttrValueName(String saleAttrValueName) {
        this.saleAttrValueName = saleAttrValueName;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }
}
