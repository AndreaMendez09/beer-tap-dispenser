package com.rviewer.skeletons.domain.repo;


import com.rviewer.skeletons.domain.dto.DispenserUsageDTO;

public interface DispenserCommandRepo {

    public void save(DispenserUsageDTO t);

    public void updateStatus(Long id);


}
