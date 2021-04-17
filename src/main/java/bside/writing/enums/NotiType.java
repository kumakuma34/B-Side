package bside.writing.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotiType {
    WELLCOM("na","라이틴 가입을 환영합니다."),
    ARTICLE_LIKE("article", "이(가) 좋아요를 받았어요!"), // 해당 글로 이동
    BEFORE_START_CHALLENGE("challenge","시작 하루 전이에요!"), // 해당 첼린지 화면으로 이동
    MODIFY_CHALLENGE("challenge", "이(가) 수정되었어요. 확인해보세요"), // 해당 첼린지 화면으로 이동
    DELETE_CHALLENGE("challenge","이(가) 개설자에 의해 삭제되었어요 ㅠㅠ");

    private final String fromType;
    private final String msg;
}
