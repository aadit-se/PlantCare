package in.natureom.plantcare.plantspace.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import in.natureom.plantcare.usermgmt.dao.NatureOmUserRole;
import lombok.Data;

@Data
@Entity
@Table(name="plant_spot_log")
public class PlantSpaceSpotLog {

    @Id
    @Column(name = "log_id")
    private Long logId;

    @ManyToOne
    @JoinColumn(name = "spot_id")
    private PlantSpaceSpot plantSpotId;

    @Column(name = "spot_name")
    private String spotName;

    @ManyToOne
    @JoinColumn(name = "plant_master_id")
    private NatureOmUserRole plantMasterId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private NatureOmUserRole customerId;

    @Column(name = "action_done")
    private String actionDone;

    @Column(name = "action_timing")
    private Date actionTiming;
    
}
