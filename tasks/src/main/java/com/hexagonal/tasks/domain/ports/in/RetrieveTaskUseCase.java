package com.hexagonal.tasks.domain.ports.in;

import com.hexagonal.tasks.domain.model.Task;

import java.util.List;
import java.util.Optional;

public interface RetrieveTaskUseCase {

    Optional<Task> getTask(Long id);

    List<Task> getAllTask();
}
