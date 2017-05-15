package com.lafite.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * @author LafiteHao
 * @create 2017-05-15 14:08
 **/
@Entity
public class Daily implements Serializable {
    private int id;
    private String title;
    private String content;
    private Integer personId;
    private String personName;
    private String type;
    private Date time;
    private String isRefer;
    private Integer inquirerId;
    private String inquirerName;
    private String feedback;
    private User userByPersonId;
    private User userByInquirerId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 45)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 1024)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "person_id", nullable = true)
    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "person_name", nullable = true, length = 45)
    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 45)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "time", nullable = true)
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Basic
    @Column(name = "isRefer", nullable = true, length = 1)
    public String getIsRefer() {
        return isRefer;
    }

    public void setIsRefer(String isRefer) {
        this.isRefer = isRefer;
    }

    @Basic
    @Column(name = "inquirer_id", nullable = true)
    public Integer getInquirerId() {
        return inquirerId;
    }

    public void setInquirerId(Integer inquirerId) {
        this.inquirerId = inquirerId;
    }

    @Basic
    @Column(name = "inquirer_name", nullable = true, length = 45)
    public String getInquirerName() {
        return inquirerName;
    }

    public void setInquirerName(String inquirerName) {
        this.inquirerName = inquirerName;
    }

    @Basic
    @Column(name = "feedback", nullable = true, length = 1024)
    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Daily daily = (Daily) o;

        if (id != daily.id) return false;
        if (title != null ? !title.equals(daily.title) : daily.title != null) return false;
        if (content != null ? !content.equals(daily.content) : daily.content != null) return false;
        if (personId != null ? !personId.equals(daily.personId) : daily.personId != null) return false;
        if (personName != null ? !personName.equals(daily.personName) : daily.personName != null) return false;
        if (type != null ? !type.equals(daily.type) : daily.type != null) return false;
        if (time != null ? !time.equals(daily.time) : daily.time != null) return false;
        if (isRefer != null ? !isRefer.equals(daily.isRefer) : daily.isRefer != null) return false;
        if (inquirerId != null ? !inquirerId.equals(daily.inquirerId) : daily.inquirerId != null) return false;
        if (inquirerName != null ? !inquirerName.equals(daily.inquirerName) : daily.inquirerName != null) return false;
        if (feedback != null ? !feedback.equals(daily.feedback) : daily.feedback != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (personId != null ? personId.hashCode() : 0);
        result = 31 * result + (personName != null ? personName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (isRefer != null ? isRefer.hashCode() : 0);
        result = 31 * result + (inquirerId != null ? inquirerId.hashCode() : 0);
        result = 31 * result + (inquirerName != null ? inquirerName.hashCode() : 0);
        result = 31 * result + (feedback != null ? feedback.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    public User getUserByPersonId() {
        return userByPersonId;
    }

    public void setUserByPersonId(User userByPersonId) {
        this.userByPersonId = userByPersonId;
    }

    @ManyToOne
    @JoinColumn(name = "inquirer_id", referencedColumnName = "id")
    public User getUserByInquirerId() {
        return userByInquirerId;
    }

    public void setUserByInquirerId(User userByInquirerId) {
        this.userByInquirerId = userByInquirerId;
    }
}
