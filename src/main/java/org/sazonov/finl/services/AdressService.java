package org.sazonov.finl.services;


import org.sazonov.finl.repository.AdressRepository;
import org.sazonov.finl.repository.UserRepository;
import org.sazonov.finl.entity.Adress;
import org.sazonov.finl.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdressService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdressRepository adressRepository;

    public List<Adress> getAdress(String userName) {
        User user = userRepository.findByUsername(userName);
        return user.getAdresses();
    }

    public Adress save(String userName, Adress adressToAdd) {
        User user = userRepository.findByUsername(userName);
        Adress adress = Adress.builder()
                .acity(adressToAdd.getAcity())
                .bstreet(adressToAdd.getBstreet())
                .cnumOfHouse(adressToAdd.getCnumOfHouse())
                .user(user)
                .build();
        user.getAdresses().add(adress);
        return adressRepository.save(adress);
    }

    public Adress updAdress(String userName, int x, Adress adressToUp) {
        adressRepository.deleteById(x);
        User user = userRepository.findByUsername(userName);
        Adress adress = Adress.builder()
                .acity(adressToUp.getAcity())
                .bstreet(adressToUp.getBstreet())
                .cnumOfHouse(adressToUp.getCnumOfHouse())
                .user(user)
                .build();
        user.getAdresses().add(adress);
        return adressRepository.save(adress);
    }

    public List<Adress> deleteById(String userName, int x) {
        User user = userRepository.findByUsername(userName);
        Adress adressToDelete = adressRepository.findById(x);
        user.getAdresses().remove(adressToDelete);
        adressRepository.deleteById(x);
        return adressRepository.findAll();
    }
}
