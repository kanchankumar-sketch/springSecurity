package com.management.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.entity.Users;

public interface UsersRepo extends JpaRepository<Users,Long> {

}
