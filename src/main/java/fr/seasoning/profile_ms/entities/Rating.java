package fr.seasoning.profile_ms.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "profile")
public class Rating {
    @Id
    private Integer id;

    private Integer stars;

    private String comment;

    public Rating(){}

    public Rating(Integer id, Integer stars, String comment){
        this.id = id;
        this.stars = stars;
        this.comment = comment;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
