package ru.gaplikov.CarDealershipInformationSystem.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Manager")
public class Manager {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "ФИО должны быть заполнены")
    @Column(name = "fio")
    private String fio;

    @Column(name = "employment_date")
    private Date employment_date;

    @Column(name = "number_sold_cars")
    private int number_sold_cars;

    @Column(name = "work_experience")
    private String work_experience;

    @OneToMany(mappedBy = "manager")
    private List<Car> cars;

    public Manager(int id, String fio, Date employment_date, int number_sold_cars, String work_experience) {
        this.id = id;
        this.fio = fio;
        this.employment_date = employment_date;
        this.number_sold_cars = number_sold_cars;
        this.work_experience = work_experience;
    }

    public Manager() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Date getEmployment_date() {
        return employment_date;
    }

    public void setEmployment_date(Date employment_date) {
        this.employment_date = employment_date;
    }

    public int getNumber_sold_cars() {
        return number_sold_cars;
    }

    public void setNumber_sold_cars(int number_sold_cars) {
        this.number_sold_cars = number_sold_cars;
    }

    public String getWork_experience() {
        return work_experience;
    }

    public void setWork_experience(String work_experience) {
        this.work_experience = work_experience;
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
        Manager manager = (Manager) o;
        return id == manager.id && number_sold_cars == manager.number_sold_cars && Objects.equals(fio, manager.fio) && Objects.equals(employment_date, manager.employment_date) && Objects.equals(work_experience, manager.work_experience);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, employment_date, number_sold_cars, work_experience);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", employment_date=" + employment_date +
                ", number_sold_cars=" + number_sold_cars +
                ", work_experience='" + work_experience + '\'' +
                '}';
    }
}
