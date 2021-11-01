package in.natureom.plantcare.plantcare.controller;

import java.util.List;

import in.natureom.plantcare.plantcare.dto.CategoryDto;
import in.natureom.plantcare.plantcare.dto.CategoryListDto;
import in.natureom.plantcare.plantcare.service.CategoryService;
import in.natureom.plantcare.util.CommonConstants;
import in.natureom.plantcare.util.NatureOmUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

import static in.natureom.plantcare.util.CommonConstants.Success;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping("/plant-care-interval")
public class PlantCareIntervalController {

    @Autowired
    CategoryService categoryService;

   
    @PostMapping("/addCategory/")
    public ResponseEntity<String> addCategory(@RequestBody CategoryDto categoryDto) {
        log.info(" inside addplantCareInterval, adding object"+categoryDto);
        String response = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(response, response.equals(CommonConstants.Success) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/categoryList/")
    public ResponseEntity<List<CategoryListDto>> getcategoriesList() {
        log.info("in getCategories");
        List<CategoryListDto> categoryListDto = categoryService.getCategoryList();
        return new ResponseEntity<>(categoryListDto, NatureOmUtil.isObjectValid(categoryListDto) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }


    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getcategories() {
        log.info("in getCategories");
        List<CategoryDto> categoryDtos = categoryService.getCategories();
        return new ResponseEntity<>(categoryDtos, NatureOmUtil.isObjectValid(categoryDtos) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") int categoryId) {
        log.info("in getCategory");
        CategoryDto categoryDto = categoryService.getCategory(categoryId);
        log.info("category=",categoryDto);
        return new ResponseEntity<>(categoryDto, NatureOmUtil.isObjectValid(categoryDto) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    @PatchMapping("/")
    public ResponseEntity<String> updateCategory(CategoryDto categoryDTO) {
        log.info("in updateCategory");
        String response = categoryService.updateCategory(categoryDTO);
        return new ResponseEntity<>(response, response.equals(Success) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") int categoryId) {
        log.info("in deleteCategory");
        String response = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(response, response.equals(Success) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }

}