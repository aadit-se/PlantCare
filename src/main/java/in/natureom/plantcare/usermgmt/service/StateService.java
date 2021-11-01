package in.natureom.plantcare.usermgmt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.natureom.plantcare.usermgmt.dao.State;
import in.natureom.plantcare.usermgmt.dao.StateRepo;
import in.natureom.plantcare.util.CommonConstants;
import in.natureom.plantcare.util.NatureOmUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StateService implements CommonConstants {

    @Autowired
    StateRepo dao;

    public void addAllIndiaState(List<State> states) {

        try {
            dao.saveAll(states);
        } catch (Exception e) {
            log.error("Adding all states error:" + e.getMessage());
        }
    }

    public String addState(State c) {
        try {
            log.info("in addState,  object being added");
            dao.save(c);
            log.info("object was added");
            return Success;
        } catch (Exception e) {
            log.error(e.getMessage());
            return Failed;
        }
    }

    public State viewState(int id) {
        try {
            log.info("in viewState,fetching object");
            State state = dao.findById(id).get();
            if (!NatureOmUtil.isObjectValid(state))
                throw new Exception("no such object");
            log.info("object was fetched");
            log.debug("object={}", state);
            return state;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public ArrayList<State> viewAllStates() {
        try {
            log.info("in viewAllStates,fetching list");
            ArrayList<State> states = (ArrayList<State>) dao.findAll();
            if (!NatureOmUtil.isObjectValid(states))
                throw new Exception("no such object");
            log.info("list was fetched ");
            return states;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

    }

    public String deleteState(int id) {
        try {
            log.info("in deleteState");
            dao.deleteById(id);
            log.info("state was deleted");
            return Success;
        } catch (Exception e) {
            log.error(e.getMessage());
            return Failed;
        }
    }

    public String updateState(State c) {
        try {
            log.info("in updateState");
            dao.save(c);
            log.info("object was updated");
            return Success;
        } catch (Exception e) {
            log.error(e.getMessage());
            return Failed;
        }
    }
}
