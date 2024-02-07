package com.rviewer.skeletons.infrastructure.api;

import com.rviewer.skeletons.app.DispenserService;
import com.rviewer.skeletons.infrastructure.api.res.AmoutResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dispenser")
public class DispenserController {

  @Autowired private DispenserService dispenserService;

  @GetMapping("/:id/spending")
  public ResponseEntity<AmoutResponse> retrieveAmout(@PathVariable Long id) {
    return ResponseEntity.ok(dispenserService.retrieveAmout(id));
  }

  @PostMapping()
  public ResponseEntity<?> save() {
    dispenserService.save();
    return ResponseEntity.noContent().build();
  }
}
