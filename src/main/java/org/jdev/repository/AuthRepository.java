package org.jdev.repository;

import org.jdev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository {//extends JpaRepository<User, Long> {

    User findAccountByUid(Integer uid);

}
