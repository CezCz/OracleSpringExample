package io.github.cezcz.rest;

import io.github.cezcz.model.SdqcEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SdqcRepository extends JpaRepository<SdqcEntity, Integer> {
}
