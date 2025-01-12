package org.jdev.repository;

import org.jdev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findAccountById(Integer id);

}
