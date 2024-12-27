package com.challenge.encryption.controller;

import com.challenge.encryption.dto.SensitiveDataDTO;
import com.challenge.encryption.model.SensitiveData;
import com.challenge.encryption.service.SensistiveDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data")
public class SensitiveDataController {

    @Autowired
    private SensistiveDataService sensistiveDataService;

    @PostMapping("/save")
    public ResponseEntity<SensitiveData> saveSensitiveData(@RequestBody SensitiveDataDTO sensitiveDataDTO){
        SensitiveData sensitiveData = sensistiveDataService.createSensitiveData(sensitiveDataDTO);
        return ResponseEntity.ok()
                .body(sensitiveData);
    }

    @GetMapping("/get")
    public ResponseEntity<List> getAllSensitiveData(){
         List<SensitiveData> sensitiveData = sensistiveDataService.getAllSensitiveData();
        return ResponseEntity.ok()
                .body(sensitiveData);
    }

    @GetMapping("/get/decripted/{id}")
    public ResponseEntity<SensitiveData> getSensitiveDataByIdDecripted(@PathVariable long id){
        SensitiveData sensitiveData = sensistiveDataService.getSensitiveDataByIdDecrypted(id);
        return ResponseEntity.ok()
                .body(sensitiveData);
    }

    @GetMapping("/get/encrypted/{id}")
    public ResponseEntity<SensitiveData> getSensitiveDataByIdEncrypted(@PathVariable long id){
        SensitiveData sensitiveData = sensistiveDataService.getSensitiveDataByIdEncrypted(id);
        return ResponseEntity.ok()
                .body(sensitiveData);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SensitiveData> updateSensitiveData(@PathVariable long id, @RequestBody SensitiveDataDTO sensitiveDataDTO){
        SensitiveData sensitiveData = sensistiveDataService.updateSensitiveDataById(sensitiveDataDTO, id);
        return ResponseEntity.ok()
                .body(sensitiveData);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SensitiveData> deleteSensitiveDataById(@PathVariable long id){
        return sensistiveDataService.deleteSensitiveDAtaById(id);
    }

}
