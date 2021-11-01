package in.natureom.plantcare.usermgmt.service;

import in.natureom.plantcare.plantcare.dao.NatureOmPlantCareAction;
import in.natureom.plantcare.plantcare.dao.NatureOmPlantactionRepo;
import in.natureom.plantcare.usermgmt.dto.PlantCareActionDto;
import in.natureom.plantcare.util.NatureOmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static in.natureom.plantcare.util.CommonConstants.Failed;
import static in.natureom.plantcare.util.CommonConstants.Success;

@Service
@Slf4j
public class PlantCareActionService {

    @Autowired
    NatureOmPlantactionRepo dao;

    public void addPlantCareAction(List<NatureOmPlantCareAction> plantCareActions) {

        try {
            dao.saveAll(plantCareActions);
        } catch (Exception e) {
            log.error("Adding Plant care action error:" + e.getMessage());
        }
    }

    public NatureOmPlantCareAction plantCareActionDtoToModel(PlantCareActionDto plantActionDto) {
        NatureOmPlantCareAction plantAction = new NatureOmPlantCareAction();
        plantAction.setPlantCareActionId(plantActionDto.getPlantCareActionId());
        plantAction.setPlantCareActionName(plantActionDto.getPlantCareActionName());
        plantAction.setPlantCareActionDescription(null);
        log.debug("in PlantCareActionService inside plantCareActionDtoToModel converting Dto to Model object");
        return plantAction;
    }

    public PlantCareActionDto plantCareActionModelToDto(NatureOmPlantCareAction plantAction) {
        PlantCareActionDto plantActionDto = new PlantCareActionDto();
        plantActionDto.setPlantCareActionId(plantAction.getPlantCareActionId());
        plantActionDto.setPlantCareActionName(plantAction.getPlantCareActionName());
        log.debug("in PlantCareActionService  inside plantCareActionModelToDto converting Model  to Dto object");
        return plantActionDto;
    }

    public String addPlantCareAction(PlantCareActionDto plantActionDto) {
        try {
            log.info("in addPlantCareAction, object being added");
            dao.save(plantCareActionDtoToModel(plantActionDto));
            log.info("object was added");
            log.debug("added object=", plantActionDto);
            return Success;
        } catch (Exception e) {
            log.error(e.getMessage());
            return Failed;
        }
    }

    public List<PlantCareActionDto> getPlantCareActions() {

        try {
            log.info("in get plantCareActions, list being fetched");
            List<NatureOmPlantCareAction> plantActions = (List<NatureOmPlantCareAction>) dao.findAll();
            if (!NatureOmUtil.isObjectValid(plantActions))
                throw new Exception("no such object");
            List<PlantCareActionDto> plantActionDtos = new ArrayList<PlantCareActionDto>();
            plantActions.forEach((action) -> {
                plantActionDtos.add(plantCareActionModelToDto(action));
            });
            log.info("list was fetched");
            log.debug("fetched list=", plantActionDtos);
            return plantActionDtos;
        } catch (Exception e) {
            return null;
        }
    }

    public PlantCareActionDto getPlantCareAction(int PlantCareActionId) {

        try {
            log.info("in get plantCareAction, object being fetched");
            NatureOmPlantCareAction plantAction = dao.findById(PlantCareActionId).get();
            if (!NatureOmUtil.isObjectValid(plantAction))
                throw new Exception("no such oject");
            log.info("object was fetched");
            log.debug("fetched object=", plantAction);
            return plantCareActionModelToDto(plantAction);
        } catch (Exception e) {
            return null;
        }
    }

}
