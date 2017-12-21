package io.github.cezcz.model;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "NIP_AUDIT_TRIAL", schema = "CEZ", catalog = "")
public class NipAuditTrialEntity {
    private int id;
    private int nipId;
    private String nipName;
    private Time startDate;
    private Time endDate;
    private String sdqcFrequency;
    private NipStateEntity state;
    private Time actionDate;
    private int nipUser;
    private int nipRole;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NIP_ID")
    public int getNipId() {
        return nipId;
    }

    public void setNipId(int nipId) {
        this.nipId = nipId;
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

    @ManyToOne
    @JoinColumn(name = "STATE_ID")
    public NipStateEntity getStateId() {
        return state;
    }

    public void setStateId(NipStateEntity stateId) {
        this.state = stateId;
    }

    @Basic
    @Column(name = "ACTION_DATE")
    public Time getActionDate() {
        return actionDate;
    }

    public void setActionDate(Time actionDate) {
        this.actionDate = actionDate;
    }

    @Basic
    @Column(name = "NIP_USER")
    public int getNipUser() {
        return nipUser;
    }

    public void setNipUser(int nipUser) {
        this.nipUser = nipUser;
    }

    @Basic
    @Column(name = "NIP_ROLE")
    public int getNipRole() {
        return nipRole;
    }

    public void setNipRole(int nipRole) {
        this.nipRole = nipRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NipAuditTrialEntity that = (NipAuditTrialEntity) o;

        if (id != that.id) return false;
        if (nipId != that.nipId) return false;
        if (state != that.state) return false;
        if (nipUser != that.nipUser) return false;
        if (nipRole != that.nipRole) return false;
        if (nipName != null ? !nipName.equals(that.nipName) : that.nipName != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (sdqcFrequency != null ? !sdqcFrequency.equals(that.sdqcFrequency) : that.sdqcFrequency != null)
            return false;
        if (actionDate != null ? !actionDate.equals(that.actionDate) : that.actionDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nipId;
        result = 31 * result + (nipName != null ? nipName.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (sdqcFrequency != null ? sdqcFrequency.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (actionDate != null ? actionDate.hashCode() : 0);
        result = 31 * result + nipUser;
        result = 31 * result + nipRole;
        return result;
    }
}
