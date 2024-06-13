package com.busanit501.boot501.controller;

import com.busanit501.boot501.dto.PageRequestDTO;
import com.busanit501.boot501.dto.PageResponseDTO;
import com.busanit501.boot501.dto.ReplyDTO;
import com.busanit501.boot501.service.ReplyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @Tag(name = "댓글 등록 post 방식", description = "댓글 등록 post 방식")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    //ResponseEntity, 데이터 + http 상태코드 같이 전달 도구.
//    public ResponseEntity<Map<String,Long>> register(
    public Map<String,Long> register(
            //@RequestBody -> JSON 문자열.  , 키: 값의 구조 형태의 중간 데이터 타입,
            // JSON 문자열 <--Jackson 컨버터 --> DTO
           @Valid @RequestBody ReplyDTO replyDTO,
            BindingResult bindingResult) throws BindException {
        log.info("ReplyController의 register ,replyDTO 확인: "+replyDTO);

        // 에러가 발생한다면, 처리하기.
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        // 응답 해줄 더미 데이터 만들기.
//        Map<String,Long> resultMap = Map.of("rno",123L);
        // 실제 데이터를 입력 후, 해당 데이터를 반환
        Map<String,Long> resultMap = new HashMap<>();
        Long rno = replyService.register(replyDTO);
        resultMap.put("rno",rno);

        //        return ResponseEntity.ok(resultMap);
        return resultMap;

    } // register 닫는 부분

    @Tag(name = "댓글 목록 조회 get 방식", description = "댓글 목록 조회 get 방식")
    @GetMapping(value = "/list/{bno}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageResponseDTO<ReplyDTO> getList(
            // 경로의 변수를 서버에 재할당.
            // 게시글 번호를 가지고 올 예정.
        @PathVariable("bno") Long bno, PageRequestDTO pageRequestDTO
    ) {
        PageResponseDTO<ReplyDTO> responseDTO = replyService.getListOfBoard(bno, pageRequestDTO);
        return  responseDTO;
    }


}