package cn.aegisa.project.model;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * User Entity.
 */
public class User implements Serializable {

    //列信息
    private Integer id;

    private String name;

    private String password;

    private LocalDateTime lastLoginTime;

    private Integer wrongTime;

    private Integer status;

    private LocalDateTime lockTime;


    public void setId(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return this.id;
    }


    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }


    public void setPassword(String value) {
        this.password = value;
    }

    public String getPassword() {
        return this.password;
    }


    public void setLastLoginTime(LocalDateTime value) {
        this.lastLoginTime = value;
    }

    public LocalDateTime getLastLoginTime() {
        return this.lastLoginTime;
    }


    public void setWrongTime(Integer value) {
        this.wrongTime = value;
    }

    public Integer getWrongTime() {
        return this.wrongTime;
    }


    public void setStatus(Integer value) {
        this.status = value;
    }

    public Integer getStatus() {
        return this.status;
    }


    public void setLockTime(LocalDateTime value) {
        this.lockTime = value;
    }

    public LocalDateTime getLockTime() {
        return this.lockTime;
    }

}

