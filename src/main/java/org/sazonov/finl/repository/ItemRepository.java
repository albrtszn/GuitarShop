package org.sazonov.finl.repository;

import org.sazonov.finl.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Integer> {
    List<Item> findAll();
    Item findById(int x);
}
