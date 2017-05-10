package com.slemma.test.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.slemma.test.model.User;
import com.slemma.test.repo.AlbumRepo;
import com.slemma.test.repo.PostRepo;
import com.slemma.test.repo.TodosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by turov on 10.05.2017.
 */
@Service
public class ParsingServiceImpl implements ParsingService {
    @Autowired
    private AlbumRepo albumRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private TodosRepo todosRepo;

    @Autowired
    private HttpService httpService;

    private JsonParser jsonParser = new JsonParser();


    public List<User> parseUserList() {
        String res = httpService.sendGet("https://jsonplaceholder.typicode.com/users");
        List<User> userList = new ArrayList<>();
        for (JsonElement jsonElement : jsonParser.parse(res).getAsJsonArray()){
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            User user = new User();
            user.setId(jsonObject.get("id").getAsLong());
            user.setName(jsonObject.get("name").getAsString());
            user.setEmail(jsonObject.get("email").getAsString());
            user.setPhone(jsonObject.get("phone").getAsString());
            user.setCity(jsonObject.get("address").getAsJsonObject().get("city").getAsString());
            user.setZipCode(jsonObject.get("address").getAsJsonObject().get("zipcode").getAsString());
            user.setCompanyName(jsonObject.get("company").getAsJsonObject().get("name").getAsString());
            user.setLatitude(jsonObject.get("address").getAsJsonObject().get("geo").getAsJsonObject().get("lat").getAsDouble());
            user.setLongitude(jsonObject.get("address").getAsJsonObject().get("geo").getAsJsonObject().get("lng").getAsDouble());
            user.setTodosCount(todosRepo.countByUserId(user.getId()));
            user.setPostsCount(postRepo.countByUserId(user.getId()));
            user.setAlbumsCount(albumRepo.countByUserId(user.getId()));
            userList.add(user);
        }
        return userList;
    }
}
