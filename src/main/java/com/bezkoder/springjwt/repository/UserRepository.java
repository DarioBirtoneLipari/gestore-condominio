package com.bezkoder.springjwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
  Optional<UserEntity> findByUsername(String username);
  public Boolean existsByUsername(String username);
  public Boolean existsByEmail(String email);
  public Optional<UserEntity> findByEmail(String email);
}
