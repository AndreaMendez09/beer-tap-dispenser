package com.rviewer.skeletons.domain.repo;

import com.rviewer.skeletons.domain.dto.DispenserUsageDTO;

public interface DispenserQueryRepo {
    public DispenserUsageDTO retrieve(Long id);
}
