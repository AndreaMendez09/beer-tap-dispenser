package com.rviewer.skeletons.infrastructure.api;

import com.rviewer.skeletons.app.DispenserService;
import com.rviewer.skeletons.infrastructure.api.req.DispenserFlowReq;
import com.rviewer.skeletons.infrastructure.api.req.DispenserStatusReq;
import com.rviewer.skeletons.infrastructure.api.res.DispenserAmoutRes;
import com.rviewer.skeletons.infrastructure.api.res.DispenserRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dispenser")
public class DispenserController {

  @Autowired private DispenserService dispenserService;

  @GetMapping("/:id/spending")
  public ResponseEntity<DispenserAmoutRes> retrieveAmout(@PathVariable Long id) {
    return ResponseEntity.ok(dispenserService.retrieveAmout(id));
  }

  @PostMapping()
  public ResponseEntity<DispenserRes> save(@RequestBody DispenserFlowReq dispenserFlowReq) {
    return ResponseEntity.ok(dispenserService.save(dispenserFlowReq));
  }

  @PutMapping("/:id/status")
  public ResponseEntity<?> changeStatus(@PathVariable Long id, @RequestBody DispenserStatusReq dispenserStatusReq) {
    dispenserService.changeStatus(dispenserStatusReq, id);
    return ResponseEntity.accepted().build();
  }
}
