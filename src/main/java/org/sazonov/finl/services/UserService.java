package org.sazonov.finl.services;

import org.sazonov.finl.entity.*;
import org.sazonov.finl.entity.stuff.CartDto;
import org.sazonov.finl.entity.stuff.OrderDto;
import org.sazonov.finl.entity.stuff.StatusOfOrder;
import org.sazonov.finl.repository.*;
import org.sazonov.finl.security.Roles;
import org.sazonov.finl.services.model.AdressDto;
import org.sazonov.finl.services.model.RoleDto;
import org.sazonov.finl.services.model.UserDto;
import org.sazonov.finl.services.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

//@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AdressRepository adressRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private CartRepository cartRepository;

    public static UserDto toUserDto(User user) {
        List<AdressDto> listAdressDto = user.getAdresses().stream()
                .map(adress -> new AdressDto().convertToDto(adress))
                .collect(Collectors.toList());

        List<RoleDto> listRoleDto = user.getAuthorities().stream()
                .map(role -> new RoleDto().convertToDto(role))
                .collect(Collectors.toList());

        return UserDto.builder()
                .username(user.getUsername())
                .firstname(user.getFirstName())
                .secondName(user.getSecondName())
                .adresses(listAdressDto)
                .authorities(listRoleDto)
                .build();
    }

    public static CartDto toCartDto(Cart cart) {
        BigDecimal summary = new BigDecimal(BigInteger.ZERO);
        cart.getItems().stream().forEach(item -> summary.add(item.getPrice()));
        return CartDto.builder()
                .items(cart.getItems())
                .summaryPrice(summary)
                .build();
    }

    public static List<OrderDto> toOrderDto(User user) {
        return user.getOrders().stream().map(order -> OrderDto.builder()
                .userName(user.getUsername())
                .statusOfOrder(StatusOfOrder.WAI_FOR_PAYMENT)
                .adress(order.getAdress().toModel())
                .items(order.getItems())
                .summaryPrice(order.getPrice())
                .build()).collect(Collectors.toList());
    }

    public UserDto createUser(UserModel user) {
        Cart cart = new Cart();
        User userToCreate = User.builder()
                .username(user.getUsername())
                .cart(cart)
                .password(bCryptPasswordEncoder.encode((user.getPassword())))
                .firstName(user.getFirstName())
                .secondName(user.getSecondName())
                //.authorities(List.of(role))
                .build();
        cart.setUser(userToCreate);
        Role role = Role.builder()
                .name(Roles.USER)
                .user(userToCreate)
                .build();
        userToCreate.setAuthorities(List.of(role));
        userRepository.save(userToCreate);
        roleRepository.save(role);
        cartRepository.save(cart);
        return UserService.toUserDto(userToCreate);
    }

    public UserDto loadById(String userName) {
        User user = userRepository.findByUsername(userName);
        return UserService.toUserDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        user.setAuthorities(List.of(Role.builder().name(Roles.USER).user(user).build()));
        return user;
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public List<User> deleteUser(String userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return userRepository.findAll();
        }
        return userRepository.findAll();
    }

    public CartDto addToCart(String userName, int itemId) {
        User user = userRepository.findByUsername(userName);
        user.getCart().getItems().add(itemRepository.findById(itemId));
        cartRepository.save(user.getCart());
        return UserService.toCartDto(user.getCart());
    }

    public CartDto clearCart(String userName) {
        User user = userRepository.findByUsername(userName);
        user.getCart().getItems().clear();
        cartRepository.save(user.getCart());
        return UserService.toCartDto(user.getCart());
    }

    public List<OrderDto> makeOrder(String userName, int adressId) {
        User user = userRepository.findByUsername(userName);
        user.getOrders().add(Order.builder()
                .user(user)
                .adress(user.getAdresses().get(adressId + 1))
                .data(LocalDate.now())
                .price(user.getCart().getSummaryPrice())
                .build());
        return UserService.toOrderDto(user);
    }
}

