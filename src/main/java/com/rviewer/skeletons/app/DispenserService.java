package com.rviewer.skeletons.app;

import com.rviewer.skeletons.domain.dto.DispenserUsageDTO;
import com.rviewer.skeletons.domain.exception.DispenserIsAlreadyStatus;
import com.rviewer.skeletons.domain.exception.DispenserNotFound;
import com.rviewer.skeletons.domain.mappers.DispenserMapper;
import com.rviewer.skeletons.domain.repo.DispenserCommandRepo;
import com.rviewer.skeletons.domain.repo.DispenserLogQueryRepo;
import com.rviewer.skeletons.domain.repo.DispenserQueryRepo;
import com.rviewer.skeletons.infrastructure.api.req.DispenserFlowReq;
import com.rviewer.skeletons.infrastructure.api.req.DispenserStatusReq;
import com.rviewer.skeletons.infrastructure.api.res.DispenserAmoutRes;
import com.rviewer.skeletons.infrastructure.api.res.DispenserRes;
import java.time.Duration;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DispenserService {

  private final Double COST_PER_LITER = 12.25;
  private final DispenserCommandRepo dispenserCommandRepo;

  private final DispenserQueryRepo dispenserQueryRepo;

  private final DispenserMapper dispenserMapper;

  private final DispenserLogQueryRepo dispenserLogQueryRepo;

  @Autowired
  public DispenserService(
      DispenserCommandRepo dispenserCommandRepo,
      DispenserQueryRepo dispenserQueryRepo,
      DispenserMapper dispenserMapper,
      DispenserLogQueryRepo dispenserLogQueryRepo) {
    this.dispenserCommandRepo = dispenserCommandRepo;
    this.dispenserQueryRepo = dispenserQueryRepo;
    this.dispenserMapper = dispenserMapper;
    this.dispenserLogQueryRepo = dispenserLogQueryRepo;
  }

  public DispenserAmoutRes retrieveAmout(Long id) {
    DispenserUsageDTO dtoRepo = dispenserQueryRepo.retrieve(id).orElseThrow(DispenserNotFound::new);
    List<DispenserUsageDTO> logDispenser = dispenserLogQueryRepo.retrieveAllById(id);
    logDispenser.add(dtoRepo);
    double totalAmount = 0.0;
    for (DispenserUsageDTO dispenser : logDispenser) {
      var amount = calculateUsageSpent(dispenser);
      dispenser.setTotalSpent(amount);
      totalAmount += amount;
    }
    return dispenserMapper.mapToRes(logDispenser, totalAmount);
  }

  public DispenserRes save(DispenserFlowReq dispenserFlowReq) {
    Long id = dispenserCommandRepo.save(dispenserMapper.mapToDto(dispenserFlowReq));

    return new DispenserRes(id, dispenserFlowReq.getFlowVolume());
  }

  public void changeStatus(DispenserStatusReq req, Long id) {
    DispenserUsageDTO dtoRepo = dispenserQueryRepo.retrieve(id).orElseThrow(DispenserNotFound::new);
    var oldStatus = dispenserMapper.mapToEnum(dtoRepo);
    var newStatus = req.getStatus();

    if (newStatus.equals(oldStatus)) {
      throw new DispenserIsAlreadyStatus("Dispenser is already opened/closed");
    }
    var dtoToUpdate = dispenserMapper.mapToDto(req, id);

    switch (newStatus) {
      case CLOSE -> dispenserCommandRepo.close(dtoToUpdate);
      case OPEN -> dispenserCommandRepo.open(dtoToUpdate);
    }
  }

  private double calculateUsageSpent(DispenserUsageDTO usage) {
    if (usage.getClosedAt() == null) {
      return 0.0;
    } else {
      long durationInSeconds =
          Duration.between(usage.getOpenedAt(), usage.getClosedAt()).getSeconds();
      double totalFlowVolume = durationInSeconds * usage.getFlowVolume();
      return totalFlowVolume * COST_PER_LITER;
    }
  }
}
