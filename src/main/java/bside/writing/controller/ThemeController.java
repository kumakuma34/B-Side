package bside.writing.controller;

import bside.writing.Service.ThemeService;
import bside.writing.dto.ThemeDto;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class ThemeController {

    private final ThemeService themeService;

    @RequestMapping(value = "/theme", method = RequestMethod.GET)
    public List<ThemeDto> getTheme(@RequestBody ThemeDto.Request themeRequstDto){
        JsonObject res = new JsonObject();
        if(themeRequstDto.getCategory().equals("random")) {
            return themeService.getNRandomTheme(themeRequstDto.getCount());
        }
        return null;
    }



}
