package io.github.cezcz.rest;

import io.github.cezcz.model.NipRoleUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NipRoleUserRepository extends JpaRepository<NipRoleUserEntity,Integer> {
}
