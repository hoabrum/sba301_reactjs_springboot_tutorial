package com.usermanagement.repository;

import com.usermanagement.dto.response.UserSearchResponse;
import com.usermanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);


    @Query("SELECT new com.usermanagement.dto.response.UserSearchResponse(u.id, u.lastName, u.firstName, u.email,  a.city)" +
            " FROM User u JOIN u.addresses a WHERE u.firstName = :filter " +
            " OR u.lastName = :filter ORDER BY u.id DESC")
    List<UserSearchResponse> searchUserInfo(@Param("filter") String filter);
}
