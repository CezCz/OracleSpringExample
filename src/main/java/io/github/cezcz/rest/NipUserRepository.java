package io.github.cezcz.rest;

import io.github.cezcz.model.NipSdqcEntity;
import io.github.cezcz.model.NipUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NipUserRepository extends JpaRepository<NipUserEntity, Integer> {

    NipUserEntity findByName(String name);
}
