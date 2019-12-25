package com.dilen.gmall.bean;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 会员表
 */
public class UmsMember implements Serializable {

    @Id
    private String id;
    private String memberLevelId;
    private String username;        //用户名
    private String password;        //密码
    private String nickname;        //昵称
    private String phone;           //手机号码
    private int status;             //帐号启用状态    0->禁用；1->启用
    private Date createTime;        //注册时间
    private String icon;            //头像
    private int gender;             //性别
    private Date birthday;          //生日
    private String city;            //所做城市
    private String job;             //职业
    private String personalizedSignature;   //个性签名
    private int sourceType;         //用户来源
    private int integration;        //积分
    private int growth;             //成长值
    private int luckeyCount;        //剩余抽奖次数
    private int historyIntegration; //历史积分数量

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberLevelId() {
        return memberLevelId;
    }

    public void setMemberLevelId(String memberLevelId) {
        this.memberLevelId = memberLevelId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPersonalizedSignature() {
        return personalizedSignature;
    }

    public void setPersonalizedSignature(String personalizedSignature) {
        this.personalizedSignature = personalizedSignature;
    }

    public int getSourceType() {
        return sourceType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }

    public int getIntegration() {
        return integration;
    }

    public void setIntegration(int integration) {
        this.integration = integration;
    }

    public int getGrowth() {
        return growth;
    }

    public void setGrowth(int growth) {
        this.growth = growth;
    }

    public int getLuckeyCount() {
        return luckeyCount;
    }

    public void setLuckeyCount(int luckeyCount) {
        this.luckeyCount = luckeyCount;
    }

    public int getHistoryIntegration() {
        return historyIntegration;
    }

    public void setHistoryIntegration(int historyIntegration) {
        this.historyIntegration = historyIntegration;
    }
}
