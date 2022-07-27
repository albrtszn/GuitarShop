package org.sazonov.finl.repository;

import org.sazonov.finl.entity.Adress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdressRepository extends JpaRepository<Adress,Integer> {
    List<Adress> findAll();
    Adress findById(int id);
//    List<Adress> findById(int id);
}
