package org.example.envers.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "test")
@Audited(withModifiedFlag = true)
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Person implements Serializable {

 @Id
 @GeneratedValue
 private UUID id;

 private String firstname;
 private String lastname;
 private LocalDate birthdate;
 @NotAudited
 @OneToMany
 @JoinColumn(name = "person_id")
 private List<Address> addresses;


 @CreatedBy
 private String createdBy;
 @LastModifiedBy
 private String updatedBy;
 @CreatedDate
 private LocalDateTime createdOn;
 @LastModifiedDate
 private LocalDateTime updateOn;
}
