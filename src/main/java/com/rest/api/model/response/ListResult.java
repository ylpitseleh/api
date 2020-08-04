package com.rest.api.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 결과가 여러건인 API를 담는 모델
 * API 결과가 다중 건인 경우에 대한 데이터 모델. 결과 필드를 List 형태로 선언하고,
 * Generic Interface에 <T>를 지정하여 어떤 형태의 List값도 넣을 수 있도록 구성한다.
 * CommonResult를 상속받으므로 API요청 결과도 같이 출력된다.
 * */
@Getter @Setter
public class ListResult<T> extends CommonResult {
    private List<T> list;
}
