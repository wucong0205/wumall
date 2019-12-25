package com.dilen.gmall.member.controller;

import com.dilen.gmall.bean.UmsMember;
import com.dilen.gmall.bean.UmsMemberReceiveAddress;
import com.dilen.gmall.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author wuc
 * @date 2019-12-06
 */
@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * 查询所有用户信息
     * @return
     */
    @RequestMapping("/getAllMembers")
    @ResponseBody
    public List<UmsMember> getAllMembers(){
        return memberService.getAllMembers();
    }

    /**
     * 根据用户ID删除用户信息
     */
    @RequestMapping("/deleteByMemberId")
    public void deleteByMemberId(String memberId){
        memberService.deleteByMemberId(memberId);
    }

    /**
     * 根据用户ID查询用户收货地址信息
     * @param memberId
     * @return
     */
    @RequestMapping("/getReceiveAddressByMemberId")
    @ResponseBody
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId){
        return memberService.getReceiveAddressByMemberId(memberId);
    }

}
