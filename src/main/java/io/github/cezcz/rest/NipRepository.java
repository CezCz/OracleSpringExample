package io.github.cezcz.rest;

import io.github.cezcz.model.NipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NipRepository extends JpaRepository<NipEntity, Integer> {
}
