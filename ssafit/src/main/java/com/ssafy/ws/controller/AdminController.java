package com.ssafy.ws.controller;

import com.ssafy.ws.model.dto.Article;
import com.ssafy.ws.model.dto.Board;
import com.ssafy.ws.model.dto.User;
import com.ssafy.ws.model.service.ArticleService;
import com.ssafy.ws.model.service.BoardService;
import com.ssafy.ws.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final ArticleService articleService;
    private final BoardService boardService;

    @Autowired
    public AdminController(UserService userService, ArticleService articleService, BoardService boardService ){
        this.userService = userService;
        this.articleService = articleService;
        this.boardService = boardService;
    }

    @GetMapping("/board")
    public String adminBoard(Model model) {
        List<Board> boardList = boardService.getList();
        model.addAttribute("boardList",boardList);
        return "/admin/adminBoard";
    }


    @GetMapping("/article")
    public String adminArticle(Model model) {
        List<Article> articleList = articleService.getArticleList();
        model.addAttribute("articleList",articleList);
        return "/admin/adminArticle";
    }

    @GetMapping("/user")
    public String adminUser(Model model) {
        List<User> userList = userService.getList().stream()
                .filter(u -> u.getUserRank()<2)
                .collect(Collectors.toList());
        model.addAttribute("userList",userList);
        return "/admin/adminUser";
    }
}
