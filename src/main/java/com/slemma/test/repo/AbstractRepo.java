package com.slemma.test.repo;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.slemma.test.service.HttpService;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by turov on 10.05.2017.
 */
public abstract class AbstractRepo {
    @Autowired
    private HttpService httpService;

    private JsonParser jsonParser = new JsonParser();


    public String getUrl(){
        return null;
    }

    public Integer countByUserId(Long userId){
        String res = httpService.sendGet(getUrl());
        Integer count = 0;
        for (JsonElement element : jsonParser.parse(res).getAsJsonArray()) {
            if (userId == element.getAsJsonObject().get("userId").getAsLong()) {
                count++;
            }
        }
        return count;
    }

}
