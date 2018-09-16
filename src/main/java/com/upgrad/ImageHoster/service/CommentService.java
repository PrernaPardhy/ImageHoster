package com.upgrad.ImageHoster.service;

import com.upgrad.ImageHoster.model.Comment;

import java.util.List;

public interface CommentService {

    void saveComment(Comment comment);
    List getComments(int id);

}
