package com.lambdaschool.airbnbbuildweek;

import com.lambdaschool.airbnbbuildweek.models.Listing;
import com.lambdaschool.airbnbbuildweek.models.Role;
import com.lambdaschool.airbnbbuildweek.models.User;
import com.lambdaschool.airbnbbuildweek.models.UserRoles;
import com.lambdaschool.airbnbbuildweek.services.RoleService;
import com.lambdaschool.airbnbbuildweek.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Override
    public void run(String... args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);
        r3 = roleService.save(r3);

        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(),
            r1));
        admins.add(new UserRoles(new User(),
            r2));
        admins.add(new UserRoles(new User(),
            r3));
        User u1 = new User("lanakane",
            "Lana",
            "Kane",
            "password",
            "lana.kane@figgisagency.local",
            admins);

        u1.getListings()
            .add(new Listing(
                u1,
                //                "Lana Kane's Apartment",
                "entire place",
                //                "38291",
                //                "New York",
                "Manhattan",
                2,
                4,
                1231.00));
        u1.getListings()
            .add(new Listing(
                u1,
                //                "Lana Kane's Tropical Paradise",
                "entire place",
                //                "11111",
                //                "Polynesia",
                "Danger Island",
                2,
                6,
                999.00));

        userService.save(u1);

        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(),
            r3));
        datas.add(new UserRoles(new User(),
            r2));
        User u2 = new User("sterlingarcher",
            "Sterling",
            "Archer",
            "1234567",
            "sterling.archer@figgisagency.com",
            datas);

        u2.getListings()
            .add(new Listing(
                u2,
                //                "Sterling Archer's Penthouse",
                "entire place",
                //                "97423",
                //                "New York",
                "Manhattan",
                3,
                4,
                1200.00));

        userService.save(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(),
            r2));
        User u3 = new User("cyrilfiggis",
            "Cyril",
            "Figgis",
            "password",
            "cyril.figgis@figgisagency.com",
            users);

        u3.getListings()
            .add(new Listing(
                u3,
                //                "Cyril Figgis' Apartment",
                "shared room",
                //                "23412",
                //                "New York",
                "Manhattan",
                2,
                3,
                200.00));
        userService.save(u3);

        User u4 = new User("admin",
            "Admin",
            "Admin",
            "password",
            "admin@figgisagency.local",
            admins);

        u4.getListings()
            .add(new Listing(
                u4,
                //                "Admin's House",
                "entire place",
                //                "37243",
                //                "Nashville",
                "Germantown",
                6,
                6,
                400.00));
        u4.getListings()
            .add(new Listing(
                u4,
                //                "Admin's Lakehouse",
                "entire place",
                //                "12345",
                //                "Eastern Tennessee",
                "Lake Cumberland",
                5,
                12,
                340.00));

        userService.save(u4);
    }
}
