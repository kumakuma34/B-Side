package bside.writing.controller;

import bside.writing.Service.ThemeService;
import bside.writing.dto.ThemeDto;
import bside.writing.templateClass.StatusCode;
import com.google.gson.JsonObject;
import io.opencensus.trace.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ThemeController {

    private final ThemeService themeService;

    @RequestMapping(value = "/theme", method = RequestMethod.GET)
    public List<ThemeDto> getTheme(@RequestBody ThemeDto.Request themeRequstDto, HttpServletResponse response){
        JsonObject res = new JsonObject();
        try{
            if(themeRequstDto.getCategory().equals("random")){
               return themeService.getNRandomTheme(themeRequstDto.getCount());

            }
        }
        catch (Exception e){
            response.setStatus(StatusCode.BAD_REQUEST.getCode());
        }
        return null;
    }
}
