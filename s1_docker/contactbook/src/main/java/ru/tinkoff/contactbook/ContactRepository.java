package ru.tinkoff.contactbook;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "contacts", path = "contacts")
public interface ContactRepository extends CrudRepository<Contact, Long> {
    List<Contact> findByName(String name);
}
