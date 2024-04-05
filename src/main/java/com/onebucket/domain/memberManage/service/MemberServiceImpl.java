package com.onebucket.domain.memberManage.service;


import com.onebucket.domain.memberManage.dao.MemberRepository;
import com.onebucket.domain.memberManage.domain.Member;
import com.onebucket.domain.memberManage.dto.CreateMemberRequestDto;
import com.onebucket.domain.memberManage.dto.ReadMemberResponseDto;
import com.onebucket.domain.memberManage.dto.UpdateMemberRequestDto;
import com.onebucket.global.utils.EntityUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.onebucket.global.utils.EntityUtils.updateIfNotNull;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createMember(CreateMemberRequestDto createRequestDto) {
        Member member = Member.builder()
                .username(createRequestDto.getUsername())
                .password(passwordEncoder.encode(createRequestDto.getPassword()))
                .nickName(createRequestDto.getNickName())
                .build();

        memberRepository.save(member);
    }

    @Override
    public ReadMemberResponseDto readMember(String username) {
        Member member = memberRepository.findByUsername(username)
                .orElse(Member.builder().build());

        return ReadMemberResponseDto.builder()
                .username(member.getUsername())
                .nickName(member.getNickName())
                .build();
    }

    @Override
    public void updateMember(String username, UpdateMemberRequestDto updateRequestDto) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("user not found"));

        updateIfNotNull(updateRequestDto.getNickName(), member::setNickName);
        memberRepository.save(member);
    }

    @Override
    public void deleteMember(String username){
        memberRepository.deleteByUsername(username);
    }
}
