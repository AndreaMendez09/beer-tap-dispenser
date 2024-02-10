package com.rviewer.skeletons.domain.repo;

import com.rviewer.skeletons.domain.dto.DispenserUsageDTO;

import java.util.Optional;

public interface DispenserQueryRepo {
    public Optional<DispenserUsageDTO> retrieve(Long id);
}
