package ru.gaplikov.CarDealershipInformationSystem.models;


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

    @NotEmpty(message = "Стоймость должна быть указана")
    @Column(name = "car_cost")
    private int car_cost;

    @NotEmpty(message = "Дата поставки должна быть указана")
    @Column(name = "receive_date")
    private Date receive_date;

    @Column(name = "sold")
    private boolean sold;

    @Column(name = "stock_time")
    private String stock_time;

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
            name = "Car_SpareParts",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "spareparts_id"))
    private List<SpareParts> spareParts;

    public Car() {}

    public Car(int id, String vin_number, int car_cost, Date receive_date, boolean sold, String stock_time, String color) {
        this.id = id;
        this.vin_number = vin_number;
        this.car_cost = car_cost;
        this.receive_date = receive_date;
        this.sold = sold;
        this.stock_time = stock_time;
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

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public String getStock_time() {
        return stock_time;
    }

    public void setStock_time(String stock_time) {
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

    public List<SpareParts> getSpareParts() {
        return spareParts;
    }

    public void setSpareParts(List<SpareParts> spareParts) {
        this.spareParts = spareParts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && car_cost == car.car_cost && sold == car.sold && Objects.equals(vin_number, car.vin_number) && Objects.equals(receive_date, car.receive_date) && Objects.equals(stock_time, car.stock_time) && Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vin_number, car_cost, receive_date, sold, stock_time, color);
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
