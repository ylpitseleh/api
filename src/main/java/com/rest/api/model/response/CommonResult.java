package com.rest.api.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * model.response 패키지 = api의 실행 결과를 담는 공통 모델
 *
 * // 기존 USER 정보
 * {
 *     "msrl": 1,
 *     "uid": "yumi@naver.com",
 *     "name": "정유미"
 * }
 * // 표준화한 USER 정보
 * {
 *   "data": {
 *     "msrl": 1,
 *     "uid": "yumi@naver.com",
 *     "name": "정유미"
 *   },
 *   "success": true
 *   "code": 0,
 *   "message": "성공하였습니다."
 * }
 */
// API의 실행 결과를 담는 공통 모델
@Getter
@Setter
public class CommonResult {

    @ApiModelProperty(value = "응답 성공여부 : true/false")
    private boolean success;

    @ApiModelProperty(value = "응답 코드 번호 : >= 0 정상, < 0 비정상")
    private int code;

    @ApiModelProperty(value = "응답 메시지")
    private String msg;
}