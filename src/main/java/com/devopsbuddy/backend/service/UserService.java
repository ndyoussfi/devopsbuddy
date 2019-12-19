package com.devopsbuddy.backend.service;

import com.devopsbuddy.backend.persistence.domain.backend.Plan;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy.backend.persistence.repositories.PlanRepository;
import com.devopsbuddy.backend.persistence.repositories.RoleRepository;
import com.devopsbuddy.backend.persistence.repositories.UserRepository;
import com.devopsbuddy.enums.PlansEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

// this service will expose the functionality that allows us
// to create the user, with

// service transactionality will override jpa transactionality
 // we should use readonly transactions to minimize locking
// we want all repositories operations with a single service method to run as an atomic unit of work
@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User createUser(User user, PlansEnum plansEnum, Set<UserRole> userRoles){
        Plan plan = new Plan(plansEnum);
        // it makes sure the plans exist in the db
        if(!planRepository.exists(plansEnum.getId())){
            plan = planRepository.save(plan);
        }

        user.setPlan(plan); // set plan for user

        for (UserRole ur : userRoles){
            roleRepository.save(ur.getRole()); // loops each role and seves them in db
        }

        user.getUserRoles().addAll(userRoles);

        user = userRepository.save(user);

        return user;
    }

}
