package fr.seasoning.profile_ms.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "profile_id")
    private Integer id;
    private String ssoid;
    private String name;
    private String email;
    private String surname;
    private String phone;
    private String address;
    private String genre;
    private Date birthdate;
    private String photo;
    private String cv;
    private String biography;
    @OneToMany( targetEntity=Rating.class )
    private List ratings;
    @OneToMany( targetEntity=Experience.class )
    private List experiences;
    @OneToMany( targetEntity=Reference.class )
    private List references;

    public Profile(Integer id, String ssoid, String name, String surname, String email, String phone, String address, String genre, Date birthdate, String photo, String cv, String biography) {
        this.id = id;
        this.ssoid = ssoid;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.genre = genre;
        this.birthdate = birthdate;
        this.photo = photo;
        this.cv = cv;
        this.biography = biography;
    }

    public Profile() {
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSsoid() {
        return ssoid;
    }

    public void setSsoid(String ssoid) {
        this.ssoid = ssoid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List getRatings(){
        return ratings;
    }

    public void setRatings(List ratings){
        this.ratings = ratings;
    }

    public List getExperiences(){
        return experiences;
    }

    public void setExperiences(List experiences){
        this.experiences = experiences;
    }

    public List getReferences(){
        return references;
    }

    public void setReferences(List references){
        this.references = references;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}