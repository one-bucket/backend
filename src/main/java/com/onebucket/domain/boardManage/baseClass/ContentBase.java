package com.onebucket.domain.boardManage.baseClass;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ContentBase {
    @Id
    private String id;

    private String content;
    private String authorId;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private boolean isModified;
    private int like;
    private int disLike;

    protected ContentBase() {
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    public void setUpdateTime() {
        this.updateAt = LocalDateTime.now();
    }
}
