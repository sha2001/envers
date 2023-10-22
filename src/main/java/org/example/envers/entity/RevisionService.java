package org.example.envers.entity;

import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RevisionService {
    private final AuditReader auditReader;

    public List<?> getRevisions(UUID id, boolean fetchChanges) {
        AuditQuery auditQuery = null;

        if(fetchChanges) {
            auditQuery = auditReader.createQuery()
                    .forRevisionsOfEntityWithChanges(Person.class, true);
        }
        else {
            auditQuery = auditReader.createQuery()
                    .forRevisionsOfEntity(Person.class, true);
        }
        auditQuery.add(AuditEntity.id().eq(id));
        return auditQuery.getResultList();
    }
}
