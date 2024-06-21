package com.busanit501.boot501.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MemberJoinDTO {
    private String mid;
    private String mpw;
    private String email;
//    private boolean del;
//    private boolean social;
    private String uuid;
    private String fileName;
    //첨부 파일 이름들
    private List<String> fileNames;

}
