package com.upgrad.ImageHoster.controller;

import com.upgrad.ImageHoster.model.Comment;
import com.upgrad.ImageHoster.model.Image;
import com.upgrad.ImageHoster.model.User;
import com.upgrad.ImageHoster.service.CommentService;
import com.upgrad.ImageHoster.service.ImageService;
import com.upgrad.ImageHoster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private ImageService imageService;
    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;


    /**
     * This controller saves a specific comments to database
     * @param id the id of the image that we want to retrieve
     * @param model used to pass data to the view for render ing
     *
     * @return view for the image and comments
     */



    @RequestMapping(value="/image/{id}/comments/create",method=RequestMethod.POST)
    public String uploadComment(@PathVariable int id,
                                @RequestParam("comment") String comment, HttpSession session,
                                Model model){
        User currUser = (User) session.getAttribute("currUser");
        if(currUser==null){
            return "redirect:/";
        }else {
            Image image = imageService.getByIdWithJoin(id);
            Comment comments = new Comment(comment);
            comments.setImage(image);
            comments.setUser(currUser);

            comments.getCommentText();
            String name=comments.getUser().getUsername();
            String text=comments.getCommentText();

            commentService.saveComment(comments);

            User user=userService.getByName(currUser.getUsername());
            List<Comment> commentList=image.getCommentText();

            model.addAttribute("comments",comments);

            return "redirect:/comments/"+image.getId();
        }
    }


    /**
     * This controller shows a specific image and comments
     * @param id the id of the image that we want to retrieve
     * @param model used to pass data to the view for render ing
     *
     * @return view for the image and comments that was requested
     */

    @RequestMapping("/comments/{id}")
    public String showComment(@PathVariable int id,Model model){
        List<Comment> commentList= commentService.getComments(id);


        Image image = imageService.getByIdWithJoin(id);
        image.setNumView(image.getNumView() + 1);
        imageService.update(image);

        model.addAttribute("user", image.getUser());
        model.addAttribute("image", image);
        model.addAttribute("tags", image.getTags());

        model.addAttribute("comments",commentList);


        return "images/image";
    }
}
