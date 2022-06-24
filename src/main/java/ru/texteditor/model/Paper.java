package ru.texteditor.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "papers")
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String oldText;
    private String newText;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    private Date modify;
    private boolean done;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String createUser;
    private String modifyUser;

    public Paper() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOldText() {
        return oldText;
    }

    public void setOldText(String oldText) {
        this.oldText = oldText;
    }

    public String getNewText() {
        return newText;
    }

    public void setNewText(String newText) {
        this.newText = newText;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModify() {
        return modify;
    }

    public void setModify(Date modify) {
        this.modify = modify;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Paper paper = (Paper) o;
        return id == paper.id
                && Objects.equals(name, paper.name)
                && Objects.equals(oldText, paper.oldText)
                && Objects.equals(newText, paper.newText)
                && Objects.equals(created, paper.created)
                && Objects.equals(modify, paper.modify)
                && Objects.equals(user, paper.user)
                && Objects.equals(createUser, paper.createUser)
                && Objects.equals(modifyUser, paper.modifyUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, oldText, newText, created, modify, user, createUser, modifyUser);
    }

    @Override
    public String toString() {
        return "Paper{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", oldText='" + oldText + '\''
                + ", newText='" + newText + '\''
                + ", created=" + created
                + ", modify=" + modify
                + ", user=" + user
                + ", createUser=" + createUser
                + ", modifyUser=" + modifyUser
                + '}';
    }
}