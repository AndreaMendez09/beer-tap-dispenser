package com.rviewer.skeletons.infrastructure.data.repo;

import com.rviewer.skeletons.domain.dto.DispenserUsageDTO;
import com.rviewer.skeletons.domain.repo.DispenserLogQueryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DispenserLogQueryRepository implements DispenserLogQueryRepo {
  private JdbcTemplate jdbcTemplate;

  @Autowired
  public DispenserLogQueryRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<DispenserUsageDTO> retrieveAllById(Long id) {
    String sql = "SELECT * FROM dispenser_log WHERE entity_id = ?";
    return jdbcTemplate.query(
        sql, new Object[] {id}, new BeanPropertyRowMapper<>(DispenserUsageDTO.class));
  }
}
