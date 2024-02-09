package com.rviewer.skeletons.domain.mappers;

import com.rviewer.skeletons.domain.dto.DispenserUsageDTO;
import com.rviewer.skeletons.domain.enums.DispenserStatusEnum;
import com.rviewer.skeletons.infrastructure.api.req.DispenserFlowReq;
import com.rviewer.skeletons.infrastructure.api.req.DispenserStatusReq;
import com.rviewer.skeletons.infrastructure.api.res.DispenserAmoutRes;
import org.springframework.stereotype.Component;

@Component
public class DispenserMapper {
  public DispenserAmoutRes mapToRes(DispenserUsageDTO dto) {
    //return new DispenserAmoutRes(id, dto.getFlowVolume());
    return null;
  }

  public DispenserUsageDTO mapToDto(DispenserFlowReq dispenserFlowReq) {
    return new DispenserUsageDTO(null, null, null, dispenserFlowReq.getFlowVolume(), null);
  }

  public DispenserStatusEnum mapToEnum (DispenserUsageDTO dto) {
    if (dto.getClosedAt() == null && dto.getOpenedAt() == null) {
      return null;
    }
    if (dto.getClosedAt() == null) {
      return DispenserStatusEnum.CLOSE;
    }
    return DispenserStatusEnum.OPEN;
  }

  public DispenserUsageDTO mapToDto(DispenserStatusReq req, Long id) {
    if (req.getStatus().equals(DispenserStatusEnum.CLOSE)) {
      return new DispenserUsageDTO(id, null, req.getUpdatedAt() ,null, null);
    }
    return new DispenserUsageDTO(id, req.getUpdatedAt(), null, null, null);
  }
}
