package com.lafite.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author LafiteHao
 * @create 2017-05-09 13:46
 **/
@Entity
public class Login implements Serializable {
    private Integer id;
    private String loginName;
    private String password;
    private User user;

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "login_name", nullable = true, length = 45)
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Login login = (Login) o;

        if (id != null ? !id.equals(login.id) : login.id != null) return false;
        if (loginName != null ? !loginName.equals(login.loginName) : login.loginName != null) return false;
        if (password != null ? !password.equals(login.password) : login.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (loginName != null ? loginName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
