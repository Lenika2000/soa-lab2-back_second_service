package ru.itmo.soalab2.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Coordinates implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private Integer x; //Значение поля должно быть больше -60, Поле не может быть null
    private Long y; //Максимальное значение поля: 498, Поле не может быть null

    public Coordinates() {
    }

    public Coordinates(int id, Integer x, Long y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }
}

