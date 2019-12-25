package com.dilen.gmall.service;

import com.dilen.gmall.bean.UmsMember;
import com.dilen.gmall.bean.UmsMemberReceiveAddress;

import java.util.List;

/**
 * @author wuc
 * @date 2019-12-06
 */
public interface MemberService {

    /**
     * 查询所有用户信息
     * @return  List<UmsMember>
     */
    List<UmsMember> getAllMembers();

    /**
     * 根据用户ID查询用户收获地址信息
     * @param memberId      用户ID
     * @return              List<UmsMemberReceiveAddress>
     */
    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId);

    /**
     * 根据用户ID删除用户信息
     * @param memberId      用户ID
     */
    void deleteByMemberId(String memberId);
}
