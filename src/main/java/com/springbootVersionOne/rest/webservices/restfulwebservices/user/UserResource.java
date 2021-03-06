package com.springbootVersionOne.rest.webservices.restfulwebservices.user;

import com.springbootVersionOne.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    //retriveAllUsers
    @GetMapping("/users")
    public List<User> retriveAllUsers(){
        return service.findAll();
    }

    //retriveUser(int id)
    @GetMapping("/user/{id}")
    public Resource<User> retriveOneUser(@PathVariable int id){
        User user = service.findOne(id);
        if(user==null){
            throw new UserNotFoundException("id-"+id);
        }else{
            //hateoas link
            Resource<User> resource = new Resource<User>(user);
            ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllUsers());
            resource.add(linkTo.withRel("all-users"));
            return resource;
        }
    }

    //CREATED
    //input - details of the user
    //output - CREATED & Return thhe created URI

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);

        //to append path of user created
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();

    }

    //deleteUser(int id)
    @DeleteMapping("/user/{id}")
    public void deleteOneUser(@PathVariable int id){
        User user = service.deleteById(id);
        if(user==null){
            throw new UserNotFoundException("id-"+id);
        }
    }


}
