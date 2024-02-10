package com.rviewer.skeletons.infrastructure.api.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DispenserFlowReq {
  @JsonProperty("flow_volume")
  private Double flowVolume;

  public DispenserFlowReq() {}
}
