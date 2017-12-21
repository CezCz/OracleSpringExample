package io.github.cezcz.model;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "OVERDUE_SDQC", schema = "CEZ", catalog = "")
public class OverdueSdqcEntity {
    private int id;
    private String nipName;
    private Time dueDate;
    private Long overdue;
    private String name;

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
    @Column(name = "DUE_DATE")
    public Time getDueDate() {
        return dueDate;
    }

    public void setDueDate(Time dueDate) {
        this.dueDate = dueDate;
    }

    @Basic
    @Column(name = "OVERDUE")
    public Long getOverdue() {
        return overdue;
    }

    public void setOverdue(Long overdue) {
        this.overdue = overdue;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OverdueSdqcEntity that = (OverdueSdqcEntity) o;

        if (id != that.id) return false;
        if (nipName != null ? !nipName.equals(that.nipName) : that.nipName != null) return false;
        if (dueDate != null ? !dueDate.equals(that.dueDate) : that.dueDate != null) return false;
        if (overdue != null ? !overdue.equals(that.overdue) : that.overdue != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nipName != null ? nipName.hashCode() : 0);
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + (overdue != null ? overdue.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
