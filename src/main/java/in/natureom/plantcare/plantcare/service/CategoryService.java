package in.natureom.plantcare.plantcare.service;

import in.natureom.plantcare.plantcare.dao.PlantCareInterval;
import in.natureom.plantcare.plantcare.dao.PlantCareIntervalRepo;
import in.natureom.plantcare.plantcare.dto.CategoryDto;
import in.natureom.plantcare.plantcare.dto.CategoryListDto;
import in.natureom.plantcare.util.NatureOmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static in.natureom.plantcare.util.CommonConstants.Success;
import static in.natureom.plantcare.util.CommonConstants.Failed;

@Service
@Slf4j
public class CategoryService {

    @Autowired
    PlantCareIntervalRepo dao;

    public void addPlantCareInterval(List<PlantCareInterval> plantCareIntervals) {

        try {
            dao.saveAll(plantCareIntervals);
        } catch (Exception e) {
            log.error("Adding Plant care interval error:" + e.getMessage());
        }
    }

    public String addCategory(CategoryDto categoryDto) {
        log.info("in addCategoryService");
        try {
            if (!NatureOmUtil.isObjectValid(categoryDto))
                throw new Exception("Request category detail is empty");
            dao.save(categoryDtoToModel(categoryDto));
            log.info("Category is added"+categoryDto);
            log.debug("added object=" + categoryDto);
            return Success;
        } catch (Exception e) {
            log.error(e.getMessage());
            return Failed;
        }
    }

    public List<CategoryDto> getCategories() {
        log.info("in get categories");
        try {
            List<PlantCareInterval> categories = (List<PlantCareInterval>) dao.findAll();
            if (!NatureOmUtil.isObjectValid(categories)) {
                throw new Exception("Category List is empty");
            }
            List<CategoryDto> categoryDtos = new ArrayList<CategoryDto>();
            categories.forEach((category) -> {
                categoryDtos.add(categoryModelTODto(category));
            });
            log.debug("fetched list=", categoryDtos);
            return categoryDtos;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public List<CategoryListDto> getCategoryList() {
        log.info("in getCategoryList");
        try {
            List<PlantCareInterval> categories = (List<PlantCareInterval>) dao.findAll();
            if (!NatureOmUtil.isObjectValid(categories)) {
                throw new Exception("Category List is empty");
            }
            List<CategoryListDto> categoryListDtos = new ArrayList<CategoryListDto>();
            categories.forEach((category) -> {
                categoryListDtos.add(categoryModelTOListDto(category));
            });
            log.debug("fetched list=", categoryListDtos);
            return categoryListDtos;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }


    public CategoryDto getCategory(int categoryId) {
        log.info("in get category");
        try {

        	CategoryDto categoryDto = categoryModelTODto(dao.findById(categoryId).get());
            System.out.println(categoryDto);
            if (!NatureOmUtil.isObjectValid(categoryDto))
                throw new Exception("Category detail not found");
            log.info("object was fetched");
            log.debug("fetched object=", categoryDto);
            return categoryDto;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public String updateCategory(CategoryDto categoryDto) {
        log.info("in update category");
        try {
//            if (!dao.findById(categoryDto.getPlantCategoryId()).isPresent())
//                throw new Exception("No category found");
            if (!NatureOmUtil.isObjectValid(categoryDto))
                throw new Exception("invalid object");
            PlantCareInterval category = dao.save(categoryDtoToModel(categoryDto));
            log.info("Category is updated");
            log.debug("updated object=", category);
            return Success;
        } catch (Exception e) {
            log.info(e.getMessage());
            return Failed;
        }
    }

    public String deleteCategory(int categoryId) {
        try {
            log.info("in deleteCategory");
            if (!dao.findById(categoryId).isPresent())
                throw new Exception("No Object with such categoryId exist");
            dao.deleteById(categoryId);
            log.info("Category was deleted");
            log.debug("object deleted was with id={}", categoryId);
            return Success;
        } catch (Exception e) {
            log.error(e.getMessage());
            return Failed;
        }
    }

    public PlantCareInterval categoryDtoToModel(CategoryDto categoryDto) {
        log.debug("in categoryDtoToModel");
        PlantCareInterval category = new PlantCareInterval();
       // category.setPlantCategoryName(categoryDto.getPlantCatName());
        category.setPlantCategoryId(categoryDto.getPlantCatId());
//        category.setTrimmingInterval(categoryDto.getTrimInterval());
//        category.setWateringInterval(categoryDto.getWaterInterval());
//        category.setPesticideSprayInterval(categoryDto.getPesticideInterval());
//        category.setFertilizationInterval(categoryDto.getFertilizeInterval());
//        category.setSoilCultivationInterval(categoryDto.getSoilCultivateInterval());
//        category.setCleaningInterval(categoryDto.getCleanInterval());
        log.debug("object was converted from DTO object to Model");
        return category;

    }

    public CategoryDto categoryModelTODto(PlantCareInterval category) {
        log.debug("in categoryModelTODto");
        CategoryDto categoryDto = new CategoryDto();
      //  categoryDto.setPlantCatName(category.getPlantCategoryName());
        categoryDto.setPlantCatId(category.getPlantCategoryId());
//        categoryDto.setTrimInterval(category.getTrimmingInterval());
//        categoryDto.setWaterInterval(category.getWateringInterval());
//        categoryDto.setPesticideInterval(category.getPesticideSprayInterval());
//        categoryDto.setFertilizeInterval(category.getFertilizationInterval());
//        categoryDto.setSoilCultivateInterval(category.getSoilCultivationInterval());
        log.debug("object was converted from DTO object to Model");
        return categoryDto;

    }

    public CategoryListDto categoryModelTOListDto(PlantCareInterval category) {
        log.debug("in categoryModelTODto");
        CategoryListDto categoryDto = new CategoryListDto();
        categoryDto.setPlantCategoryName(category.getPlantCategoryName());
        categoryDto.setPlantCategoryId(category.getPlantCategoryId());
        log.debug("object was converted from DTO object to Model");
        return categoryDto;
    }
}
