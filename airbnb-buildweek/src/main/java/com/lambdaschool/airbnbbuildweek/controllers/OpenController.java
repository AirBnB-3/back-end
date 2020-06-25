package com.lambdaschool.airbnbbuildweek.controllers;

import com.lambdaschool.airbnbbuildweek.models.User;
import com.lambdaschool.airbnbbuildweek.models.UserMinimum;
import com.lambdaschool.airbnbbuildweek.models.UserRoles;
import com.lambdaschool.airbnbbuildweek.services.RoleService;
import com.lambdaschool.airbnbbuildweek.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OpenController
{
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostMapping(value = "/createnewuser",
        consumes = {"application/json"},
        produces = {"application/json"})
    public ResponseEntity<?> addSelf(
        HttpServletRequest httpServletRequest,
        @Valid
        @RequestBody
            UserMinimum newminuser)
        throws
        URISyntaxException
    {
        // Create the user
        User newuser = new User();

        newuser.setUsername(newminuser.getUsername());
        newuser.setFirstname(newminuser.getFirstname());
        newuser.setLastname(newminuser.getLastname());
        newuser.setPassword(newminuser.getPassword());
        newuser.setPrimaryemail(newminuser.getPrimaryemail());

        // add the default role of user
        List<UserRoles> newRoles = new ArrayList<>();
        newRoles.add(new UserRoles(newuser,
            roleService.findByName("user")));
        newuser.setRoles(newRoles);

        newuser = userService.save(newuser);

        // set the location header for the newly created resource
        // The location comes from a different controller!
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromUriString(httpServletRequest.getServerName() + ":" + httpServletRequest.getLocalPort() + "/users/user/{userId}")
            .buildAndExpand(newuser.getUserid())
            .toUri();
        responseHeaders.setLocation(newUserURI);

        // return the access token
        // To get the access token, surf to the endpoint /login just as if a client had done this.
        RestTemplate restTemplate = new RestTemplate();
        //        String requestURI = "http://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getLocalPort() + "/login";
        String port = "";
        if (httpServletRequest.getServerName()
            .equalsIgnoreCase("localhost"))
        {
            port = ":" + httpServletRequest.getLocalPort();
        }
        String requestURI = "http://" + httpServletRequest.getServerName() + port + "/login";


        List<MediaType> acceptableMediaTypes = new ArrayList<>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(acceptableMediaTypes);
        headers.setBasicAuth(System.getenv("OAUTHCLIENTID"),
            System.getenv("OAUTHCLIENTSECRET"));

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type",
            "password");
        map.add("scope",
            "read write trust");
        map.add("username",
            newminuser.getUsername());
        map.add("firstname",
            newminuser.getFirstname());
        map.add("lastname",
            newminuser.getLastname());
        map.add("password",
            newminuser.getPassword());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map,
            headers);

        String theToken = restTemplate.postForObject(requestURI,
            request,
            String.class);

        return new ResponseEntity<>(theToken,
            responseHeaders,
            HttpStatus.CREATED);
    }
}
