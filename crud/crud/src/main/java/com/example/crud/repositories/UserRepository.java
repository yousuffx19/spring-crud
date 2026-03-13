package com.example.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
