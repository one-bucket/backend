package com.onebucket.domain.memberManage.service;

import com.onebucket.domain.memberManage.dto.BasicMemberDto;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;


/**
 * interface of MemberServiceImpl/or else. <br>
 * provide <b>CRUD</b> of Member table, for USER. <br>
 * <p>
 *     Has method create, read, update, delete.
 * </p>
 */
public interface MemberService {

    void createMember(BasicMemberDto basicMemberDto) throws Exception;

    BasicMemberDto readMember(String username);

    void updateMember(BasicMemberDto basicMemberDto) throws Exception;

    void deleteMember(String username) throws Exception;
}
