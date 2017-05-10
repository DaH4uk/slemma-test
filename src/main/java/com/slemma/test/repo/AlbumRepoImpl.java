package com.slemma.test.repo;

import org.springframework.stereotype.Service;


/**
 * Created by turov on 10.05.2017.
 */
@Service
public class AlbumRepoImpl extends AbstractRepo implements AlbumRepo {


    @Override
    public String getUrl() {
        return "https://jsonplaceholder.typicode.com/albums";
    }
}
