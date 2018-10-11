package com.ims.model;

import javax.persistence.*;

/**
 * @author admin
 * @date 2018/9/29
 */
@Entity
@Table(name = "permissions_operation", schema = "authorization", catalog = "")
public class PermissionsOperationEntity {
    private int id;
    private int permissionsId;
    private int operationId;



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
    @Column(name = "operation_id", nullable = false)
    public int getOperationId() {
        return operationId;
    }

    public void setOperationId(int operationId) {
        this.operationId = operationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PermissionsOperationEntity that = (PermissionsOperationEntity) o;

        if (permissionsId != that.permissionsId) return false;
        if (operationId != that.operationId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = permissionsId;
        result = 31 * result + operationId;
        return result;
    }
}
