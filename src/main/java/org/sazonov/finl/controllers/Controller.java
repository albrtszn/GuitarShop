package org.sazonov.finl.controllers;

import lombok.AllArgsConstructor;
import org.sazonov.finl.entity.Order;
import org.sazonov.finl.entity.Adress;
import org.sazonov.finl.entity.Item;
import org.sazonov.finl.entity.User;
import org.sazonov.finl.entity.stuff.CartDto;
import org.sazonov.finl.entity.stuff.OrderDto;
import org.sazonov.finl.services.AdressService;
import org.sazonov.finl.services.ItemService;
import org.sazonov.finl.services.UserService;
import org.sazonov.finl.services.model.UserDto;
import org.sazonov.finl.services.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
public class Controller {
    @Autowired
    private final UserService userService;
    @Autowired
    private AdressService adressService;
    @Autowired
    private  ItemService itemService;

    @GetMapping("/test")
    public Authentication getReq() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    //  =User=
    @PostMapping("/login")
    public UserDto createUser(@RequestBody UserModel user){
        return userService.createUser(user);
    }
//    @Client
    @GetMapping("/admin/{username}")
    public UserDto getUserById(@PathVariable("username") String username){
        return userService.loadById(username);
    }
//    @Client
//    @Secured("ROLE_USER")
    @GetMapping("admin/users")
    public List<User> allUsers(){
        return userService.allUsers();
    }
//    @Client
    @PostMapping("admin/users/delete")
    public List<User> delete(String x){
        return userService.deleteUser(x);
    }

    //  =Items=
    @GetMapping("/items")
//    @Client
    public List<Item> allitems(@RequestParam String userName){
        return itemService.findAll(userName);
    }
    @PostMapping("users/items/add")
    public Item addItem(@RequestBody Item itemToSave,@RequestParam String userName){
        return itemService.save(userName,itemToSave);
    }
    @PostMapping("admin/items/update")
    public Item updProduct(@RequestParam String userName, @RequestParam int x,@RequestBody Item item){
        return itemService.updProduct(userName, x,item);
    }
    @PostMapping("admin/items/delete")
    public List<Item> delProduct(@RequestParam String userName,@RequestParam int x) {
        return itemService.deleteById(userName,x);
    }

    //  =Adress=
    @GetMapping("/adress")
    public List<Adress> allAdresses(String userName){
        return adressService.getAdress(userName);
    }
    @PostMapping("/adress/add")
    public Adress addAdress(@RequestParam String userName, @RequestBody Adress adressToAdd){
        return adressService.save(userName, adressToAdd);
    }
    @PostMapping("/adress/update")
    public Adress updAdress(@RequestParam int x,@RequestParam String userName,@RequestBody Adress adress){
        return adressService.updAdress(userName,x,adress);
    }
    @PostMapping("/adress/delete")
    public List<Adress> delAdress(@RequestParam String userName,@RequestParam int x) {
        return adressService.deleteById(userName,x);
    }

    //  =Cart=
    @PostMapping("users/additem")
    public CartDto addItem(@RequestParam String userName, @RequestParam int itemId){
        return userService.addToCart(userName,itemId);
    }
    @PostMapping("users/delitem")
    public CartDto delItem(@RequestParam String userName){
        return userService.clearCart(userName);
    }

    //  =Order=
    @PostMapping("users/order/create")
    public List<OrderDto> createOrder(@RequestParam String userName, @RequestParam int adressId){
        return userService.makeOrder(userName,adressId);
    }
}
