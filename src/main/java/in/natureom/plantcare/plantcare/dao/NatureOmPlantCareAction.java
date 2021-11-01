package in.natureom.plantcare.plantcare.dao;

import javax.persistence.*;


import lombok.Data;


@Data
@Entity
@Table(name = "natureom_plantcare_action")
public class NatureOmPlantCareAction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="Plantcare_action_id",nullable = false)
    private int plantCareActionId;

    @Column(name ="Plantcare_action_name")
    private String plantCareActionName;

    @Column(name ="Plantcare_action_description")
    private String plantCareActionDescription;

    public NatureOmPlantCareAction(String plantCareActionName, String plantCareActionDescription) {
        this.plantCareActionName = plantCareActionName;
        this.plantCareActionDescription = plantCareActionDescription;
    }

    public NatureOmPlantCareAction() {}
}
