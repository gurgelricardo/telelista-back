package br.rickgurgel.telelista.controllers;

import br.rickgurgel.telelista.entities.Contact;
import br.rickgurgel.telelista.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/telelista")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping(value = "/contatos")
    public ResponseEntity<List<Contact>> findAll(){
        List<Contact> contactsList = contactService.findAll();
        return ResponseEntity.ok().body(contactsList);
    }

    @GetMapping(value = "/contatos-alfabetica")
    public ResponseEntity<List<String>> findAllButShowOnlyNames(){
        List<String> contactsList = contactService.findAllContactNames();
        return ResponseEntity.ok().body(contactsList);
    }

    @GetMapping(value = "/contatos/{id}")
    public ResponseEntity<Contact> findById(@PathVariable UUID id){
        Contact contact = contactService.findById(id);
        return ResponseEntity.ok().body(contact);
    }

    @PostMapping(value = "/adicionar-contato")
    public ResponseEntity<Contact> insert(@RequestBody Contact obj){
        obj = contactService.insert(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        contactService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/atualizar/{id}")
    public ResponseEntity<Contact> update(@PathVariable UUID id){
        Contact contact = contactService.updateContact(id);
        return ResponseEntity.ok().body(contact);
    }


}
