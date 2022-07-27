package org.sazonov.finl;

import lombok.extern.slf4j.Slf4j;
import org.sazonov.finl.entity.Adress;
import org.sazonov.finl.entity.Item;
import org.sazonov.finl.repository.AdressRepository;
import org.sazonov.finl.repository.ItemRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.math.BigDecimal;

@Slf4j
@EnableJpaAuditing
@SpringBootApplication
//@EnableAutoConfiguration
@EntityScan
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    protected ApplicationListener<ContextRefreshedEvent> start() {
        return event -> {
            ApplicationContext ctx = event.getApplicationContext();
            ItemRepository itemRepository = ctx.getBean(ItemRepository.class);
            AdressRepository adressRepository = ctx.getBean(AdressRepository.class);
            {
                Item item1 = new Item();
                item1.setAname("Laviere CG-39 (BK)");
                item1.setDescription("Классическая гитара с качественным исполнением в сочетании с невысокой ценой делают её отличным вариантом для начинающих музыкантов");
                item1.setCountryOfmade("Франция");
                item1.setPrice(BigDecimal.valueOf(243.00));
                itemRepository.save(item1);
                Item item2 = new Item();
                item2.setAname("Laviere L-39C CCT");
                item2.setDescription("Небольшой размер корпуса в сочетании с узким грифом придаёт больше удобства на начальном этапе обучения.");
                item2.setCountryOfmade("Франция");
                item2.setPrice(BigDecimal.valueOf(264.00));
                itemRepository.save(item2);
                Item item3 = new Item();
                item3.setAname("Sonata F-600 (SB)");
                item3.setDescription("Акустическая вестерн-гитара Sonata F-600 (SB)");
                item3.setCountryOfmade("Китай");
                item3.setPrice(BigDecimal.valueOf(267.00));
                itemRepository.save(item3);

                Adress adress1 = new Adress();
                adress1.setAcity("Полоцк");
                adress1.setBstreet("Замковая");
                adress1.setCnumOfHouse("15c1");
                adressRepository.save(adress1);
            }
        };
    }
}