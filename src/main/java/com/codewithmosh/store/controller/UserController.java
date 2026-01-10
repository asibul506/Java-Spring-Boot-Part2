package com.codewithmosh.store.controller;

import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.mappers.UserMapper;
import com.codewithmosh.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping()
    public Iterable<UserDto> getAllUsers(
            @RequestHeader (value = "X-Auth-token", required = false) String authToken,
            @RequestParam(required = false, defaultValue = "", name = "sort") String sortBy
    ) {

        System.out.println("Auth User: " + authToken); // just to show how to read headers

        if(!Set.of("name", "email").contains(sortBy))
            sortBy ="name";// validate sort parameter

        return userRepository.findAll(Sort.by(sortBy).descending())
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        var  user= userRepository.findById(id).orElse(null);
        if(user==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userMapper.toDto(user));

    }

}
