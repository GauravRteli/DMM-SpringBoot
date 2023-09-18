package com.onlyjavatech.gaurav.dao;

import com.onlyjavatech.gaurav.Entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public List<User> findByName(String name);
    public List<User> findByCity(String city);

    public List<User> findByNameAndCity(String name, String city);

    public List<User> findByNameOrCity(String name,String city);

    public List<User> findByIdGreaterThanEqual(int i);

    public List<User> findByIdLessThanEqual(int i);

    @Query("select u from User u WHERE u.name =:n AND u.city =:c")
    public List<User> customFunction(@Param("n")String name, @Param("c") String city);

}
