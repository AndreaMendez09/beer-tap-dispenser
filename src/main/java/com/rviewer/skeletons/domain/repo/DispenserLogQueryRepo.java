package com.rviewer.skeletons.domain.repo;

import com.rviewer.skeletons.domain.dto.DispenserUsageDTO;

import java.util.List;

public interface DispenserLogQueryRepo {
    public List<DispenserUsageDTO> retrieveAllById(Long id);
}
