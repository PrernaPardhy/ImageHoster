package com.upgrad.ImageHoster.service;

import com.upgrad.ImageHoster.common.CommentManager;
import com.upgrad.ImageHoster.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
private CommentManager commentManager;

    public CommentServiceImpl() {
        commentManager = new CommentManager();
    }

    @Override
    public void saveComment(Comment comment) {
        commentManager.saveComment(comment);
    }

    @Override
    public List getComments(int id) {
        return commentManager.getComments(id);
    }
}
