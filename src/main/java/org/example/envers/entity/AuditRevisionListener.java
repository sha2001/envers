package org.example.envers.entity;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object revisionEntity) {
        String username = Optional.ofNullable(SecurityContextHolder.getContext())
                .map(e -> e.getAuthentication())
                .map(Authentication::getName).orElse("Unknown user");
        AuditRevisionEntity audit = (AuditRevisionEntity) revisionEntity;
        audit.setUsername(username);
    }
}
