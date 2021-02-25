package bside.writing.controller;

import bside.writing.Service.Security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/jwt")
public class JWTtestService {
    @Autowired
    private SecurityService securityService;

    @GetMapping("/gen/token")
    public Map<String, Object> genToken(@RequestParam(value = "subject") String subject){
        String token = securityService.createToken(subject, 2 * 1000 * 60);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("result", token);
        return map;
    }

    @ResponseBody
    @GetMapping("/get/subject")
    public Map<String, Object> getSubject(@RequestParam("token") String token){
        String subject = securityService.getEmail(token);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("result", subject);
        return map;
    }
}
