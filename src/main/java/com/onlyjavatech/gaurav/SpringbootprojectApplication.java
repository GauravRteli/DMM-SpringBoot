package com.onlyjavatech.gaurav;

import com.onlyjavatech.gaurav.Entites.Transaction;
import com.onlyjavatech.gaurav.dao.UserRepository;
import com.onlyjavatech.gaurav.Entites.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@ComponentScan( basePackages = {"com.onlyjavatech.gaurav.dao", "com.onlyjavatech.gaurav.controllers"})
public class SpringbootprojectApplication {

	public static void main(String[] args) {

//		getting the context of the application .
		ConfigurableApplicationContext context = SpringApplication.run(SpringbootprojectApplication.class, args);


    }

}
