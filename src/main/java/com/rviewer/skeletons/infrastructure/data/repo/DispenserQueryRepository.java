package com.rviewer.skeletons.infrastructure.data.repo;

import com.rviewer.skeletons.domain.dto.DispenserUsageDTO;
import com.rviewer.skeletons.domain.repo.DispenserQueryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DispenserQueryRepository implements DispenserQueryRepo {

  private JdbcTemplate jdbcTemplate;

  @Autowired
  public DispenserQueryRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public DispenserUsageDTO retrieve(Long id) {
    String sql = "SELECT * FROM dispenser WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, DispenserUsageDTO.class);
  }
}
