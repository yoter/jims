package com.ims.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 2018/9/29
 */
@Entity
@Table(name = "organization", schema = "authorization", catalog = "")
public class OrganizationEntity {
    private int id;
    private String project;
    private String code;
    private String name;
    private Integer parent;
    private String description;

    private List<OrganizationEntity> children = new ArrayList<>();

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
    @Column(name = "project", nullable = false)
    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    @Basic
    @Column(name = "code", nullable = true, length = 255)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "parent", nullable = true)
    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    @Transient
    public List<OrganizationEntity> getChildren() {
        return children;
    }

    public void setChildren(List<OrganizationEntity> children) {
        this.children = children;
    }
}
