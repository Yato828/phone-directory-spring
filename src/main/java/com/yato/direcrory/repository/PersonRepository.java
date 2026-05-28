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

    public List<Person> search(String query) {
        String sql = "SELECT * FROM contacts " +
                "WHERE LOWER(first_name) LIKE LOWER(:query) " +
                "OR LOWER(last_name) LIKE LOWER(:query) " +
                "OR LOWER(middle_name) LIKE LOWER(:query) ";

        if (query != null && query.contains(".") && query.length() == 10) {
            String[] parts = query.split("\\.");
            String dateForSql = parts[2] + "-" + parts[1] + "-" + parts[0];
            sql += "OR birth_date = '" + dateForSql + "' ";
        }

        return entityManager.createNativeQuery(sql, Person.class)
                .setParameter("query", "%" + query + "%")
                .getResultList();
    }

    public List<Person> searchByNumber(String number) {
        String sql = "SELECT * FROM contacts " +
                "WHERE id IN (SELECT contact_id FROM phone_numbers WHERE number LIKE :number)";

        return entityManager.createNativeQuery(sql, Person.class)
                .setParameter("number", "%" + number + "%")
                .getResultList();
    }
}
