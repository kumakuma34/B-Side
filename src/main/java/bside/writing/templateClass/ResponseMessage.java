package bside.writing.templateClass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseMessage {
     LOGIN_SUCCESS("로그인 성공")
    ,UNAUTHORIZED_USER("unauthorized user")
    ,UNAUTHORIZED_TOKEN("invalid token")
    ;
    private final String msg;
}
