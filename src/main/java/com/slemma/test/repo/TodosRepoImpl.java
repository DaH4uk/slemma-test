package com.slemma.test.repo;

/**
 * Created by turov on 10.05.2017.
 */
public class TodosRepoImpl extends AbstractRepo implements TodosRepo {
    @Override
    public String getUrl() {
        return "https://jsonplaceholder.typicode.com/todos";
    }
}
