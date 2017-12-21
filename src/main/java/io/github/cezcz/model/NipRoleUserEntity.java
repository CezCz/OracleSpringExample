package io.github.cezcz.model;

import javax.persistence.*;

@Entity
@Table(name = "NIP_ROLE_USER", schema = "CEZ", catalog = "")
public class NipRoleUserEntity {
    private int id;
    private NipEntity nip;
    private NipRoleEntity roleId;
    private NipUserEntity userId;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NipRoleUserEntity that = (NipRoleUserEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "NIP_ID")
    public NipEntity getNipId() {
        return nip;
    }

    public void setNipId(NipEntity nipId) {
        this.nip = nipId;
    }

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    public NipRoleEntity getRoleId() {
        return roleId;
    }

    public void setRoleId(NipRoleEntity roleId) {
        this.roleId = roleId;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public NipUserEntity getUserId() {
        return userId;
    }

    public void setUserId(NipUserEntity userId) {
        this.userId = userId;
    }
}
