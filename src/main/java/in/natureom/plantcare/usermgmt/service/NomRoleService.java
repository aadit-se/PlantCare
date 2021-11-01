package in.natureom.plantcare.usermgmt.service;

import in.natureom.plantcare.usermgmt.dao.NatureOmRole;
import in.natureom.plantcare.usermgmt.dao.NatureOmRoleRepo;
import in.natureom.plantcare.usermgmt.dto.NatureOmRoleDto;
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
public class NomRoleService {

    @Autowired
    NatureOmRoleRepo dao;

    public void addAllMasterRoles(List<NatureOmRole> natureOmRoles) {

        try {
            dao.saveAll(natureOmRoles);
        } catch (Exception e) {
            log.error("Adding master roles error:" + e.getMessage());
        }
    }

    public NatureOmRole NomRoleDtoToModel(NatureOmRoleDto roleDto) {
        log.debug("in NomRoleModelToDto converting from dto to model object");
        NatureOmRole role = new NatureOmRole();
        role.setRoleId(roleDto.getRoleId());
        role.setRoleName(roleDto.getRoleName());
        role.setRoleDescription(roleDto.getRoleDescription());
        return role;
    }

    public NatureOmRoleDto NomRoleModelToDto(NatureOmRole role) {
        log.debug("in NomRoleModelToDto converting from model to dto object");
        NatureOmRoleDto roleDto = new NatureOmRoleDto();
        roleDto.setRoleId(role.getRoleId());
        roleDto.setRoleName(role.getRoleName());
        roleDto.setRoleDescription(role.getRoleDescription());
        return roleDto;
    }

    public String addRole(NatureOmRoleDto roleDto) {

        try {
            log.info("inside addRole, object being added");
            dao.save(NomRoleDtoToModel(roleDto));
            log.info("object was added");
        } catch (Exception e) {
            return Failed;
        }
        return Success;
    }

    public List<NatureOmRoleDto> getRoles() {

        try {
            log.info("inside getRoles, fetching list of roles");
            List<NatureOmRole> roles = (List<NatureOmRole>) dao.findAll();
            if (!NatureOmUtil.isObjectValid(roles))
                throw new Exception("no such object");
            List<NatureOmRoleDto> roleDtos = new ArrayList<NatureOmRoleDto>();
            roles.forEach((role) -> {
                roleDtos.add(NomRoleModelToDto(role));
            });
            log.info("list was fetched");
            return roleDtos;
        } catch (Exception e) {
            return null;
        }
    }

    public NatureOmRole getByRoleName(String roleName) {
        try {
            log.info("in getByRoleName");
            NatureOmRole objRole = dao.findByRoleName(roleName);
            if (!NatureOmUtil.isObjectValid(objRole))
                throw new Exception("no such object");
            log.info("object was fetched");
            return objRole;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }


    public NatureOmRoleDto getRole(Long roleId) {

        try {
            log.info("in getRole, object being fetched");
            NatureOmRoleDto roleDto = NomRoleModelToDto(dao.findById(roleId).get());
            if (!NatureOmUtil.isObjectValid(roleDto))
                throw new Exception("no such object");
            log.info("object was fetched");
            log.debug("the object={}", roleDto);
            return roleDto;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

}