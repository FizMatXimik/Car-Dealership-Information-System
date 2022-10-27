package ru.gaplikov.CarDealershipInformationSystem.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Cmodel")
public class Cmodel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Марка должна быть заполнена")
    @Column(name = "brand")
    private String brand;

    @NotEmpty(message = "Базовая стоймость должна быть указана")
    @Column(name = "basic_cost")
    private int basic_cost;

    @Column(name = "stock_availability")
    private boolean stock_availability;

    @OneToMany(mappedBy = "cmodel")
    private List<Car> cars;

    public Cmodel() {}

    public Cmodel(int id, String brand, int basic_cost, boolean stock_availability) {
        this.id = id;
        this.brand = brand;
        this.basic_cost = basic_cost;
        this.stock_availability = stock_availability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getBasic_cost() {
        return basic_cost;
    }

    public void setBasic_cost(int basic_cost) {
        this.basic_cost = basic_cost;
    }

    public boolean isStock_availability() {
        return stock_availability;
    }

    public void setStock_availability(boolean stock_availability) {
        this.stock_availability = stock_availability;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cmodel cmodel = (Cmodel) o;
        return id == cmodel.id && basic_cost == cmodel.basic_cost && stock_availability == cmodel.stock_availability && Objects.equals(brand, cmodel.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, basic_cost, stock_availability);
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", basic_cost=" + basic_cost +
                ", stock_availability=" + stock_availability +
                '}';
    }

}
