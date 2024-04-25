package com.onebucket.domain.boardManage.baseClass;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class BoardBase<C extends CommentBase> extends ContentBase{

    private String title;

    private List<C> comments;


}
