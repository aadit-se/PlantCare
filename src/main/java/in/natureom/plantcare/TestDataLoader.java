package in.natureom.plantcare;

import in.natureom.plantcare.plantcare.dao.NatureOmPlantCareAction;
import in.natureom.plantcare.plantcare.dao.PlantCareInterval;
import in.natureom.plantcare.plantcare.service.CategoryService;
import in.natureom.plantcare.usermgmt.dao.NatureOmRole;
import in.natureom.plantcare.usermgmt.dao.State;
import in.natureom.plantcare.usermgmt.service.NomRoleService;
import in.natureom.plantcare.usermgmt.service.PlantCareActionService;
import in.natureom.plantcare.usermgmt.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestDataLoader {

    @Autowired
    CategoryService categoryService;

    @Autowired
    StateService stateService;

    @Autowired
    NomRoleService roleService;

    @Autowired
    PlantCareActionService plantCareActionService;

    /** Adding all Indian states in Master tables of States */
    public void addIndiaStates() {

        List<State> states = new ArrayList<>();
        states.add(new State("Jammu & Kashmir"));
        states.add(new State("Ladakh"));
        states.add(new State("Himachal Pradesh"));
        states.add(new State("Punjab"));
        states.add(new State("Uttarakhand"));
        states.add(new State("Haryana"));
        states.add(new State("Rajasthan"));
        states.add(new State("Uttar Pradesh"));
        states.add(new State("Bihar"));
        states.add(new State("West Bengal"));
        states.add(new State("Sikkim"));
        states.add(new State("Andhra Pradesh"));
        states.add(new State("Assam"));
        states.add(new State("Arunachal Pradesh"));
        states.add(new State("Meghalaya"));
        states.add(new State("Nagaland"));
        states.add(new State("Manipur"));
        states.add(new State("Tripura"));
        states.add(new State("Mizoram"));
        states.add(new State("Jharkhand"));
        states.add(new State("Gujarat"));
        states.add(new State("Madhya Pradesh"));
        states.add(new State("Maharashtra"));
        states.add(new State("Chhattisgarh"));
        states.add(new State("Odisha"));
        states.add(new State("Goa"));
        states.add(new State("Telangana"));
        states.add(new State("Karnataka"));
        states.add(new State("Andhra Pradesh"));
        states.add(new State("Kerala"));
        states.add(new State("Tamil Nadu"));
        states.add(new State("Andaman & Nicobar"));
        states.add(new State("Chandigarh"));
        states.add(new State("Diu, Daman & Dadra Nagar Haveli"));
        states.add(new State("Delhi"));
        states.add(new State("Lakshadweep"));
        states.add(new State("Puducherry"));
        stateService.addAllIndiaState(states);
    }

    /** Add user master roles */
    public void loadUserRoles() {

        List<NatureOmRole> natureOmRoles = new ArrayList<>();
        natureOmRoles.add(new NatureOmRole(101L,"admin","Supervises action of plant masters"));
        natureOmRoles.add(new NatureOmRole(200L,"customer","Owner of garden, using facilities from plant master"));
        natureOmRoles.add(new NatureOmRole(300L,"plantmaster","Take care of plants (gardner)"));
        roleService.addAllMasterRoles(natureOmRoles);
    }

    /** Add default plantcare intervals */
    public void loadPlantcareInterval() {

        List<PlantCareInterval> plantCareIntervals = new ArrayList<>();
        plantCareIntervals.add(new PlantCareInterval("tulsi", 2, 15, 25, 10, 18, 35));
        plantCareIntervals.add(new PlantCareInterval("aloevera", 7, 20, 30, 40, 35, 30));
        plantCareIntervals.add(new PlantCareInterval("rose", 2, 13, 20, 9, 16, 20));
        plantCareIntervals.add(new PlantCareInterval("tulip", 2, 17, 22, 9, 25, 45));
        categoryService.addPlantCareInterval(plantCareIntervals);
    }

    /** Add plantcare actions */
    public void loadPlantcareAction() {

        List<NatureOmPlantCareAction> plantCareActions = new ArrayList<>();
        plantCareActions.add(new NatureOmPlantCareAction("watering","Pour water to plants"));
        plantCareActions.add(new NatureOmPlantCareAction("trimming", "It trims plants"));
        plantCareActions.add(new NatureOmPlantCareAction("fertilizing","To fertilize plants"));
        plantCareActions.add(new NatureOmPlantCareAction("pesticide","Sprinkle pesticides to plants"));
        plantCareActions.add(new NatureOmPlantCareAction("soil cultivation","It cultivate the soil"));
        plantCareActionService.addPlantCareAction(plantCareActions);
    }

}
