package com.onebucket.domain.memberManage.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * <p>
 *  The Member Entity, which saved in RDS. Includes <br> <b>username, password, and nickname.</b>  <br>
 * </p>

 * <p>
 *     annotated with <b>Builder, Getter, Setter</b> <br> <br>
 * </p>
 * <pre>
 *  {@code private Long id;}
 *  {@code private String  username;}
 *  {@code private String password;}
 *  {@code private String nickName;}
 *  {@code private List<String> roles;} - default = GUEST
 * </pre>
 */
@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickName;

    @ElementCollection(fetch=FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>(Arrays.asList("GUEST"));
}
