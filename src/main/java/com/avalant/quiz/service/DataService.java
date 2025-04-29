package com.avalant.quiz.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {

    public List<Double> getArrayFromWebService() {
        return List.of(1.0, 8.0, -40.0, 37.5, 7.0, 9.0, -14.0, 20.0);
    }

    public List<String> getFirstNameList() {
        return List.of("alice", "bob", "trudy", "jack");
    }

    public List<String> getSecondNameList() {
        return List.of("janet", "alice", "james", "jack");
    }
    public List<Integer> getNumbersFromWebService() {
        return List.of(1, 5, 13, 2, 7, 6);
    }

}
