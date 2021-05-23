package com.webec.WGApplication;

import com.webec.WGApplication.model.entity.User;
import com.webec.WGApplication.model.repository.UserRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Set;

@Component
public class InitialUserAdder {

    private final UserRepository userRepo;

    public InitialUserAdder(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (userRepo.findAll().isEmpty()) {
            var password = "" + new SecureRandom().nextInt();
            var encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            var encoded = encoder.encode(password);
            var admin = new User("admin", encoded, Set.of("ROLE_ADMIN"));
            userRepo.save(admin);
            System.out.println(encoded);
            System.out.println(password);
        }
    }
}
