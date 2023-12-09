package br.rickgurgel.telelista.config;

import br.rickgurgel.telelista.entities.Contact;
import br.rickgurgel.telelista.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@org.springframework.context.annotation.Configuration
@Profile("test")
public class Configuration implements CommandLineRunner {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public void run (String... args) throws Exception{

        Contact ricardo = new Contact(null, "Ricardo Gurgel", "1234567890","ricardo@gurgel.br");
        Contact arielton = new Contact(null, "Arielton Nunes", "0987654321","arielton@nunes.br");
        Contact guanabara = new Contact(null, "Guanabara", "01928384756","arielton@nunes.br");

        contactRepository.saveAll(Arrays.asList(ricardo, arielton, guanabara));

    }
}
