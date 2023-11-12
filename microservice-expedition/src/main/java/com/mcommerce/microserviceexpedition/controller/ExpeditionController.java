package com.mcommerce.microserviceexpedition.controller;

import com.mcommerce.microserviceexpedition.exceptions.ExpeditionNotFoundException;
import com.mcommerce.microserviceexpedition.model.Expedition;
import com.mcommerce.microserviceexpedition.repository.ExpeditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/expedition")
public class ExpeditionController {

    @Autowired
    private ExpeditionRepository expeditionRepository;

    @PostMapping("/")
 public ResponseEntity<Expedition> createExpedition(@RequestBody Expedition expedition){
        return new ResponseEntity<>(expeditionRepository.save(expedition)
                , HttpStatus.CREATED);


 }

 @GetMapping("/{id}")
 public Expedition getExpedition(@PathVariable Long id){
     Optional<Expedition> expedition = expeditionRepository.findById(id);

     if(!expedition.isPresent())  throw new ExpeditionNotFoundException("L'expedition correspondant à l'id " + id + " n'existe pas");

     return expedition.get();
 }

    @PutMapping("/")
    public Expedition updateExpedition(@RequestBody Expedition expeditionData){
        Optional<Expedition> expedition = expeditionRepository.findById(expeditionData.getId());

        if(!expedition.isPresent())  throw new ExpeditionNotFoundException("L'expedition correspondant à l'id " + expeditionData.getId() + " n'existe pas");
        return expeditionRepository.save(expeditionData);
    }

}
