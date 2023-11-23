package com.ssafit.video.controller;

import com.ssafit.video.model.dto.request.VideoCommentCreateRequest;
import com.ssafit.video.model.dto.request.VideoCommentModifyRequest;
import com.ssafit.video.model.dto.response.VideoCommentResponse;
import com.ssafit.video.model.entity.Video;
import com.ssafit.video.service.VideoCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/videoComment")
@RequiredArgsConstructor
@Api(tags="비디오 댓글 컨트롤러")
@CrossOrigin("http://localhost:5173/")
public class VideoCommentController {

    private final VideoCommentService videoCommentService;

    @GetMapping("/{videoId}")
    @ApiOperation(value = "비디오의 댓글 리스트")
    public ResponseEntity<List<VideoCommentResponse>> getVideoCommentList(@PathVariable String videoId) {
        return new  ResponseEntity<List<VideoCommentResponse>>(videoCommentService.getVideoCommentList(videoId),HttpStatus.OK);
    }

    @GetMapping("/{videoCommentId}/detail")
    @ApiOperation(value = "비디오 댓글 1개")
    public ResponseEntity<VideoCommentResponse> getVideoComment(@PathVariable int videoCommentId) {
        return new ResponseEntity<VideoCommentResponse>(videoCommentService.getVideoComment(videoCommentId),HttpStatus.OK);
    }

    @PostMapping("/{videoId}/write")
    @ApiOperation(value = "비디오 댓글 작성")
    public ResponseEntity<Boolean> writeVideoComment(
            @RequestBody VideoCommentCreateRequest videoCommentCreateRequest,
            @PathVariable String videoId,
            HttpServletRequest request) {
        int userSeq = Integer.parseInt(request.getHeader("userSeq"));
        boolean isWritten = videoCommentService.writeVideoComment(videoCommentCreateRequest,userSeq,videoId);
        if(!isWritten)
            return new ResponseEntity<Boolean>(isWritten,HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Boolean>(isWritten, HttpStatus.ACCEPTED);

    }

    @PutMapping("/{videoCommentId}/update")
    @ApiOperation(value="비디오 댓글 수정")
    public ResponseEntity<Boolean> modifyVideoComment(
            @RequestBody VideoCommentModifyRequest videoCommentModifyRequest,
            @PathVariable int videoCommentId,
            HttpServletRequest request) {
        int userSeq = Integer.parseInt(request.getHeader("userSeq"));
        boolean isModified = videoCommentService.modifyVideoComment(videoCommentModifyRequest,userSeq,videoCommentId);
        if(!isModified)
            return new ResponseEntity<Boolean>(isModified,HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Boolean>(isModified, HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/{videoCommentId}/delete")
    @ApiOperation(value="비디오 댓글 삭제")
    public ResponseEntity<Boolean> removeVideoComment(@PathVariable int videoCommentId){
        boolean isRemoved = videoCommentService.removeVideoComment(videoCommentId);
        if(!isRemoved)
            return new ResponseEntity<Boolean>(isRemoved,HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Boolean>(isRemoved, HttpStatus.ACCEPTED);


    }



}
