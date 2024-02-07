package com.rviewer.skeletons.infrastructure.data.repo;

import com.rviewer.skeletons.domain.dto.DispenserUsageDTO;
import com.rviewer.skeletons.domain.repo.DispenserQueryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class DispenserQueryRepository implements DispenserQueryRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public DispenserUsageDTO retrieve(Long id) {
        return null;
    }
}
