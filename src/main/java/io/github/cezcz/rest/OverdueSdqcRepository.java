package io.github.cezcz.rest;

import io.github.cezcz.model.OverdueSdqcEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OverdueSdqcRepository extends JpaRepository<OverdueSdqcEntity, Integer> {
}
