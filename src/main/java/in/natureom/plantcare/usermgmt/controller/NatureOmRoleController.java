package in.natureom.plantcare.usermgmt.controller;

import in.natureom.plantcare.usermgmt.dto.NatureOmRoleDto;
import in.natureom.plantcare.usermgmt.service.NomRoleService;
import in.natureom.plantcare.util.CommonConstants;
import in.natureom.plantcare.util.NatureOmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Applicable roles in NatureOm app are:
 * Plant Admin - one who oversee garden / farm status on regular basis
 * Plant Master -  generally Gardener / farmer who look after garden / farm
 * Customer -  the end user whose garden / farm is being managed
 * NatureOm Admin - is kind of super admin who manages plant master, plant admin, customer
 * */
@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping("/nom-role")
public class NatureOmRoleController {

    @Autowired
    NomRoleService nomRoleService;

    @PostMapping("/")
    public ResponseEntity<String> addRole(@RequestBody NatureOmRoleDto nomRoleDto)
    {
        log.info("in add role adding obj");
        String response = nomRoleService.addRole(nomRoleDto);
        return new ResponseEntity<>(response, response.equals(CommonConstants.Success) ? HttpStatus.OK: HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/")
    public ResponseEntity<List<NatureOmRoleDto>> getRoles() {
        log.info("in getRoles");
        List<NatureOmRoleDto> nomRoles = nomRoleService.getRoles();
        return new ResponseEntity<>(nomRoles, NatureOmUtil.isObjectValid(nomRoles) ? HttpStatus.OK: HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<NatureOmRoleDto> getRole(@PathVariable("roleId") Long roleId) {
        log.info("in getRole");
        NatureOmRoleDto nomRole = nomRoleService.getRole(roleId);
        return new ResponseEntity<>(nomRole, NatureOmUtil.isObjectValid(nomRole) ? HttpStatus.OK: HttpStatus.EXPECTATION_FAILED);
    }

}
