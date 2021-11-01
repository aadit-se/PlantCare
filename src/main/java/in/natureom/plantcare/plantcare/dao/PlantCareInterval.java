package in.natureom.plantcare.plantcare.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "plant_care_interval")
@NoArgsConstructor
public class PlantCareInterval {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "plant_category_id")
	public int plantCategoryId;

	@Column(name = "plant_category_name")
	public String plantCategoryName;

	@Column(name = "watering_interval")
	public int wateringInterval;

	@Column(name = "cleaning_interval")
	public int cleaningInterval;

	@Column(name = "trimming_interval")
	public int trimmingInterval;

	@Column(name = "fertilization_interval")
	public int fertilizationInterval;

	@Column(name = "soil_cultivation_interval")
	public int soilCultivationInterval;

	@Column(name = "pesticide_spray_interval")
	public int pesticideSprayInterval;

	public PlantCareInterval(String plantCategoryName, int wateringInterval, int cleaningInterval, int trimmingInterval, int fertilizationInterval, int soilCultivationInterval, int pesticideSprayInterval) {
		this.plantCategoryName = plantCategoryName;
		this.wateringInterval = wateringInterval;
		this.cleaningInterval = cleaningInterval;
		this.trimmingInterval = trimmingInterval;
		this.fertilizationInterval = fertilizationInterval;
		this.soilCultivationInterval = soilCultivationInterval;
		this.pesticideSprayInterval = pesticideSprayInterval;
	}

}
