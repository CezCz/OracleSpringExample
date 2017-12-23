package io.github.cezcz.rest;

import io.github.cezcz.model.NipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.sql.Time;

public interface NipRepository extends JpaRepository<NipEntity, Integer> {
    @Procedure(name = "INSERT_INTO_NIP")
    void createNewNip(@Param("nip_name_p") String nip_name_p,
                      @Param("start_date_p") Time start_date_p,
                      @Param("end_date_p") Time end_date_p,
                      @Param("sdqcFrequency") String sdqcFrequency,
                      @Param("nip_user_p") Integer nip_user_p);

    @Procedure(name = "UPDATE_NIP")
    void updateNip(@Param("nip_id_p") Integer nip_id_p,
                   @Param("nip_name_p") String nip_name_p,
                   @Param("start_date_p") Time start_date_p,
                   @Param("end_date_p") Time end_date_p,
                   @Param("sdqcFrequency") String sdqcFrequency,
                   @Param("nip_user_p") Integer nip_user_p,
                   @Param("nip_role_p") Integer nip_role_p);

    @Procedure(name = "UPDATE_NIP_STATE")
    void updateNipState(@Param("nip_id_p") Integer nip_id_p,
                        @Param("nip_user_p") Integer nip_name_p,
                        @Param("new_nip_state") Integer start_date_p,
                        @Param("nip_role_p") Integer nip_role_p);
}
