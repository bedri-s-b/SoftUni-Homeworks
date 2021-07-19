package com.example.dtoexercise.model.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Set;

@Entity(name = "orders")
public class Order extends BaseEntity {
    private User buyer;
    private Set<Game> games;

    public Order() {
    }

    @ManyToOne
    @Cascade(CascadeType.REMOVE)
    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    @ManyToMany
    @Cascade(CascadeType.REMOVE)
    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}
