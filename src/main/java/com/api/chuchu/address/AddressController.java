package com.api.chuchu.address;

import com.api.chuchu.exception.ResourceNotFoundException;
import com.api.chuchu.address.Address;
import com.api.chuchu.address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @CrossOrigin
    @GetMapping("/addresses")
    public List<Address> getAllAddresss() {
        return addressRepository.findAll();
    }

    @CrossOrigin
    @PostMapping("/addresses")
    public Address createAddress(@RequestBody Address address) {
        return addressRepository.save(address);
    }

    @CrossOrigin
    @PostMapping("/aAddresss")
    public void createAddresss(@RequestBody List<Address> addresses) {
        addresses.forEach(address -> addressRepository.save(address));
    }

    @CrossOrigin
    @GetMapping("/address/{id}")
    public Address getAddressById(@PathVariable(value = "id") Long addressId) {
        return addressRepository
                .findById(addressId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Address", "id", addressId)
                );
    }

    @CrossOrigin
    @PutMapping("/address/{id}")
    public Address updateAddress(
            @PathVariable(value = "id") Long addressId,
            @Valid @RequestBody Address addressDetails
    ) {

        Address address = addressRepository
                .findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId));

        address.setCity(addressDetails.getCity());
        address.setZipcode(addressDetails.getZipcode());
        address.setStreet(addressDetails.getStreet());
        address.setSuite(addressDetails.getSuite());

        return addressRepository.save(address);
    }

    @CrossOrigin
    @DeleteMapping("/address/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable(value = "id") Long addressId) {
        Address address = addressRepository
                .findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId));

        addressRepository.delete(address);

        return ResponseEntity.ok().build();
    }

}
