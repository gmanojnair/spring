package com.manoj.spring.auth.repository;

import com.manoj.spring.auth.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
