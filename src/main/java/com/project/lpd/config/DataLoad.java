package com.project.lpd.config;

import com.project.lpd.entity.RoleEntity;
import com.project.lpd.entity.UserEntity;
import com.project.lpd.repository.RoleRepo;
import com.project.lpd.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Component
public class DataLoad implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private UserRepo userRepo;

    @Lazy
    @Autowired
    private PasswordEncoder crypt;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        final RoleEntity admin = createRoleIfNotFound("ADMIN");
        final RoleEntity user = createRoleIfNotFound("USER");

        createUserIfNotFound("admin","admin@gmail.com","Store game","123456",new ArrayList<>(Arrays.asList(admin)));
        alreadySetup = true;
    }
    @Transactional
    RoleEntity createRoleIfNotFound(final String name) {
        RoleEntity role = roleRepo.findByName(name);
        if (role == null) {
            role = new RoleEntity(name);
        }
        role = roleRepo.save(role);
        return role;
    }
    @Transactional
    UserEntity createUserIfNotFound(final String username,final String email, final String fullName, final String password, final Collection<RoleEntity> roles) {
        UserEntity user = userRepo.findByEmail(email);
        if (user == null) {
            user = new UserEntity();
            user.setFullName(fullName);
            user.setPassword(crypt.encode(password));
            user.setEmail(email);
            user.setUsername(username);
        }
        user.setRoles(roles);
        user = userRepo.save(user);
        return user;
    }


}
