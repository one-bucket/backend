package com.onebucket.domain.boardManage.boards;

import com.onebucket.domain.boardManage.baseClass.BoardBase;
import com.onebucket.domain.boardManage.comments.CommentSales;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


public class BoardSales extends BoardBase<CommentSales> {

    private List<String> tags;

}
