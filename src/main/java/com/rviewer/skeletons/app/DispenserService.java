package com.rviewer.skeletons.app;

import com.rviewer.skeletons.domain.mappers.DispenserMapper;
import com.rviewer.skeletons.domain.repo.DispenserCommandRepo;
import com.rviewer.skeletons.domain.repo.DispenserQueryRepo;
import com.rviewer.skeletons.infrastructure.api.req.DispenserFlowReq;
import com.rviewer.skeletons.infrastructure.api.req.DispenserStatusReq;
import com.rviewer.skeletons.infrastructure.api.res.DispenserAmoutRes;
import com.rviewer.skeletons.infrastructure.api.res.DispenserRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DispenserService {


    private final DispenserCommandRepo dispenserCommandRepo;

    private final DispenserQueryRepo dispenserQueryRepo;

    private final DispenserMapper dispenserMapper;
    @Autowired
    public DispenserService(DispenserCommandRepo dispenserCommandRepo, DispenserQueryRepo dispenserQueryRepo, DispenserMapper dispenserMapper) {
        this.dispenserCommandRepo = dispenserCommandRepo;
        this.dispenserQueryRepo = dispenserQueryRepo;
        this.dispenserMapper = dispenserMapper;
    }

    public DispenserAmoutRes retrieveAmout(Long id) {
        dispenserQueryRepo.retrieve(id);
        return null;
    }

    public DispenserRes save(DispenserFlowReq dispenserFlowReq) {
        Long id = dispenserCommandRepo.save(dispenserMapper.mapToDto(dispenserFlowReq));

        return new DispenserRes(id, dispenserFlowReq.getFlowVolume());
    }

    public void changeStatus(DispenserStatusReq dispenserStatusReq, Long id) {
        dispenserCommandRepo.updateStatus();
    }
}
