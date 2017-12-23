package io.github.cezcz.rest;

import io.github.cezcz.model.NipAuditTrialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.sql.Time;

public interface NipAuditTrialRepository extends JpaRepository<NipAuditTrialEntity, Integer> {


}
