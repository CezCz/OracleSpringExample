package io.github.cezcz.model;

import javax.persistence.*;

@Entity
@Table(name = "NIP_STATE", schema = "CEZ", catalog = "")
public class NipStateEntity {
    private int id;
    private String nipState;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NIP_STATE")
    public String getNipState() {
        return nipState;
    }

    public void setNipState(String nipState) {
        this.nipState = nipState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NipStateEntity that = (NipStateEntity) o;

        if (id != that.id) return false;
        if (nipState != null ? !nipState.equals(that.nipState) : that.nipState != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nipState != null ? nipState.hashCode() : 0);
        return result;
    }
}
