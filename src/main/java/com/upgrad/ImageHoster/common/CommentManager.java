package com.upgrad.ImageHoster.common;

import com.upgrad.ImageHoster.model.Comment;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CommentManager extends  SessionManager{

    /**
     * This method saves an image's comments to the database
     *
     * @param comment the comments of the Image who's data that we want to save to the database
     */


    public void saveComment(Comment comment){
        Session session=openSession();
        session.save(comment);
        commitSession(session);
    }


    /**
     * This method retrieves comments of an mage by a specific image id.
     *
     * @param id the id of the image that we want to retrieve.
     *
     * @return a list of comments objects that we retrieved by its image id.
     */

    public List<Comment> getComments(final int id){

        Session session = openSession();

        try {
            List<Comment> comments = session.createCriteria(Comment.class)
                    .add(Restrictions.eq("image.id",id))
                    .list();
            int i=0;
            while(i<comments.size()){
                Hibernate.initialize(comments.get(i).getUser());
                i++;
            }
            commitSession(session);
            return comments;
        } catch(HibernateException e) {
            System.out.println("unable to retrieve an comments from database by its id");
        }

        return null;
    }

}
