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
import com.devopsbuddy.utils.UserUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DevopsbuddyApplication.class)
public class UserIntegrationTest extends AbstractIntegrationTest{

    @Rule public TestName testName = new TestName();

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
    /**
     * Create and save the plan and set it as user's foreign key
     * persist all roles
     * add them to set of user roles
     * add the set to the existing user entity
     * and finally we persist the user*/

    @Test
    public void createNewUser() throws Exception{
        String username = testName.getMethodName();
        String email = testName.getMethodName() + "@devopsbuddy.com";
        User basicUser = createUser(username, email);

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

    @Test
    public void testDeleteUser() throws Exception{
        String username = testName.getMethodName();
        String email = testName.getMethodName() + "@devopsbuddy.com";

        User basicUser = createUser(username,email);
        userRepository.delete(basicUser.getId());
    }

}
