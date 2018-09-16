package com.upgrad.ImageHoster.service;

import com.upgrad.ImageHoster.common.ImageManager;
import com.upgrad.ImageHoster.model.Comment;
import com.upgrad.ImageHoster.model.Image;
import com.upgrad.ImageHoster.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    private ImageManager imageManager;

    public ImageServiceImpl() {
        imageManager = new ImageManager();
    }

    @Override
    public List<Image> getAll() { return imageManager.getAllImages(); }

    @Override
    public List<Image> getByTag(String tagName) { return imageManager.getImagesByTag(tagName); }

    @Override
    public Image getByTitle(String title) {
       return imageManager.getImageByTitle(title);
    }

    @Override
    public Image getById(int id) {
        return imageManager.getImageById(id);
    }

    @Override
    public Image getByTitleWithJoin(String title) { return imageManager.getImageByTitleWithJoins(title); }

    @Override
    public Image getByIdWithJoin(int id) { return imageManager.getImageByIdWithJoins(id); }



    @Override
    public void deleteByTitle(Image image) {
        imageManager.deleteImage(image.getId());
    }

    @Override
    public void save(Image image) { imageManager.saveImage(image); }


    @Override
    public void update(Image newImage) { imageManager.updateImage(newImage); }
}
