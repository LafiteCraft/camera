package com.lafite.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author LafiteHao
 * @create 2017-05-15 14:06
 **/
@Entity
public class User implements Serializable {
    private int id;
    private String name;
    private String birth;
    private String sex;
    private String qq;
    private String phone;
    private String loginName;
    private String password;
    private Collection<Daily> dailiesById;
    private Collection<Daily> dailiesById_0;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "birth", nullable = true, length = 45)
    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    @Basic
    @Column(name = "sex", nullable = true, length = 1)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "qq", nullable = true, length = 45)
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 45)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (birth != null ? !birth.equals(user.birth) : user.birth != null) return false;
        if (sex != null ? !sex.equals(user.sex) : user.sex != null) return false;
        if (qq != null ? !qq.equals(user.qq) : user.qq != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (loginName != null ? !loginName.equals(user.loginName) : user.loginName != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birth != null ? birth.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (loginName != null ? loginName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userByPersonId")
    public Collection<Daily> getDailiesById() {
        return dailiesById;
    }

    public void setDailiesById(Collection<Daily> dailiesById) {
        this.dailiesById = dailiesById;
    }

    @OneToMany(mappedBy = "userByInquirerId")
    public Collection<Daily> getDailiesById_0() {
        return dailiesById_0;
    }

    public void setDailiesById_0(Collection<Daily> dailiesById_0) {
        this.dailiesById_0 = dailiesById_0;
    }
}
