package com.dilen.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dilen.gmall.bean.PmsBaseCatalog1;
import com.dilen.gmall.bean.PmsBaseCatalog2;
import com.dilen.gmall.bean.PmsBaseCatalog3;
import com.dilen.gmall.service.CatalogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author wuc
 * @date 2019-12-08
 */
@Controller
@CrossOrigin//跨域访问注解
public class CatalogController {

    @Reference
    private CatalogService catalogService;

    @RequestMapping("getCatalog1")
    @ResponseBody
    public List<PmsBaseCatalog1> getCatalog1() {
        List<PmsBaseCatalog1> catalog1s = catalogService.getCatalog1s();
        return catalog1s;
    }

    @RequestMapping("getCatalog2")
    @ResponseBody
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id) {
        List<PmsBaseCatalog2> catalog2s = catalogService.getCatalog2(catalog1Id);
        return catalog2s;
    }

    @RequestMapping("getCatalog3")
    @ResponseBody
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id) {
        List<PmsBaseCatalog3> catalog3s = catalogService.getCatalog3(catalog2Id);
        return catalog3s;
    }

}
