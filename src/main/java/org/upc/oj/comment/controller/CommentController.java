package org.upc.oj.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.upc.oj.auth.interceptor.wrapper.AuthedHttpServletRequest;
import org.upc.oj.comment.po.Comment;
import org.upc.oj.comment.service.CommentService;

import java.util.List;
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/comment")
    public void insertComment(AuthedHttpServletRequest request,@RequestBody Comment comment){
        commentService.insertComment(request.getUsername(),comment.getQid(),comment.getContent());
    }
    @GetMapping("/comments")
    public List<Comment> getComments(Integer qid){
        return commentService.getComments(qid);
    }
}
