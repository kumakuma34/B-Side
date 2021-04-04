package bside.writing.controller;

import bside.writing.Service.ThemeService;
import bside.writing.dto.ThemeDto;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ThemeController {

    private final ThemeService themeService;

    @CrossOrigin("*")
    @RequestMapping(value = "/theme", method = RequestMethod.GET)
    public Map<String, Object> getTheme(@RequestParam(value="searchMethod") String searchCode,
                        @RequestParam(value="count") int themeCount){

        Map<String, Object> response = new LinkedHashMap<>();


        //TODO : 추후 분기 처리 필요
        if(searchCode.equals("random")) {
            response.put("theme_list", themeService.getNRandomTheme(themeCount));
        }

        return response;
    }

}
