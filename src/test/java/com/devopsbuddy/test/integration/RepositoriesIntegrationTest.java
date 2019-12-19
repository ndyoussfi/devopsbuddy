package com.devopsbuddy.test.integration;

import com.devopsbuddy.DevopsbuddyApplication;
import com.devopsbuddy.backend.persistence.domain.backend.Plan;
import com.devopsbuddy.backend.persistence.domain.backend.Role;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy.backend.persistence.repositories.PlanRepository;
import com.devopsbuddy.backend.persistence.repositories.RoleRepository;
import com.devopsbuddy.backend.persistence.repositories.UserRepository;
import com.devopsbuddy.enums.PlansEnum;
import com.devopsbuddy.enums.RolesEnum;
import com.devopsbuddy.utils.UsersUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DevopsbuddyApplication.class)
public class RepositoriesIntegrationTest {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

//    private static final int BASIC_PLAN_ID = 1;
//    private static final int BASIC_ROLE_ID = 1;

    @Before // this methods executes before each test runs
    public void init(){
        Assert.assertNotNull(planRepository);
        Assert.assertNotNull(roleRepository);
        Assert.assertNotNull(userRepository);
    }

     // throws exception but were not interested in exceptions
    // creates a basic role, invoke the rolerepository safe method
    // and subsequently the newly created role with the findone method
    @Test
    public void testCreateNewPlan() throws Exception{
        Plan basicPlan = createPlan(PlansEnum.BASIC);
        planRepository.save(basicPlan);

        Plan retrievePlan = planRepository.findOne(RolesEnum.BASIC.getId());
        Assert.assertNotNull(retrievePlan);
    }
    @Test
    public void testCreateNewRole() throws Exception{
        Role userRole = createRole(RolesEnum.BASIC);
        roleRepository.save(userRole);

//        Role retrieveRole = roleRepository.findOne( BASIC_ROLE_ID); // old way without enum
        Role retrieveRole = roleRepository.findOne(RolesEnum.BASIC.getId());
        Assert.assertNotNull(retrieveRole);
    }

    // User entity has many to many relationship with Role entity
    // User entity has many to one relationship with Plan Table
    // this means that before we can save the User entity
    // we need to save all its related entities in order to saveguard the data integrity
    @Test
    public void createNewUser() throws Exception{
        Plan basicPlan = createPlan(PlansEnum.BASIC);
        planRepository.save(basicPlan);

        User basicUser = UsersUtils.createBasicUser();
        basicUser.setPlan(basicPlan);

//        Role basicRole = createRole(); // old way without enum
        Role basicRole = createRole(RolesEnum.BASIC);
        Set<UserRole> userRoles = new HashSet<>();
        UserRole userRole = new UserRole(basicUser, basicRole);
//        userRole.setUser(basicUser);
//        userRole.setRole(basicRole);

        userRoles.add(userRole);

        basicUser.getUserRoles().addAll(userRoles); // important to use getter, and then objects

        for(UserRole ur: userRoles){
            roleRepository.save(ur.getRole());
        }

        basicUser = userRepository.save(basicUser);
        User newlyCreatedUser = userRepository.findOne(basicUser.getId());
        Assert.assertNotNull(newlyCreatedUser);
        Assert.assertTrue(newlyCreatedUser.getId() != 0);
        Assert.assertNotNull(newlyCreatedUser.getPlan());
        Assert.assertNotNull(newlyCreatedUser.getPlan().getId());
        Set<UserRole> newlyCreatedUserUserRoles = newlyCreatedUser.getUserRoles();
        for (UserRole ur: newlyCreatedUserUserRoles){
            Assert.assertNotNull(ur.getRole());
            Assert.assertNotNull(ur.getRole().getId());
        }


    }

    private Plan createPlan(PlansEnum plansEnum){
        return new Plan(plansEnum);
    }
//    private Plan createBasicPlan(){
//        Plan plan = new Plan();
//        plan.setId(BASIC_PLAN_ID);
//        plan.setName("Basic");
//        return plan;
//    }

    private Role createRole(RolesEnum rolesEnum){
        return new Role(rolesEnum);
    }

    // old way without rolesenum
//    private Role createBasicRole(){
//        Role role = new Role();
//        role.setId(BASIC_PLAN_ID);
//        role.setName("ROLE_USER");
//        return role;
//    }

//    private User createBasicUser(){
//
//        User user = new User();
//        user.setUsername("basicUser");
//        user.setPassword("secret");
//        user.setEmail("me@example.com");
//        user.setFirstName("firstName");
//        user.setLastName("lastName");
//        user.setPhoneNumber("123456789123");
//        user.setCountry("GB");
//        user.setEnabled(true);
//        user.setDescription("A basic user");
//        user.setProfileImageUrl("https://blabla.images.com/basicuser");
//
//        return user;
//    }

}
