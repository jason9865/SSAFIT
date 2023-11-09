package com.ssafit.user.controller;

import com.ssafit.article.model.dto.response.ArticleResponse;
import com.ssafit.article.model.entity.Article;
import com.ssafit.board.model.entity.Board;
import com.ssafit.user.model.entity.User;
import com.ssafit.article.service.ArticleService;
import com.ssafit.board.service.BoardService;
import com.ssafit.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@Api(tags="관리자 게시판 컨트롤러")
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
    @ApiOperation(value="게시판 리스트", notes="관리자 계정만 접근 가능합니다.")
    public ResponseEntity<List<Board>> adminBoard() {
        List<Board> boardList = boardService.getList();
        return new ResponseEntity<List<Board>>(boardList, HttpStatus.OK);
    }


    @GetMapping("/article")
    @ApiOperation(value="게시글 리스트", notes="관리자 계정만 접근 가능합니다.")
    public ResponseEntity<List<ArticleResponse>> adminArticle() {
        List<ArticleResponse> articleList = articleService.getArticleList();
        return new ResponseEntity<List<ArticleResponse>>(articleList,HttpStatus.OK);
    }

    @GetMapping("/user")
    @ApiOperation(value="유저 리스트", notes="관리자 계정만 접근 가능합니다.")
    public ResponseEntity<List<User>> adminUser() {
        List<User> userList = userService.getList().stream()
                .filter(u -> u.getUserRank()<2)
                .collect(Collectors.toList());
        return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
    }
}
