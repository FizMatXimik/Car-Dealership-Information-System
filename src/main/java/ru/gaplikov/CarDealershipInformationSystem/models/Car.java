package ru.gaplikov.CarDealershipInformationSystem.models;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Car")
public class Car {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Номер должен быть заполнен")
    @Column(name = "vin_number")
    private String vin_number;

    @Column(name = "car_cost")
    private int car_cost;

    @Column(name = "receive_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date receive_date;

    @Column(name = "sold_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sold_date;

    @Column(name = "sold")
    private boolean sold;

    @Transient
    private long stock_time;

    @Column(name = "color")
    private String color;

    @ManyToOne
    @JoinColumn(name = "cmodel_id", referencedColumnName = "id")
    private Cmodel cmodel;

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Manager manager;

    @ManyToMany
    @JoinTable(
            name = "Car_Parts",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "parts_id"))
    private List<Parts> parts;

    public Car() {}

    public Car(int id, String vin_number, int car_cost, Date receive_date, boolean sold, String color) {
        this.id = id;
        this.vin_number = vin_number;
        this.car_cost = car_cost;
        this.receive_date = receive_date;
        this.sold = sold;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVin_number() {
        return vin_number;
    }

    public void setVin_number(String vin_number) {
        this.vin_number = vin_number;
    }

    public int getCar_cost() {
        return car_cost;
    }

    public void setCar_cost(int car_cost) {
        this.car_cost = car_cost;
    }

    public Date getReceive_date() {
        return receive_date;
    }

    public void setReceive_date(Date receive_date) {
        this.receive_date = receive_date;
    }

    public Date getSold_date() {
        return sold_date;
    }

    public void setSold_date(Date sold_date) {
        this.sold_date = sold_date;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public long getStock_time() {
        long dev = 0L;
        if (sold_date != null) {
            dev = sold_date.getTime() - receive_date.getTime();
        } else {
            dev = new Date().getTime() - receive_date.getTime();
        }
        long day = 86400000L;
        return dev/day;
    }

    public void setStock_time(long stock_time) {
        this.stock_time = stock_time;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Cmodel getCmodel() {
        return cmodel;
    }

    public void setCmodel(Cmodel cmodel) {
        this.cmodel = cmodel;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public List<Parts> getParts() {
        return parts;
    }

    public void setParts(List<Parts> parts) {
        this.parts = parts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && car_cost == car.car_cost && sold == car.sold && Objects.equals(vin_number, car.vin_number) && Objects.equals(receive_date, car.receive_date)  && Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vin_number, car_cost, receive_date, sold, color);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", vin_number='" + vin_number + '\'' +
                ", car_cost=" + car_cost +
                ", receive_date=" + receive_date +
                ", sold=" + sold +
                ", stock_time='" + stock_time + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
