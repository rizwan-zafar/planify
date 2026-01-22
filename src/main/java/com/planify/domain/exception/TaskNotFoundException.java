package com.planify.domain.exception;

import java.util.UUID;

public class TaskNotFoundException extends RuntimeException {

    private final UUID id;

    public TaskNotFoundException(UUID id) {

        super(String.format("Task with ID %s does not exit.", id));
        this.id = id;
    }
    public UUID getID()
    {
        return id;
    }
}
