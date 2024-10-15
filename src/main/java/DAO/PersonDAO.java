/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author chamo
 */

import MODEL.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonDAO {
    private static final Logger logger = Logger.getLogger(PersonDAO.class.getName());

    private static List<Person> persons = new ArrayList<>();

    static {
        persons.add(new Person("1", "Tharu ", "077456789", "12, Bandaragama"));
        persons.add(new Person("2", "Ishini", "077654321", "45/6, Kaduwela"));
        // Add more persons as needed
    }

    public List<Person> getAllPersons() {
        logger.log(Level.INFO, "Fetching all persons");
        return persons;
    }

    public Person getPersonById(String id) {
        logger.log(Level.INFO, "Fetching person by ID: " + id);
        for (Person person : persons) {
            if (person.getId().equals(id)) {
                logger.log(Level.INFO, "Person found with ID: " + id);
                return person;
            }
        }
        logger.log(Level.WARNING, "Person not found with ID: " + id);
        return null;
    }

    public void addPerson(Person person) {
        logger.log(Level.INFO, "Adding new person: " + person);
        persons.add(person);
    }

    public void updatePerson(Person updatedPerson) {
        logger.log(Level.INFO, "Updating person with ID: " + updatedPerson.getId());
        for (int i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);
            if (person.getId().equals(updatedPerson.getId())) {
                logger.log(Level.INFO, "Person found with ID: " + updatedPerson.getId() + ", updating...");
                persons.set(i, updatedPerson);
                logger.log(Level.INFO, "Person updated: " + updatedPerson);
                return;
            }
        }
        logger.log(Level.WARNING, "Person not found with ID: " + updatedPerson.getId() + ", update failed");
    }

    public void deletePerson(String id) {
        logger.log(Level.INFO, "Deleting person with ID: " + id);
        persons.removeIf(person -> person.getId().equals(id));
    }
}