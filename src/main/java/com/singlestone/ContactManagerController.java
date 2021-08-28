package com.singlestone;

import jdk.nashorn.internal.codegen.CompilerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;

@RestController
public class ContactManagerController {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private PhoneNumberRepository phoneNumberRepository;
    @Autowired
    private CallListService callListService;

    @GetMapping("/contacts")
    public ResponseEntity<Contact[]> getContacts() {
       return ResponseEntity.ok(contactRepository.findAll().toArray(new Contact[1]));
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable long id){
        Optional<Contact> optionalContact = contactRepository.findById(id);
        Name name = new Name();
        if(optionalContact.isPresent())
            return ResponseEntity.ok(optionalContact.get());
        else
           return ResponseEntity.notFound().build();
    }

    @GetMapping("/contacts/call-list")
    public CallListObj[] getCallList(){
        List<PhoneNumber> phoneNumbers = phoneNumberRepository.findByType("home");
        return callListService.convertContactsToCallListObj(phoneNumbers).toArray(new CallListObj[1]);
    }

    @PostMapping("/contacts")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact){
        Contact contactEnt = new Contact(contact);
        Contact savedContact = contactRepository.save(contactEnt);
        return ResponseEntity.ok(savedContact);
    }

    @DeleteMapping("/contacts/{id}")
    public void deleteContact(@PathVariable long id){
        contactRepository.deleteById(id);
    }

    @PutMapping("/contacts/{id}")
    public ResponseEntity<Object> updateContact(@RequestBody Contact contact, @PathVariable long id){
        Optional<Contact> optionalContact = contactRepository.findById(id);

        if(!optionalContact.isPresent())
            return ResponseEntity.notFound().build();

        contact.setId(id);

        for(PhoneNumber phoneNumber : contact.getPhone())
            phoneNumber.setContact(contact);

        Contact savedContact = contactRepository.save(contact);
        return ResponseEntity.ok(savedContact);
    }
}
