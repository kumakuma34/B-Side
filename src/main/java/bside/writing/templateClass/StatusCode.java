package bside.writing.templateClass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatusCode {
     OK(200)
    ,CREATED(201)
    ,NO_CONTENT(204)
    ,BAD_REQUEST(400)
    ,UNAUTHORIZED(401)
    ,FORBIDDEN(403)
    ,NOT_FOUND(404)
    ,INTERNAL_SERVER_ERROR(500)
    ,SERVICE_UNAVAILABLE(503)
    ,DB_ERROR(600)
    ;
    private final int code;
}
