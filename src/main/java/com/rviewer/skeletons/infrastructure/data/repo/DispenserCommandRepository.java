package com.rviewer.skeletons.infrastructure.data.repo;

import com.rviewer.skeletons.domain.dto.DispenserUsageDTO;
import com.rviewer.skeletons.domain.repo.DispenserCommandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DispenserCommandRepository implements DispenserCommandRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void save(DispenserUsageDTO t) {

    }

    @Override
    public void updateStatus(Long id) {

    }
}
