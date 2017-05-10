package com.slemma.test.controller;

import com.slemma.test.model.User;
import com.slemma.test.service.ParsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by turov on 10.05.2017.
 */
@Controller
public class UserController {
    @Autowired
    private ParsingService parsingService;


    @RequestMapping(value = "/Users.csv", method = RequestMethod.GET)
    public void getCsvUserList(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
//        response.setContentType("application/force-download");

        List<User> userList = parsingService.parseUserList();
        StringBuilder builder = new StringBuilder();
        Field[] fields = User.class.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            builder.append(fieldName).append(",");
        }
        builder.delete(0,3);
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append("\n");

        for (User user : userList) {
            builder.append("\"").append(user.getName()).append("\"").append(",");
            builder.append("\"").append(user.getEmail()).append("\"").append(",");
            builder.append("\"").append(user.getPhone()).append("\"").append(",");
            builder.append("\"").append(user.getCity()).append("\"").append(",");
            builder.append("\"").append(user.getZipCode()).append("\"").append(",");
            builder.append("\"").append(user.getCompanyName()).append("\"").append(",");
            builder.append(user.getLatitude()).append(",");
            builder.append(user.getLongitude()).append(",");
            builder.append(user.getTodosCount()).append(",");
            builder.append(user.getPostsCount()).append(",");
            builder.append(user.getAlbumsCount());
            builder.append("\n");

        }
        String res = builder.toString();
        res = res.replace("null", "");

        File file = new File("Users.csv");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(res.getBytes());
        FileInputStream inputStream = new FileInputStream(file);
        fileOutputStream.flush();
        fileOutputStream.close();

        OutputStream outputStream = response.getOutputStream();

        byte[] buffer = new byte[4096];
        int length;
        while ((length = inputStream.read(buffer)) > 0){
            outputStream.write(buffer, 0, length);
        }

        response.setContentLength((int)file.length());
        response.setHeader("Content-Transfer-Encoding", "UTF-8");
        response.setHeader("Content-Disposition","attachment; filename=\"" + file.getName()+"\"");

        inputStream.close();
        outputStream.flush();
        outputStream.close();



    }
}
