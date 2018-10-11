package com.ims.model;

import javax.persistence.*;

/**
 * @author admin
 * @date 2018/9/29
 */
@Entity
@Table(name = "permissions_menu", schema = "authorization", catalog = "")
public class PermissionsMenuEntity {
    private int id;
    private int permissionsId;
    private int menuId;



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Basic
    @Column(name = "permissions_id", nullable = false)
    public int getPermissionsId() {
        return permissionsId;
    }

    public void setPermissionsId(int permissionsId) {
        this.permissionsId = permissionsId;
    }

    @Basic
    @Column(name = "menu_id", nullable = false)
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PermissionsMenuEntity that = (PermissionsMenuEntity) o;

        if (permissionsId != that.permissionsId) return false;
        if (menuId != that.menuId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = permissionsId;
        result = 31 * result + menuId;
        return result;
    }
}
