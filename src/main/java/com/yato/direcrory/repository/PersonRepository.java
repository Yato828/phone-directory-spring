package com.yato.direcrory.repository;

import com.yato.direcrory.entity.Person;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class PersonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping
    public List<Person> findAll(){
        return entityManager
                .createQuery("FROM Person", Person.class)
                .getResultList();
    }

    public void edit(Person person){
        entityManager.merge(person);
    }

    public Person findById(int id) {
        return entityManager.find(Person.class, id);
    }

    public void update(Person person) {
        entityManager.merge(person);
    }

    public void save(Person person) {
        entityManager.persist(person);
    }

    public void delete(int id) {
        Person person = entityManager.find(Person.class, id);
        if (person != null)
        entityManager.remove(person);
    }

    public List<Person> findByFirstName(String name) {
        String sql = "SELECT * FROM contacts " +
        "WHERE LOWER(first_name) LIKE LOWER(:searchName) " +
                "OR LOWER(last_name) LIKE LOWER(:searchName) " +
                "OR LOWER(middle_name) LIKE LOWER(:searchName) "+
                "OR birth_date LIKE :searchName";
        return entityManager.createNativeQuery(sql, Person.class).setParameter("searchName", "%" + name + "%").getResultList();
    }
}
