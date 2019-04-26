package com.api.chuchu.controller;

import com.api.chuchu.dto.StateDTO;
import com.api.chuchu.exception.ResourceNotFoundException;
import com.api.chuchu.entity.State;
import com.api.chuchu.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StateController {

    @Autowired
    StateRepository stateRepository;

    // Get All States
    @GetMapping("/states")
    public List<StateDTO> getAllStates() {
        List<State> state = stateRepository.findAll();
        List<StateDTO> stateDTO = new ArrayList<>();
        for(State s: state) {
            StateDTO st = new StateDTO();
            st.setId(s.getId());
            st.setName(s.getName());
            st.setAbbreviation(s.getAbbreviation());
            st.setCreatedAt(s.getCreatedAt());
            stateDTO.add(st);
        }
        return stateDTO;
    }

    // Create a new State
    @PostMapping("/states")
    public State createState(@Valid @RequestBody State state) {
        return stateRepository.save(state);
    }

    // Create Multiple States
    @PostMapping("/aStates")
    public void createStates(@RequestBody List<State> states) {
       states.forEach(state -> stateRepository.save(state));
    }

    // Get a Single State
    @GetMapping("/states/{id}")
    public State getStateById(@PathVariable(value = "id") Long stateId) {
        return stateRepository
            .findById(stateId)
            .orElseThrow(
                () -> new ResourceNotFoundException("State", "id", stateId)
            );
    }


    // Update a State
    @PutMapping("/states/{id}")
    public State updateState(
        @PathVariable(value = "id") Long stateId,
        @Valid @RequestBody State stateDetails
    ) {

        State state = stateRepository
                .findById(stateId)
                .orElseThrow(() -> new ResourceNotFoundException("State", "id", stateId));

        state.setName(stateDetails.getName());
        state.setAbbreviation(stateDetails.getAbbreviation());

        return stateRepository.save(state);
    }

    // Delete a State
    @DeleteMapping("/states/{id}")
    public ResponseEntity<?> deleteState(@PathVariable(value = "id") Long stateId) {
        State state = stateRepository
                .findById(stateId)
                .orElseThrow(() -> new ResourceNotFoundException("State", "id", stateId));

        stateRepository.delete(state);

        return ResponseEntity.ok().build();
    }
}