package com.slemma.test.service;

import com.slemma.test.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by turov on 10.05.2017.
 */
@Service
public interface ParsingService {
    List<User> parseUserList();

}
