package org.sazonov.finl.services;

import lombok.AllArgsConstructor;
import org.sazonov.finl.entity.Item;
import org.sazonov.finl.repository.AdressRepository;
import org.sazonov.finl.repository.ItemRepository;
import org.sazonov.finl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;

    //  =Items=
    public List<Item> findAll(String userName){
        return userRepository.findByUsername(userName).getCart().getItems();
    }
    public Item save(String userName, Item itemToSave){
        userRepository.findByUsername(userName).getCart().getItems().add(itemToSave);
        itemRepository.save(itemToSave);
        return  itemToSave;
    }
    public Item updProduct(String userName, int x,  Item item){
        userRepository.findByUsername(userName).getCart().getItems().remove(x+1);
        userRepository.findByUsername(userName).getCart().getItems().add(item);
        return itemRepository.save(item);
    }
    public List<Item> deleteById(String userName, int x) {
       userRepository.findByUsername(userName).getCart().getItems().remove(x+1);
        return userRepository.findByUsername(userName).getCart().getItems();
    }
}
