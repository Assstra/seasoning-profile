package fr.seasoning.profile_ms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "profile")
public class Reference {
    @Id
    private Integer id;

    private String name;

    private String surname;

    private String phone;

    private String email;

    public Reference(){}

    public Reference(Integer id, String name, String surname, String phone, String email){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
