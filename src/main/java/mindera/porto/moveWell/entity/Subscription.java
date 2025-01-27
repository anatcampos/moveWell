package mindera.porto.moveWell.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameOfSubscription;

    private Double priceOfSubscription;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToOne(mappedBy = "subscription")
    private User user;

    public Subscription() {
    }

    public Subscription(Long id, String nameOfSubscription, Double priceOfSubscription, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.nameOfSubscription = nameOfSubscription;
        this.priceOfSubscription = priceOfSubscription;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getNameOfSubscription() {
        return nameOfSubscription;
    }

    public void setNameOfSubscription(String nameOfSubscription) {
        this.nameOfSubscription = nameOfSubscription;
    }

    public Double getPriceOfSubscription() {
        return priceOfSubscription;
    }

    public void setPriceOfSubscription(Double priceOfSubscription) {
        this.priceOfSubscription = priceOfSubscription;
    }
}
