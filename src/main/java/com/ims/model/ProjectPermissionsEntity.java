package com.ims.model;

import javax.persistence.*;

/**
 * @author admin
 * @date 2018/9/29
 */
@Entity
@Table(name = "project_permissions", schema = "authorization", catalog = "")
public class ProjectPermissionsEntity {
    private int id;
    private int projectId;
    private int permissionsId;


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
    @Column(name = "project_id", nullable = false)
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "permissions_id", nullable = false)
    public int getPermissionsId() {
        return permissionsId;
    }

    public void setPermissionsId(int permissionsId) {
        this.permissionsId = permissionsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectPermissionsEntity that = (ProjectPermissionsEntity) o;

        if (projectId != that.projectId) return false;
        if (permissionsId != that.permissionsId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = projectId;
        result = 31 * result + permissionsId;
        return result;
    }
}
