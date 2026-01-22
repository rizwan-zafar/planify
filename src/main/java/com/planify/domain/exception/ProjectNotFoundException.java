package com.planify.domain.exception;

import java.util.UUID;

public class ProjectNotFoundException extends RuntimeException {

  private final UUID id;
    public ProjectNotFoundException(UUID id) {
      super(String.format("Project with ID %s does not exit.", id));
      this.id = id;
      ;
    }

  public UUID getId() {
    return id;
  }
}
