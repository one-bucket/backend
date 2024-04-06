package com.onebucket.admin.memberManage.service;


import com.onebucket.admin.memberManage.dto.AdminCreateMemberRequestDto;
import com.onebucket.domain.memberManage.domain.Member;
import org.springframework.data.domain.Page;

public interface AdminMemberService {
    public Page<Member> getMemberByPage(int page, int size);

    public boolean deleteMember(int username);

    public Member createMemberWithRole(AdminCreateMemberRequestDto dto);

    public void updateMemberStatus();
}
