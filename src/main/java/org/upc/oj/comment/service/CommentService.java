package org.upc.oj.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upc.oj.comment.dao.CommentMapper;
import org.upc.oj.comment.po.Comment;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    public void insertComment(String username,Integer qid,String content){
        commentMapper.insertComment(username,qid,content,new Date().getTime());
    }
    public List<Comment> getComments(Integer qid){
        return commentMapper.getComments(qid);
    }
}
