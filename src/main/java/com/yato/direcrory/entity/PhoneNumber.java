package com.yato.direcrory.entity;

import javax.persistence.*;

@Entity
@Table(name="phone_numbers")
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private Person person;

    public PhoneNumber() {
    }

    public PhoneNumber(String number, String type, Person person) {
        this.number = number;
        this.type = type;
        this.person = person;
    }

    public Integer getId() {
        return Id;
    }
    public String getNumber(){
        return number;
    }

    public String getType(){
        return type;
    }

    public Person getPerson(){
        return person;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setNumber(String number){
        this.number = number;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setPerson(Person person){
        this.person = person;
    }

}

