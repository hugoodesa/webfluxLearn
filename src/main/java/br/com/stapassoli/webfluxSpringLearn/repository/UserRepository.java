package br.com.stapassoli.webfluxSpringLearn.repository;

import br.com.stapassoli.webfluxSpringLearn.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
}
