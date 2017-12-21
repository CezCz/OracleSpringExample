package io.github.cezcz.model;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "NIP_SDQC", schema = "CEZ", catalog = "")
public class NipSdqcEntity {
    private int id;
    private String nipName;
    private SdqcEntity sdqc;
    private Time dueDate;
    private Time datePerformed;
    private String performedBy;
    private String documentPath;

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

    @ManyToOne
    @JoinColumn(name = "SDQC_ID")
    public SdqcEntity getSdqcId() {
        return sdqc;
    }

    public void setSdqcId(SdqcEntity sdqcId) {
        this.sdqc = sdqcId;
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
    @Column(name = "PERFORMED_BY")
    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
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

        NipSdqcEntity that = (NipSdqcEntity) o;

        if (id != that.id) return false;
        if (sdqc != that.sdqc) return false;
        if (nipName != null ? !nipName.equals(that.nipName) : that.nipName != null) return false;
        if (dueDate != null ? !dueDate.equals(that.dueDate) : that.dueDate != null) return false;
        if (datePerformed != null ? !datePerformed.equals(that.datePerformed) : that.datePerformed != null)
            return false;
        if (performedBy != null ? !performedBy.equals(that.performedBy) : that.performedBy != null) return false;
        if (documentPath != null ? !documentPath.equals(that.documentPath) : that.documentPath != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nipName != null ? nipName.hashCode() : 0);
        result = 31 * result + (sdqc != null ? sdqc.hashCode() : 0);
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + (datePerformed != null ? datePerformed.hashCode() : 0);
        result = 31 * result + (performedBy != null ? performedBy.hashCode() : 0);
        result = 31 * result + (documentPath != null ? documentPath.hashCode() : 0);
        return result;
    }
}
