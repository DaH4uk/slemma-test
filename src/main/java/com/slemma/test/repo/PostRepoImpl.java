package com.slemma.test.repo;

/**
 * Created by turov on 10.05.2017.
 */
public class PostRepoImpl extends AbstractRepo implements PostRepo {

    @Override
    public String getUrl() {
        return "https://jsonplaceholder.typicode.com/posts";
    }
}
