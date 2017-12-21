package io.github.cezcz.model;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "SDQC", schema = "CEZ", catalog = "")
public class SdqcEntity {
    private int id;
    private Time dueDate;
    private Time datePerformed;
    private String documentPath;
    private NipStateEntity nip;
    private NipUserEntity performedBy;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DUE_DATE")
    public Time getDueDate() {
        return dueDate;
    }

    public void setDueDate(Time dueDate) {
        this.dueDate = dueDate;
    }

    @Basic
    @Column(name = "DATE_PERFORMED")
    public Time getDatePerformed() {
        return datePerformed;
    }

    public void setDatePerformed(Time datePerformed) {
        this.datePerformed = datePerformed;
    }

    @Basic
    @Column(name = "DOCUMENT_PATH")
    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SdqcEntity that = (SdqcEntity) o;

        if (id != that.id) return false;
        if (dueDate != null ? !dueDate.equals(that.dueDate) : that.dueDate != null) return false;
        if (datePerformed != null ? !datePerformed.equals(that.datePerformed) : that.datePerformed != null)
            return false;
        if (documentPath != null ? !documentPath.equals(that.documentPath) : that.documentPath != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + (datePerformed != null ? datePerformed.hashCode() : 0);
        result = 31 * result + (documentPath != null ? documentPath.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "NIP_ID")
    public NipStateEntity getNipId() {
        return nip;
    }

    public void setNipId(NipStateEntity nipId) {
        this.nip = nipId;
    }

    @ManyToOne
    @JoinColumn(name = "PERFORMED_BY")
    public NipUserEntity getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(NipUserEntity performedBy) {
        this.performedBy = performedBy;
    }
}
