package com.dilen.gmall.member.service.impl;

import com.dilen.gmall.member.bean.UmsMember;
import com.dilen.gmall.member.bean.UmsMemberReceiveAddress;
import com.dilen.gmall.member.mapper.MemberMapper;
import com.dilen.gmall.member.mapper.UmsMemberReceiveAddressMapper;
import com.dilen.gmall.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
