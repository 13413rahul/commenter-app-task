package com.comment.app.controller;

import com.comment.app.exception.NoSuchUserException;
import com.comment.app.request.CommentRequest;
import com.comment.app.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/addComment")
    public ResponseEntity<String> addComment(@RequestBody @Valid CommentRequest commentRequest) {
        String response = "";

        response = commentService.addComment(commentRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getComment")
    public ResponseEntity<List<String>> getComment(@RequestParam("commentTo") String commentTo) throws NoSuchUserException {
        List<String> response;

        response = commentService.getComment(commentTo);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
