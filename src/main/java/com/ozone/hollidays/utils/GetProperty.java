package com.ozone.hollidays.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
@NoArgsConstructor
public class GetProperty {
    private Environment env;

    @Autowired
    public GetProperty(Environment env) {
        this.env = env;
    }
    public String getPropertyValue(@RequestParam("key") String key)
    {
       String keyValue = env.getProperty(key);
       return  keyValue!= null && !keyValue.isEmpty() ?  keyValue :  "No value";
    }
}