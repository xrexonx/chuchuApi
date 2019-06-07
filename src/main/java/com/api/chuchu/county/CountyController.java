package com.api.chuchu.county;

import com.api.chuchu.exception.ResourceNotFoundException;
import com.api.chuchu.county.County;
import com.api.chuchu.county.CountyRepository;
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
public class CountyController {

    @Autowired
    private CountyRepository countyRepository;
    private StateRepository stateRepository;

    // Get All Counties
    @CrossOrigin
    @GetMapping("/counties")
    public List<County> getAllCounties() {
        return countyRepository.findAll();
    }

    @GetMapping("/pCounties")
    public Page<County> paginatedCounties(
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        Pageable params = PageRequest.of(page, size);
        return countyRepository.findAll(params);
    }

    // Create a new County
    @PostMapping("/counties")
    public County createCounty(@Valid @RequestBody County county) {
        State state = stateRepository.findByAbbreviation("AL");
        county.setName("Baldwin");
        county.setState(state);
        return countyRepository.save(county);
    }

    // Create Multiple Counties
    @PostMapping("/aCounties")
    public void createCounties(@RequestBody List<County> counties) {
        counties.forEach(county -> countyRepository.save(county));
    }

    // Get a Single County
    @GetMapping("/counties/{id}")
    public County getCountyById(@PathVariable(value = "id") Long countyId) {
        return countyRepository
                .findById(countyId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("County", "id", countyId)
                );
    }

    // Update a County
    @PutMapping("/counties/{id}")
    public County updateCounty(
            @PathVariable(value = "id") Long countyId,
            @Valid @RequestBody County countyDetails
    ) {

        County county = countyRepository
                .findById(countyId)
                .orElseThrow(() -> new ResourceNotFoundException("County", "id", countyId));

        county.setName(countyDetails.getName());
//        county.setAbbreviation(countyDetails.getAbbreviation());

        return countyRepository.save(county);
    }

    // Delete a County
    @DeleteMapping("/counties/{id}")
    public ResponseEntity<?> deleteCounty(@PathVariable(value = "id") Long countyId) {
        County county = countyRepository
                .findById(countyId)
                .orElseThrow(() -> new ResourceNotFoundException("County", "id", countyId));

        countyRepository.delete(county);

        return ResponseEntity.ok().build();
    }
}
