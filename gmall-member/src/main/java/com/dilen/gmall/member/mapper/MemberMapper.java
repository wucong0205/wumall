package com.dilen.gmall.member.mapper;

import com.dilen.gmall.member.bean.UmsMember;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author wuc
 * @date 2019-12-06
 */
public interface MemberMapper extends Mapper<UmsMember> {

    List<UmsMember> selectAllMembers();
}
