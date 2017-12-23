package io.github.cezcz.rest;

import io.github.cezcz.model.UserRoleNipsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleNipsRepository extends JpaRepository<UserRoleNipsEntity, Integer> {

//    List<UserRoleNipsEntity> findByNip__idAndUser__id(Integer nipId, Integer userId);
}
