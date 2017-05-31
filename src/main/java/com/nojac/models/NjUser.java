package com.nojac.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nickolas on 5/22/17.
 */
@Entity
@Table(name = "nj_user")
public class NjUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_id", insertable = false)
    private long userId;

//    @Column(name = "username", unique = true)
    private String username;

//    @Column(name = "email")
    private String email;

//    @Column(name = "hash_pass")
    private String hashPass;

//    @Temporal(TemporalType.TIMESTAMP)
//    @Type(type="timestamp")
//    @Column(name = "created")
    private Date created;

//    @Temporal(TemporalType.TIMESTAMP)
//    @Type(type="timestamp")
//    @Column(name = "updated")
    private Date updated;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("njUser")
    private Calendar calendar;

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

//    @Enumerated(EnumType.STRING)
//    @Column(name = "role")
//    private Role role;

    public NjUser() {
        //default ctr
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashPass() {
        return hashPass;
    }

    public void setHashPass(String hashPass) {
        this.hashPass = hashPass;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "NjUser{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", hashPass='" + hashPass + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }

    //    create table nj_user (
//            user_id int auto_increment,
//            username text,
//            email text,
//            hash_pass text,
//            created timestamp default current_timestamp,
//            updated timestamp,
//            constraint nj_user_pk primary key (user_id)
//    );

    //Utility Methods here...

}
