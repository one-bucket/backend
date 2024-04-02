package com.onebucket.domain.memberManage.service;


import com.onebucket.domain.memberManage.dao.MemberRepository;
import com.onebucket.domain.memberManage.domain.Member;
import com.onebucket.domain.memberManage.dto.BasicMemberDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    @Override
    public void createMember(BasicMemberDto basicMemberDto) throws Exception {
        Member member = Member.builder()
                .username(basicMemberDto.getUsername())
                .password(basicMemberDto.getPassword())
                .nickName(basicMemberDto.getNickName())
                .build();

        memberRepository.save(member);
    }

    @Override
    public BasicMemberDto readMember(String username) {
        Member member = memberRepository.findByUsername(username)
                .orElse(Member.builder()
                        .username("NULL")
                        .password("NULL")
                        .nickName("NULL")
                        .build());

        return BasicMemberDto.builder()
                .username(member.getUsername())
                .password("********")
                .nickName(member.getNickName())
                .build();
    }

    @Override
    public void updateMember(BasicMemberDto basicMemberDto) throws Exception {
        Member member = memberRepository.findByUsername(basicMemberDto.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("user not found"));

        member.setNickName(basicMemberDto.getNickName());
        memberRepository.save(member);
    }

    @Override
    public void deleteMember(String username) throws Exception {
        memberRepository.deleteByUsername(username);
    }
}
