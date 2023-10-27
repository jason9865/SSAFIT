package com.ssafy.ws.controller;

import com.ssafy.ws.model.dto.Comment;
import com.ssafy.ws.model.dto.User;
import com.ssafy.ws.model.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/article/{articleId}/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> showCommentsById(@PathVariable int articleId, Model model){
        System.out.println("showCommentById 도착");
        List<Comment> commentList = commentService.getList(articleId);
        return new ResponseEntity<List<Comment>>(commentList,HttpStatus.OK);
    }

    @PostMapping("/write")
    public String writeComments(@PathVariable int articleId, Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttribute){

        String content = request.getParameter("content");
        int boardId = Integer.parseInt(request.getParameter("boardId"));
        User loginUser = (User)session.getAttribute("loginUser");

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUserSeq(loginUser.getUserSeq());
        comment.setArticleId(articleId);
        comment.setBoardId(boardId);

        commentService.writeComment(comment);

        System.out.println("등록 준비 완료");
        return "redirect:/article/{articleId}";
    }

    @GetMapping("/remove/{commentId}")
    public String removeComment(@PathVariable int articleId,@PathVariable int commentId) {
        commentService.removeComment(commentId);
        return "redirect:/article/{articleId}";
    }

}
