package com.rviewer.skeletons.infrastructure.api;

import com.rviewer.skeletons.app.DispenserService;
import com.rviewer.skeletons.infrastructure.api.req.DispenserFlowReq;
import com.rviewer.skeletons.infrastructure.api.req.DispenserStatusReq;
import com.rviewer.skeletons.infrastructure.api.res.DispenserAmoutRes;
import com.rviewer.skeletons.infrastructure.api.res.DispenserRes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dispenser")
public class DispenserController {

  private static final Logger LOG = LogManager.getLogger(DispenserController.class);

  private final DispenserService dispenserService;

  @Autowired
  public DispenserController(DispenserService dispenserService) {
    this.dispenserService = dispenserService;
  }

  @GetMapping("/{id}/spending")
  public ResponseEntity<DispenserAmoutRes> retrieveAmout(@PathVariable Long id) {
    LOG.info("Retrieve amount by dispenser id {}", id);
    return ResponseEntity.ok(dispenserService.retrieveAmout(id));
  }

  @PostMapping()
  public ResponseEntity<DispenserRes> save(@RequestBody DispenserFlowReq dispenserFlowReq) {
    LOG.info("Create new dispenser with flow {}", dispenserFlowReq.getFlowVolume());
    return ResponseEntity.ok(dispenserService.save(dispenserFlowReq));
  }

  @PutMapping("/{id}/status")
  public ResponseEntity<?> changeStatus(
      @PathVariable Long id, @RequestBody DispenserStatusReq dispenserStatusReq) {
    LOG.info("Change dispenser {} to status {}", id, dispenserStatusReq.getStatus());
    dispenserService.changeStatus(dispenserStatusReq, id);
    return ResponseEntity.accepted().build();
  }
}
