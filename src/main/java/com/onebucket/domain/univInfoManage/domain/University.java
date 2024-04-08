package com.onebucket.domain.univInfoManage.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@Builder
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class University {

    @Id
    @Column(name="id", updatable = false, nullable = false)
    private Long id;

    private String name;

    private String address;

    private String emailForm;

}
