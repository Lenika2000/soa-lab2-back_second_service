package ru.itmo.soalab2.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table
public class Human implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private Double height; //Значение поля должно быть больше 0
    private LocalDateTime birthday;

    public Human(int id, Double height, LocalDateTime birthday) {
        this.id = id;
        this.height = height;
        this.birthday = birthday;
    }

    public Human() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }
}

