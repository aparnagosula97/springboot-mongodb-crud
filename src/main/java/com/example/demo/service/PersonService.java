package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;


import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class PersonService {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    private final PersonDao personDao;
    private final SequenceGeneratorService service;


    public PersonService(@Autowired PersonDao personDao,
                         @Autowired SequenceGeneratorService service) {
        this.personDao = personDao;
        this.service = service;
    }

    public Person insertPersonData(Person person) {
        person.setId(String.valueOf(service.getSequenceNumber(SEQUENCE_NAME)));
        return personDao.insertPersonData(person);
    }

    public Collection<Person> getAllPersonInformation() {
        return personDao.getAllPersonInformation();
    }

    public Optional<Person> getPersonInformationUsingId(String id) {
        return personDao.getPersonInformationById(id);
    }

    public void updatePersonUsingId(String id, Person person) {
         personDao.updatePersonUsingId(id, person);
    }

    public void deletePersonUsingId(String id) {
        personDao.deletePersonUsingId(id);
    }
}
