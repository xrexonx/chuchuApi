package com.api.chuchu.state;

import com.api.chuchu.exception.ResourceNotFoundException;
import com.api.chuchu.state.State;
import com.api.chuchu.state.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @GetMapping("/")
    public String index() {
        return "Welcome to Chuchu API";
    }

    // Get All States
    @GetMapping("/states")
    public List<State> getAllStates() {
        return stateRepository.findAll();
    }

    @GetMapping("/pStates")
    public Page<State> paginatedStates(
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        Pageable params = PageRequest.of(page, size);
        return stateRepository.findAll(params);
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