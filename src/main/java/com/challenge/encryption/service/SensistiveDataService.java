package com.challenge.encryption.service;

import com.challenge.encryption.component.AESUtil;
import com.challenge.encryption.dto.SensitiveDataDTO;
import com.challenge.encryption.model.SensitiveData;
import com.challenge.encryption.repository.SensitiveDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SensistiveDataService {

    @Autowired
    private SensitiveDataRepository sensitiveDataRepository;

    @Autowired
    private AESUtil encryption;

    public SensitiveData createSensitiveData(SensitiveDataDTO sensitiveDataDTO){

        SensitiveData sensitiveData = new SensitiveData();
        sensitiveData.setUserDocument(encryption.encrypt(sensitiveDataDTO.userDocument()));
        sensitiveData.setCreditCardToken(encryption.encrypt(sensitiveDataDTO.creditCardToken()));
        sensitiveData.setValue(sensitiveDataDTO.value());
        sensitiveDataRepository.save(sensitiveData);

        return sensitiveData;
    }

    public List<SensitiveData> getAllSensitiveData(){
        return sensitiveDataRepository.findAll();
    }

    public SensitiveData getSensitiveDataByIdEncrypted(long id){
         return sensitiveDataRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("error when retrieving sensitive data with encrypted data"));
    }

    public SensitiveData getSensitiveDataByIdDecrypted(long id){
        SensitiveData sensitiveData = sensitiveDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("error fetching sensitive data with decrypted data"));

        return getSensitiveDataDecrypted(sensitiveData);
    }

    public SensitiveData updateSensitiveDataById(SensitiveDataDTO sensitiveDataDTO, long id){
        SensitiveData sensitiveData = sensitiveDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("error retrieving sensitive data"));
        sensitiveData.setUserDocument(encryption.encrypt(sensitiveDataDTO.userDocument()));
        sensitiveData.setCreditCardToken(encryption.encrypt(sensitiveDataDTO.creditCardToken()));
        sensitiveData.setValue(sensitiveDataDTO.value());
        sensitiveDataRepository.save(sensitiveData);
        return sensitiveData;
    }

    public ResponseEntity<SensitiveData> deleteSensitiveDAtaById(long id){
        return sensitiveDataRepository.findById(id)
                .map(record -> {sensitiveDataRepository.deleteById(id);
                    return ResponseEntity.ok().body(record);
                }).orElse(ResponseEntity.notFound().build());
    }

    private SensitiveData getSensitiveDataDecrypted(SensitiveData sensitiveData) {
        SensitiveData sensitiveDataDecripted = new SensitiveData();
        sensitiveDataDecripted.setId(sensitiveData.getId());
        sensitiveDataDecripted.setUserDocument(encryption.decrypt(sensitiveData.getUserDocument()));
        sensitiveDataDecripted.setCreditCardToken(encryption.decrypt(sensitiveData.getCreditCardToken()));
        sensitiveDataDecripted.setValue(sensitiveData.getValue());
        return sensitiveDataDecripted;
    }
}
