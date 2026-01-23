package com.planify.domain.exception;

import java.util.UUID;

public class ManagerNotFoundException extends RuntimeException {

    private final UUID id;

    public ManagerNotFoundException(UUID id) {
        super(String.format("Manager with this id %s not found",id));
        this.id=id;
    }

    public UUID getId() {
        return id;
    }
}
