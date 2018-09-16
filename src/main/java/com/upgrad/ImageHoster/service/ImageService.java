package com.upgrad.ImageHoster.service;

import com.upgrad.ImageHoster.model.Comment;
import com.upgrad.ImageHoster.model.Image;

import java.util.List;

public interface ImageService{
    List<Image> getAll();
    List<Image> getByTag(String tagName);
    Image getByTitle(String title);
    Image getById(int id);
    Image getByTitleWithJoin(String title);
    Image  getByIdWithJoin(int id);
    void deleteByTitle(Image image);
    void save(Image image);
  //  void saveComment(Comment comment);
  //  List getComments(int id);

    void update(Image image);
}