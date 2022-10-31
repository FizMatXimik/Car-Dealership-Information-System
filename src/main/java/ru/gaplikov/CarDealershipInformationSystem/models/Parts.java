package ru.gaplikov.CarDealershipInformationSystem.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Parts")
public class Parts {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Название должно быть заполнено")
    @Column(name = "part_name")
    private String part_name;

    @Column(name = "part_cost")
    private int part_cost;

    @Column(name = "installing_cost")
    private int installing_cost;

    @ManyToMany(mappedBy = "parts")
    private List<Car> cars;

    public Parts() {}

    public Parts(int id, String part_name, int part_cost, int installing_cost) {
        this.id = id;
        this.part_name = part_name;
        this.part_cost = part_cost;
        this.installing_cost = installing_cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPart_name() {
        return part_name;
    }

    public void setPart_name(String part_name) {
        this.part_name = part_name;
    }

    public int getPart_cost() {
        return part_cost;
    }

    public void setPart_cost(int part_cost) {
        this.part_cost = part_cost;
    }

    public int getInstalling_cost() {
        return installing_cost;
    }

    public void setInstalling_cost(int installing_cost) {
        this.installing_cost = installing_cost;
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
        Parts that = (Parts) o;
        return id == that.id && part_cost == that.part_cost && installing_cost == that.installing_cost && Objects.equals(part_name, that.part_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, part_name, part_cost, installing_cost);
    }

    @Override
    public String toString() {
        return "SpareParts{" +
                "id=" + id +
                ", part_name='" + part_name + '\'' +
                ", part_cost=" + part_cost +
                ", installing_cost=" + installing_cost +
                '}';
    }
}

