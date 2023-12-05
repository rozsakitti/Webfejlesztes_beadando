package com.example.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    @Autowired private PetRepository repo;

    public List<Pet> listAll(){
        return (List<Pet>) repo.findAll();
    }

    public void save(Pet pet) {
        repo.save(pet);
    }

    public Pet get(Integer id) throws PetNotFoundException {
        Optional<Pet> result = repo.findById(id);
        if(result.isPresent()){
           return result.get();
        }
        throw new PetNotFoundException("Could not find any pets with ID" + id);
    }

    public void delete(Integer id) throws PetNotFoundException {
        Long count = repo.countById(id);
        if(count == null || count == 0){
            throw new PetNotFoundException("Could not find any pets with ID" + id);
        }
        repo.deleteById(id);
    }
}
