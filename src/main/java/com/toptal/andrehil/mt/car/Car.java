package com.toptal.andrehil.mt.car;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Car entity. <br>
 *
 * @author André Hildinger
 *
 */
@Entity
@Table(name = "car")
@XmlRootElement
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @SequenceGenerator(name = "car_seq", sequenceName = "car_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "car_seq")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Column
    private String name;

    @Column
    private String brand;

}
