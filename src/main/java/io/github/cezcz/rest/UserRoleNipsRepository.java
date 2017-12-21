package io.github.cezcz.rest;

import io.github.cezcz.model.UserRoleNipsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleNipsRepository extends JpaRepository<UserRoleNipsEntity, Integer> {
}
