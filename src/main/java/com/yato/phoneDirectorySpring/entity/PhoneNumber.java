package com.yato.phoneDirectorySpring.entity;

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
    private Contact contact;

    public PhoneNumber() {
    } // второй конструктор нужен для хибернейта


    public PhoneNumber(String number, String type, Contact contact) {
        this.number = number;
        this.type = type;
        this.contact = contact;


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

    public  Contact getContact(){
        return contact;
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

    public void setContact(Contact contact){
        this.contact = contact;
    }

}

