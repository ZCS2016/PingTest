package com.example.PingTest.controller;

import com.example.PingTest.utils.Ping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PingTest")
public class PingController {

    @RequestMapping("/ping")
    public Map<String,String> ping(@RequestBody Map<String,String> params){
        Map<String,String> resultMap = new HashMap<>();

        System.out.println("--------------------------------------------");
        String ipAddress = params.get("ipAddress");
        String result = Ping.ping(ipAddress,5,500);

        String time = result.substring(result.indexOf("平均 =") + 4);
        time = time.substring(0,time.indexOf("<"));
        time = time.trim();
        resultMap.put("status", "0");
        if(!time.contains("ms")){
            time = "Time out";
            resultMap.put("status", "-1");
        }
        resultMap.put("result",result);
        resultMap.put("time",time);

        return resultMap;
    }

}
