package com.rviewer.skeletons.domain.repo;

import com.rviewer.skeletons.domain.dto.DispenserUsageDTO;

public interface DispenserCommandRepo {

  public Long save(DispenserUsageDTO t);

  public void open(DispenserUsageDTO t);

  public void close(DispenserUsageDTO t);
}
