package com.api.chuchu.zipcode;

import com.api.chuchu.exception.ResourceNotFoundException;
import com.api.chuchu.zipcode.Zipcode;
import com.api.chuchu.zipcode.ZipcodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ZipcodeController {

    @Autowired
    private ZipcodeRepository zipcodeRepository;

    @CrossOrigin
    @GetMapping("/zipcodes")
    public List<Zipcode> getAllZipcodes() {
        return zipcodeRepository.findAll();
    }

    @CrossOrigin
    @PostMapping("/zipcodes")
    public Zipcode createZipcode(@RequestBody Zipcode zipcode) {
        return zipcodeRepository.save(zipcode);
    }

    @CrossOrigin
    @PostMapping("/aZipcodes")
    public void createZipcodes(@RequestBody List<Zipcode> zipcodes) {
        zipcodes.forEach(zipcode -> zipcodeRepository.save(zipcode));
    }

    @CrossOrigin
    @GetMapping("/zipcodes/{id}")
    public Zipcode getZipcodeById(@PathVariable(value = "id") Long zipcodeId) {
        return zipcodeRepository
                .findById(zipcodeId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Zipcode", "id", zipcodeId)
                );
    }

    @CrossOrigin
    @PutMapping("/zipcodes/{id}")
    public Zipcode updateZipcode(
            @PathVariable(value = "id") Long zipcodeId,
            @Valid @RequestBody Zipcode zipcodeDetails
    ) {

        Zipcode zipcode = zipcodeRepository
                .findById(zipcodeId)
                .orElseThrow(() -> new ResourceNotFoundException("Zipcode", "id", zipcodeId));

        zipcode.setCode(zipcodeDetails.getCode());
        zipcode.setType(zipcodeDetails.getType());
        zipcode.setAreaCode(zipcodeDetails.getAreaCode());

        return zipcodeRepository.save(zipcode);
    }

    @CrossOrigin
    @DeleteMapping("/zipcodes/{id}")
    public ResponseEntity<?> deleteZipcode(@PathVariable(value = "id") Long zipcodeId) {
        Zipcode zipcode = zipcodeRepository
                .findById(zipcodeId)
                .orElseThrow(() -> new ResourceNotFoundException("Zipcode", "id", zipcodeId));

        zipcodeRepository.delete(zipcode);

        return ResponseEntity.ok().build();
    }

}
