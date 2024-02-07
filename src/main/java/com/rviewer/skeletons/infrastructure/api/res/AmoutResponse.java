package com.rviewer.skeletons.infrastructure.api.res;

import com.rviewer.skeletons.domain.dto.DispenserUsageDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AmoutResponse {

  private Double amount;

  private List<DispenserUsageDTO> usages;
}
