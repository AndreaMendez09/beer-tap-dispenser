package com.rviewer.skeletons.infrastructure.data.repo;

import com.rviewer.skeletons.domain.dto.DispenserUsageDTO;
import com.rviewer.skeletons.domain.repo.DispenserQueryRepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
  public Optional<DispenserUsageDTO> retrieve(Long id) {
    String sql = "SELECT * FROM dispenser WHERE id = ?";
    try {
      return Optional.ofNullable(
          jdbcTemplate.queryForObject(
              sql, new Object[] {id}, new BeanPropertyRowMapper<>(DispenserUsageDTO.class)));
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }
}
