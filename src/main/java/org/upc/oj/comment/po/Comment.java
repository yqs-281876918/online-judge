package org.upc.oj.comment.po;

import lombok.Data;

@Data
public class Comment {
    private Integer id;
    private String username;
    private Integer qid;
    private String content;
    private Long commentAt;
}
