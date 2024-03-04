package fr.seasoning.profile_ms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "profile")
public class Experience {
    @Id
    private Integer id;

    private String title;

    private String company;

    private Date startDate;

    private Date endDate;

    public Experience(){}

    public Experience(Integer id, String title, String company, Date startDate, Date endDate){
        this.id = id;
        this.title = title;
        this.company = company;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
