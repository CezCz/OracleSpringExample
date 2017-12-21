package io.github.cezcz.model;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "NIP", schema = "CEZ", catalog = "")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "INSERT_INTO_NIP",
                procedureName = "INSERT_INTO_NIP",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "nip_name_p", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "start_date_p", type = Time.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "end_date_p", type = Time.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "sdqcFrequency", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "nip_user_p", type = Integer.class)
                }),
        @NamedStoredProcedureQuery(name = "UPDATE_NIP",
                procedureName = "UPDATE_NIP",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "nip_id_p", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "start_date_p", type = Time.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "end_date_p", type = Time.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "sdqcFrequency", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "nip_user_p", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "nip_role_p", type = Integer.class)

                }),
        @NamedStoredProcedureQuery(name = "UPDATE_NIP_STATE",
                procedureName = "UPDATE_NIP_STATE",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "nip_id_p", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "nip_user_p", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "new_nip_state", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "nip_role_p", type = Integer.class)

                })
})
public class NipEntity {
    private int id;
    private String nipName;
    private Time startDate;
    private Time endDate;
    private String sdqcFrequency;
    private NipStateEntity state;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NIP_NAME")
    public String getNipName() {
        return nipName;
    }

    public void setNipName(String nipName) {
        this.nipName = nipName;
    }

    @Basic
    @Column(name = "START_DATE")
    public Time getStartDate() {
        return startDate;
    }

    public void setStartDate(Time startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "END_DATE")
    public Time getEndDate() {
        return endDate;
    }

    public void setEndDate(Time endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "SDQC_FREQUENCY")
    public String getSdqcFrequency() {
        return sdqcFrequency;
    }

    public void setSdqcFrequency(String sdqcFrequency) {
        this.sdqcFrequency = sdqcFrequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NipEntity nipEntity = (NipEntity) o;

        if (id != nipEntity.id) return false;
        if (nipName != null ? !nipName.equals(nipEntity.nipName) : nipEntity.nipName != null) return false;
        if (startDate != null ? !startDate.equals(nipEntity.startDate) : nipEntity.startDate != null) return false;
        if (endDate != null ? !endDate.equals(nipEntity.endDate) : nipEntity.endDate != null) return false;
        if (sdqcFrequency != null ? !sdqcFrequency.equals(nipEntity.sdqcFrequency) : nipEntity.sdqcFrequency != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nipName != null ? nipName.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (sdqcFrequency != null ? sdqcFrequency.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "STATE_ID")
    public NipStateEntity getStateId() {
        return state;
    }

    public void setStateId(NipStateEntity state) {
        this.state = state;
    }
}
