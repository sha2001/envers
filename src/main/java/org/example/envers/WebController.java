package org.example.envers;


import lombok.AllArgsConstructor;
import org.example.envers.entity.Person;
import org.example.envers.entity.RevisionService;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/persons")
@AllArgsConstructor
public class WebController {


    private final PersonRepository personRepository;

    private final RevisionService revisionService;
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        return ResponseEntity.ok(personRepository.save(person));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") UUID id, @RequestBody Person person) {

       return personRepository.findById(id).map(p -> {
            person.setId(id);
            return ResponseEntity.ok(personRepository.save(person));
        }).orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping
    ResponseEntity<List<Person>> getAllPersons() {
        return ResponseEntity.ok(personRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable("id") UUID id) {
        return personRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/{id}/revisions")
    public ResponseEntity<?> getRevisions(@PathVariable("id") UUID id, @RequestParam(value = "changes", defaultValue = "true") boolean changes){

        List<?> revisions = revisionService.getRevisions(id, changes);
        System.out.println(revisions);
        return ResponseEntity.ok(revisions);

    }
}
