package com.hexagonal.tasks.domain.ports.in;

import com.hexagonal.tasks.domain.model.Task;

public interface CreateTaskUseCase {

    Task created(Task task);
}
