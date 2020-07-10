package com.example.todoappserver.repository;

import com.example.todoappserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select * from todo_user u where u.username=?1 and u.password=crypt(?2, u.password)", nativeQuery = true)
    User findByUsernameAndPassword(String username, String password);

    @Transactional
    @Modifying
    @Query(value = "insert into todo_user(username, password) values(?1, crypt(?2, gen_salt('bf')))", nativeQuery = true)
    int insert(String username, String password);
}
