package com.hexagonal.tasks.domain.ports.out;

import com.hexagonal.tasks.domain.model.AdditionalTaskInfo;

public interface ExternalServicePort {

    AdditionalTaskInfo getAdditionalTaskInfo(Long taskId);
}
