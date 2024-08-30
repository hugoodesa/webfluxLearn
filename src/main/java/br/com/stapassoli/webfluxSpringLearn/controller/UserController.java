package br.com.stapassoli.webfluxSpringLearn.controller;

import br.com.stapassoli.webfluxSpringLearn.entity.User;
import br.com.stapassoli.webfluxSpringLearn.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping
    public Mono<User> createUser(@RequestBody User user){
        return this.userRepository.save(user);
    }

    @GetMapping
    public Flux<User> findAllUsers(){
        return this.userRepository.findAll();
    }


}
