package com.onebucket.global.auth.jwtAuth.utils;

import com.onebucket.domain.memberManage.dao.MemberRepository;
import com.onebucket.domain.memberManage.domain.Member;
import com.onebucket.global.auth.jwtAuth.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * CustomUserDetailsService implements UserDetailsService. <br>
 * load User entity by username saved in {@link com.onebucket.domain.memberManage.domain.Member Member.class} <br>
 * Method return {@link com.onebucket.global.auth.jwtAuth.user.CustomUserDetails CustomUserDetails.class}
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("no user."));

        return new CustomUserDetails(member);
    }
}
