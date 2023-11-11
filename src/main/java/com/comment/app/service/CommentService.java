package com.comment.app.service;

import com.comment.app.entity.Comment;
import com.comment.app.entity.User;
import com.comment.app.entity.UserInteraction;
import com.comment.app.exception.NoSuchUserException;
import com.comment.app.repository.CommentRepository;
import com.comment.app.repository.UserInteractionRepository;
import com.comment.app.repository.UserRepository;
import com.comment.app.request.CommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserInteractionRepository userInteractionRepository;

    public String addComment(CommentRequest commentRequest) {

        User fromUser = getUser(commentRequest.getCommentFrom());
        User toUser = getUser(commentRequest.getCommentTo());
        if(fromUser == null) {
            fromUser = createAndSaveUser(commentRequest.getCommentFrom());
        }
        if(toUser == null) {
           toUser = createAndSaveUser(commentRequest.getCommentTo());
        }


        storeUsersInteractions(fromUser, toUser);

        storeUserComment(fromUser.getId(), commentRequest.getComment());


        return "comment added successfully";
    }

    public List<String> getComment(String commentTo) throws NoSuchUserException {

        List<String> comments = new ArrayList<>();

        User commenter = getUserWhoCommented(commentTo);

        if(commenter == null) {
            throw new NoSuchUserException("no such User exception");
        }else {
            UserInteraction userInteraction = userInteractionRepository.findByUserId(commenter.getId());
            for (long userId : userInteraction.getCommentFromId()) {
                Optional<User> user = userRepository.findById(userId);
                for (Comment comment : commentRepository.findAllByCommentedByUserId(user.get().getId())) {
                    comments.add(comment.getMessage());
                }
            }
        }

        return comments;
    }

    private void storeUserComment(long id, String message) {
        Comment comment = new Comment();
        comment.setMessage(message);
        comment.setCommentedByUserId(id);

        commentRepository.save(comment);

        System.out.println("saved user Comment");
    }

    private void storeUsersInteractions(User fromUser, User toUser) {
        UserInteraction userInteraction = getUserInteraction(fromUser.getId());
        userInteraction.getCommentToId().add(toUser.getId());
        userInteractionRepository.save(userInteraction);

        userInteraction = getUserInteraction(toUser.getId());
        userInteraction.getCommentFromId().add(fromUser.getId());
        userInteractionRepository.save(userInteraction);

        System.out.println("saved user interaction");
    }

    private UserInteraction getUserInteraction(Long id) {
        UserInteraction userInteraction = userInteractionRepository.findByUserId(id);
        return userInteraction;
    }

    private User createAndSaveUser(String name) {
        User user = new User();
        user.setName(name);
        user = userRepository.save(user);

        UserInteraction userInteraction = new UserInteraction();
        userInteraction.setUserId(user.getId());
        userInteraction.setCommentToId(new ArrayList<>());
        userInteraction.setCommentFromId(new ArrayList<>());
        userInteractionRepository.save(userInteraction);

        return user;
    }

    private User getUserWhoCommented(String name) {
        User user = userRepository.findByName(name);

        return user;
    }

    private User getUser(String commenterName) {
        User user = userRepository.findByName(commenterName);

        return user;
    }
}
