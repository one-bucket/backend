package com.onebucket.domain.memberManage.service;

import com.onebucket.domain.memberManage.dto.CreateMemberRequestDto;
import com.onebucket.domain.memberManage.dto.ReadMemberResponseDto;
import com.onebucket.domain.memberManage.dto.UpdateMemberRequestDto;


/**
 * interface of MemberServiceImpl/or else. <br>
 * provide <b>CRUD</b> of Member table, for USER. <br>
 * <p>
 *     Has method create, read, update, delete.
 * </p>
 */
public interface MemberService {

    void createMember(CreateMemberRequestDto CreateMemberRequestDto) throws Exception;

    ReadMemberResponseDto readMember(String username);

    void updateMember(String username, UpdateMemberRequestDto updateMemberRequestDto) throws Exception;

    void deleteMember(String username) throws Exception;
}
