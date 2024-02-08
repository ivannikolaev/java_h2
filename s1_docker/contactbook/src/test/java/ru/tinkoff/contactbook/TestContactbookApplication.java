package ru.tinkoff.contactbook;

import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public class TestContactbookApplication {

	@Autowired
	private ContactRepository contactRepository;

	@Container
	@ServiceConnection
	static PostgreSQLContainer<?> postgres =  new PostgreSQLContainer<>(DockerImageName.parse("postgres:14"));

	@Test
	public void testContactbook() {
		Contact contact = new Contact();
		contact.setName("test");
		contact.setNumber("1234567890");

		contactRepository.save(contact);

		List<Contact> actualContacts = contactRepository.findByName("test");

		Assertions.assertThat(actualContacts).containsExactly(contact);

	}

}
