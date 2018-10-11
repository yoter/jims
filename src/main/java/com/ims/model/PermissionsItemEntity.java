package com.ims.model;

import javax.persistence.*;

/**
 * @author admin
 * @date 2018/9/29
 */
@Entity
@Table(name = "permissions_item", schema = "authorization", catalog = "")
public class PermissionsItemEntity {
    private int id;
    private int permissionsId;
    private int itemId;



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
    @Column(name = "item_id", nullable = false)
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PermissionsItemEntity that = (PermissionsItemEntity) o;

        if (permissionsId != that.permissionsId) return false;
        if (itemId != that.itemId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = permissionsId;
        result = 31 * result + itemId;
        return result;
    }
}
