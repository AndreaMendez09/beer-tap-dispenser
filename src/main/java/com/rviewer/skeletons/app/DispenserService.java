package com.rviewer.skeletons.app;

import com.rviewer.skeletons.domain.dto.DispenserUsageDTO;
import com.rviewer.skeletons.domain.enums.DispenserStatusEnum;
import com.rviewer.skeletons.domain.exception.DispenserIsAlreadyStatus;
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
        DispenserUsageDTO dtoRepo = dispenserQueryRepo.retrieve(id);
        return dispenserMapper.mapToRes(dtoRepo);
    }

    public DispenserRes save(DispenserFlowReq dispenserFlowReq) {
        Long id = dispenserCommandRepo.save(dispenserMapper.mapToDto(dispenserFlowReq));

        return new DispenserRes(id, dispenserFlowReq.getFlowVolume());
    }

    public void changeStatus(DispenserStatusReq req, Long id) {
        DispenserUsageDTO dtoRepo = dispenserQueryRepo.retrieve(id);
        var actualStatus = dispenserMapper.mapToEnum(dtoRepo);

        if (actualStatus.equals(req.getStatus())) {
            throw new DispenserIsAlreadyStatus("Dispenser is already opened/closed");
        }
        var dtoToUpdate = dispenserMapper.mapToDto(req, id);
        if (req.getStatus().equals(DispenserStatusEnum.CLOSE)) {
            return new DispenserUsageDTO(id, null, req.getUpdatedAt() ,null, null);
        }
        return new DispenserUsageDTO(id, req.getUpdatedAt(), null, null, null);
        dispenserCommandRepo.updateStatus(dtoToUpdate);
    }
}
