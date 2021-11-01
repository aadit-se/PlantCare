package in.natureom.plantcare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class NatureOmPlantCare {

	@Autowired
	private TestDataLoader loadDefaultData;

	public static void main(String[] args) {
		SpringApplication.run(NatureOmPlantCare.class, args);
	}

	@PostConstruct
	public void loadData() {
//		loadDefaultData.addIndiaStates();
		loadDefaultData.loadUserRoles();
//		loadDefaultData.loadPlantcareInterval();
//		loadDefaultData.loadPlantcareAction();
	}
	
}
	