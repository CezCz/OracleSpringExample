package io.github.cezcz.model;

import javax.persistence.*;

@Entity
@Table(name = "USER_ROLE_NIPS", schema = "CEZ", catalog = "")
public class UserRoleNipsEntity {
    private int id;
    private String nipName;
    private String name;
    private String role;

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
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "ROLE")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoleNipsEntity that = (UserRoleNipsEntity) o;

        if (id != that.id) return false;
        if (nipName != null ? !nipName.equals(that.nipName) : that.nipName != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nipName != null ? nipName.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
