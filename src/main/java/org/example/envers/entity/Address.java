package org.example.envers.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Audited
@Table(schema = "test")
public class Address {
    @Id
    @GeneratedValue
    private UUID id;

    private String street;
    private String postalCode;
    private String city;
    private String number;

    @ManyToOne
    private Person person;
}
