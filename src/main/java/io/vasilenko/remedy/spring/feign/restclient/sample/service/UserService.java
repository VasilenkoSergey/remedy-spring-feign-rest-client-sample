package io.vasilenko.remedy.spring.feign.restclient.sample.service;

import feign.RequestLine;
import io.vasilenko.remedy.spring.feign.restclient.sample.dto.User;

import java.util.List;

public interface UserService {

    @RequestLine("GET")
    List<User> getUsers();
}
