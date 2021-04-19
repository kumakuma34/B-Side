package bside.writing.controller;

import bside.writing.Service.NotificationService;
import bside.writing.Service.TokenService;
import bside.writing.dto.NotificationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    private final TokenService tokenService;

    @CrossOrigin("*")
    @RequestMapping(value = "/notification", method = RequestMethod.GET)
    public Map<String, List> getNoti(@RequestHeader(name="Authorization") String accessToken) {
        Map<String, List> response = new HashMap<>();

        Long memberId = tokenService.getUid(accessToken);
        List<NotificationResponseDto> notifications = notificationService.getNotificationAsResponseDto(memberId);

        response.put("notifications", notifications);
        return response;
    }

}
