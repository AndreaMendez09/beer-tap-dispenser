package com.rviewer.skeletons.app;

import com.rviewer.skeletons.domain.repo.DatabaseConnector;
import com.rviewer.skeletons.domain.repo.DispenserCommandRepo;
import com.rviewer.skeletons.domain.repo.DispenserQueryRepo;
import com.rviewer.skeletons.infrastructure.api.res.AmoutResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DispenserService {

    @Autowired
    private final DispenserCommandRepo dispenserCommandRepo;

    @Autowired
    private final DispenserQueryRepo dispenserQueryRepo;

    public DispenserService(DispenserCommandRepo dispenserCommandRepo, DispenserQueryRepo dispenserQueryRepo) {
        this.dispenserCommandRepo = dispenserCommandRepo;
        this.dispenserQueryRepo = dispenserQueryRepo;
    }

    public AmoutResponse retrieveAmout(Long id) {
        dispenserQueryRepo.retrieve(id);
        return null;
    }

    public AmoutResponse save() {
        //dispenserCommandRepo.save();
        return null;
    }

}
