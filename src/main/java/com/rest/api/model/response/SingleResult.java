package com.rest.api.model.response;

import lombok.Getter;
import lombok.Setter;

/**
 * 결과가 단일건인 API를 담는 모델
 * Generic Interface에 <T>를 지정하여 어떤 형태의 값도 넣을 수 있도록 한다.
 * CommonResult를 상속받으므로 API요청 결과도 같이 출력된다.
 */
@Getter @Setter
public class SingleResult<T> extends CommonResult {
    private T data;
}
