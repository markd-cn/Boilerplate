package net.boblog.app.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dave on 16/6/20.
 */
@Entity
@Table
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column private Long parentId;

    @Column private Long leftId;

    @Column private Long rightId;

    @Column private Long Level;

    @Column private Date createdAt;

    @Column private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getLeftId() {
        return leftId;
    }

    public void setLeftId(Long leftId) {
        this.leftId = leftId;
    }

    public Long getRightId() {
        return rightId;
    }

    public void setRightId(Long rightId) {
        this.rightId = rightId;
    }

    public Long getLevel() {
        return Level;
    }

    public void setLevel(Long level) {
        Level = level;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
