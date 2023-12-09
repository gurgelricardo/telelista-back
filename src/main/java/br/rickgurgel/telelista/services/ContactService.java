package br.rickgurgel.telelista.services;

import br.rickgurgel.telelista.entities.Contact;
import br.rickgurgel.telelista.repositories.ContactRepository;
import br.rickgurgel.telelista.services.exceptions.ResourceNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> findAll(){
        return contactRepository.findAll();
    }

    public List<String> findAllContactNames(){
        List<Contact> contacts = contactRepository.findAll();
        return contacts.stream()
                .map(Contact::getName)
                .sorted()
                .collect(Collectors.toList());
    }

    public Contact findById(UUID id){
        Optional<Contact> obj = contactRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Contact insert(Contact obj){
        return contactRepository.save(obj);
    }

    public void delete(UUID id){
        try{
            contactRepository.deleteById(id);
        } catch (ObjectNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
        contactRepository.deleteById(id);
    }

    public Contact updateContact(UUID id){
        Optional<Contact> obj = contactRepository.findById(id);
        Contact contact = new Contact(id,
                obj.get().getName(),
                obj.get().getNumber(),
                obj.get().getEmail()
                );
        return contact;
    }
}
