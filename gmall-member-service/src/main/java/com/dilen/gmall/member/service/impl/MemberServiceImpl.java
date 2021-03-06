package com.dilen.gmall.member.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dilen.gmall.bean.UmsMember;
import com.dilen.gmall.bean.UmsMemberReceiveAddress;
import com.dilen.gmall.member.mapper.MemberMapper;
import com.dilen.gmall.member.mapper.UmsMemberReceiveAddressMapper;
import com.dilen.gmall.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wuc
 * @date 2019-12-06
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private UmsMemberReceiveAddressMapper umsMemberReceiveAddressMapper;

    @Override
    public List<UmsMember> getAllMembers() {
        return memberMapper.selectAllMembers();
    }

    @Override
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId) {
        UmsMemberReceiveAddress umsMemberReceiveAddress = new UmsMemberReceiveAddress();
        umsMemberReceiveAddress.setMemberId(memberId);
        return umsMemberReceiveAddressMapper.select(umsMemberReceiveAddress);
    }

    @Override
    public void deleteByMemberId(String memberId) {
        memberMapper.deleteByPrimaryKey(memberId);
    }
}
