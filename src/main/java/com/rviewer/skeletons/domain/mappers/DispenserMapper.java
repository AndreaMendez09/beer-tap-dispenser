package com.rviewer.skeletons.domain.mappers;

import com.rviewer.skeletons.domain.dto.DispenserUsageDTO;
import com.rviewer.skeletons.infrastructure.api.req.DispenserFlowReq;
import com.rviewer.skeletons.infrastructure.api.res.DispenserRes;
import org.springframework.stereotype.Component;

@Component
public class DispenserMapper {
  public DispenserRes mapToRes(Long id, DispenserUsageDTO dto) {
    return new DispenserRes(id, dto.getFlowVolume());
  }

  public DispenserUsageDTO mapToDto(DispenserFlowReq dispenserFlowReq) {
    return new DispenserUsageDTO(null, null, null, dispenserFlowReq.getFlowVolume(), null);
  }
}
