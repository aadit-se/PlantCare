package in.natureom.plantcare.plantspace.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import in.natureom.plantcare.usermgmt.dao.NatureOmUserRole;
import lombok.Data;


@Entity
@Data
@Table(name = "plant_space")
public class PlantSpace {

    @Id
    @Column(name = "plant_space_id")
    private Long plantSpaceId;

    @Column(name = "plant_space_name")
    private String plantSpaceName;

    @Column(name = "plant_space_address")
    private String plantSpaceAddress;

    @Column(name = "plant_space_desc")
    private String plantSpaceDesc;

    @Column(name = "plant_space_landmark")
    private String plantSpaceLandmark;

    @OneToOne
    @JoinColumn(name = "plant_space_customer_id")
    private NatureOmUserRole plantSpaceCustomerId;

    @Column(name = "plant_space_customer_name")
    private String plantSpaceCustomerName;

    @OneToOne
    @JoinColumn(name = "plant_space_plantmaster_id")
    private NatureOmUserRole plantSpacePlantmasterId;

    @Column(name = "plant_space_plantmaster_name")
    private String plantSpacePlantmasterName;

    @Column(name = "plant_space_status")
    private String plantSpaceStatus;

}
