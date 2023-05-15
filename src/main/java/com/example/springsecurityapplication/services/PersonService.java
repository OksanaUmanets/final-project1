package com.example.springsecurityapplication.services;

import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.repositories.PersonRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
	private final PersonRepository personRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
		this.personRepository = personRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public Person findByLogin(Person person) {
		Optional<Person> person_db = personRepository.findByLogin(person.getLogin());
		return person_db.orElse(null);
	}

	@Transactional
	public void register(Person person) {
		person.setPassword(passwordEncoder.encode(person.getPassword()));
		person.setRole("ROLE_USER");
		personRepository.save(person);
	}

	// Данный метод позволяет получить список всех пользователей
	public List<Person> getAllPerson() {
		return  (List<Person>) personRepository.findAll();
	}

	// Данный метод позволяет получить пользователя по id
	public Person getPersonId(int id) {
		Optional<Person> optionalPerson = personRepository.findById(id);
		return optionalPerson.orElse(null);
	}

	// Данный метод позволяет обновить данные о пользователе

	@Transactional
	public void updatePerson(int id, Person person) {
		person.setId(id);
		person.setPassword(passwordEncoder.encode(person.getPassword()));
		personRepository.save(person);

	}

	@Transactional
	public void deletePerson(int id) {
		personRepository.deleteById(id);

	}

	

}
