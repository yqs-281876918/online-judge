package org.upc.oj.comment.dao;

import org.apache.ibatis.annotations.Mapper;
import org.upc.oj.comment.po.Comment;

import java.util.List;

@Mapper
public interface CommentMapper {
    void insertComment(String username,Integer qid,String content,Long commentAt);
    List<Comment> getComments(Integer qid);

}
