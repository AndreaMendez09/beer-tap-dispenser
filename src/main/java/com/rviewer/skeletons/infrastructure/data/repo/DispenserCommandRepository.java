package com.rviewer.skeletons.infrastructure.data.repo;

import com.rviewer.skeletons.domain.dto.DispenserUsageDTO;
import com.rviewer.skeletons.domain.repo.DispenserCommandRepo;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class DispenserCommandRepository implements DispenserCommandRepo {

  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;

  @Autowired
  public DispenserCommandRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.simpleJdbcInsert =
        new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("dispenser")
            .usingGeneratedKeyColumns("id");
  }

  @Override
  public Long save(DispenserUsageDTO t) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("opened_at", t.getOpenedAt());
    parameters.put("closed_at", t.getClosedAt());
    parameters.put("flow_volume", t.getFlowVolume());
    parameters.put("total_spent", t.getTotalSpent());

    Number generatedId = simpleJdbcInsert.executeAndReturnKey(parameters);
    return generatedId.longValue();
  }

  @Override
  public void open(DispenserUsageDTO t) {
    String query =
        "UPDATE dispenser SET " + "opened_at = ?, " + "closed_at = NULL " + "WHERE id = ?";

    jdbcTemplate.update(query, t.getOpenedAt(), t.getId());
  }

  @Override
  public void close(DispenserUsageDTO t) {
    String query = "UPDATE dispenser SET " + "closed_at = ? " + "WHERE id = ?";

    jdbcTemplate.update(query, t.getClosedAt(), t.getId());
  }
}
