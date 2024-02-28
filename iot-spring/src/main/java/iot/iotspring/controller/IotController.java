package iot.iotspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IotController {

    @GetMapping("iot")
    @ResponseBody
    public String iotString(@RequestParam("name") String name) {
        return "iot " + name;
    }


}

