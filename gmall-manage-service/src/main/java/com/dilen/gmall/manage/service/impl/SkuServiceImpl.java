package com.dilen.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.dilen.gmall.bean.PmsSkuAttrValue;
import com.dilen.gmall.bean.PmsSkuImage;
import com.dilen.gmall.bean.PmsSkuInfo;
import com.dilen.gmall.bean.PmsSkuSaleAttrValue;
import com.dilen.gmall.manage.mapper.PmsSkuAttrValueMapper;
import com.dilen.gmall.manage.mapper.PmsSkuImageMapper;
import com.dilen.gmall.manage.mapper.PmsSkuInfoMapper;
import com.dilen.gmall.manage.mapper.PmsSkuSaleAttrValueMapper;
import com.dilen.gmall.service.SkuService;
import com.dilen.gmall.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.UUID;

/**
 * @author wuc
 * @date 2019-12-15
 */
@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    private PmsSkuInfoMapper pmsSkuInfoMapper;
    @Autowired
    private PmsSkuImageMapper pmsSkuImageMapper;
    @Autowired
    private PmsSkuAttrValueMapper pmsSkuAttrValueMapper;
    @Autowired
    private PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 保存Sku信息
     * @param pmsSkuInfo
     * @return
     */
    @Override
    public String saveSkuInfo(PmsSkuInfo pmsSkuInfo) {

        //保存商品Sku
        pmsSkuInfoMapper.insertSelective(pmsSkuInfo);

        //保存Sku图片信息
        List<PmsSkuImage> skuImageList = pmsSkuInfo.getSkuImageList();
        for (PmsSkuImage pmsSkuImage : skuImageList) {
            pmsSkuImage.setSkuId(pmsSkuInfo.getId());
            pmsSkuImageMapper.insertSelective(pmsSkuImage);
        }

        // 插入平台属性关联
        List<PmsSkuAttrValue> skuAttrValueList = pmsSkuInfo.getSkuAttrValueList();
        for (PmsSkuAttrValue pmsSkuAttrValue : skuAttrValueList) {
            pmsSkuAttrValue.setSkuId(pmsSkuInfo.getId());
            pmsSkuAttrValueMapper.insertSelective(pmsSkuAttrValue);
        }

        // 插入销售属性关联
        List<PmsSkuSaleAttrValue> skuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
            pmsSkuSaleAttrValue.setSkuId(pmsSkuInfo.getId());
            pmsSkuSaleAttrValueMapper.insertSelective(pmsSkuSaleAttrValue);
        }

        return "success";
    }

    /**
     * 根据skuId查询商品sku信息：从数据库中查询
     *
     * @param skuId
     * @return
     */
    public PmsSkuInfo getBySkuIdFromDb(String skuId) {
        //获取sku信息
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        pmsSkuInfo.setId(skuId);
        PmsSkuInfo skuInfo = pmsSkuInfoMapper.selectOne(pmsSkuInfo);
        //获取sku图片信息
        PmsSkuImage pmsSkuImage = new PmsSkuImage();
        pmsSkuImage.setSkuId(skuId);
        List<PmsSkuImage> pmsSkuImages = pmsSkuImageMapper.select(pmsSkuImage);
        skuInfo.setSkuImageList(pmsSkuImages);
        return skuInfo;
    }

    /**
     * 根据skuId查询商品sku信息：从redis缓存中查询
     *
     * @param skuId
     * @return
     */
    @Override
    public PmsSkuInfo getBySkuId(String skuId) {
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        Jedis jedis = null;
        try {
            //链接缓存
            jedis = redisUtil.getJedis();
            //查询缓存
            String skuKey = "sku:"+skuId+":info";
            String skuLockKey = "sku:" + skuId + ":lock";
            String skuJson = jedis.get(skuKey);
            if(StringUtils.isNotBlank(skuJson)){
                pmsSkuInfo = JSON.parseObject(skuJson, PmsSkuInfo.class);
            } else {
                //为每个抢到分布式锁的线程分配一个唯一的value值,防止线程的分布式锁被其他线程删除
                String token = UUID.randomUUID().toString();
                //设置分布式锁    拿到锁的线程有10秒的过期时间
                String resultMsg = jedis.set(skuLockKey, token, "nx", "px", 10*1000);
                if(StringUtils.isNotBlank(resultMsg) && resultMsg.equals("OK")){
                    //设置分布式锁成功,有权在10s的过期时间内访问数据库
                    pmsSkuInfo = getBySkuIdFromDb(skuId);
                    if(pmsSkuInfo != null){
                        jedis.set(skuKey, JSON.toJSONString(pmsSkuInfo));
                    } else {
                        // 数据库中不存在该sku  为了防止缓存穿透将，将null或者空字符串值设置给redis
                        jedis.setex(skuKey, 60*3, JSON.toJSONString(""));
                    }
                    //在访问mysql后,将分布式锁释放
                    String lockToken = jedis.get(skuLockKey);
                    //用token确认删除的是自己的sku的分布式锁
                    //此时还有一种例外情况发生----代码执行到if判断时正准备删除自己的锁,此时skuLockKey失效,另外一个请求刚好拿到分布式锁,
                    //此时执行jedis.del(skuLockKey)这行代码时就把别人的锁给删掉了
                    //解决方案：将133行和134行代码作为原子操作就可避免这种问题：jedis.eval("lua")使用lua脚本在查询到key的同时删除该key，防止高并发下的意外情况
                    if(StringUtils.isNotBlank(lockToken) && lockToken.equals(token)){
                        jedis.del(skuLockKey);
                    }
                } else {
                    //设置分布式锁失败,自旋(该线程在睡眠几秒后,重新尝试访问本方法)
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return getBySkuId(skuId);
                }
            }
        } finally {
            jedis.close();
        }
        return pmsSkuInfo;
    }

    /**
     * 查询当前sku的spu的其他sku的集合的hash表
     * @param productId     商品Id
     * @return
     */
    @Override
    public List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId) {
        List<PmsSkuInfo> pmsSkuInfos = pmsSkuInfoMapper.selectSkuSaleAttrValueListBySpu(productId);
        return pmsSkuInfos;
    }

}
