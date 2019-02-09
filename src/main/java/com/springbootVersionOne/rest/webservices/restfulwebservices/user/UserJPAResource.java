package com.springbootVersionOne.rest.webservices.restfulwebservices.user;

import com.springbootVersionOne.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserJPAResource {

    @Autowired
    private UserRepository userRepository;

    //retriveAllUsers
    @GetMapping("/jpa/users")
    public List<User> retriveAllUsers(){
        return userRepository.findAll();
    }

    //retriveUser(int id)
    @GetMapping("/jpa/user/{id}")
    public Resource<User> retriveOneUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("id-"+id);
        }else{
            //hateoas link
            Resource<User> resource = new Resource<User>(user.get());
            ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllUsers());
            resource.add(linkTo.withRel("all-users"));
            return resource;
        }
    }

    //CREATED
    //input - details of the user
    //output - CREATED & Return thhe created URI

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);

        //to append path of user created
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    //deleteUser(int id)
    @DeleteMapping("/jpa/user/{id}")
    public void deleteOneUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    //retriveAllUsers
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retriveAllPostByUser(@PathVariable int id){

        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("id-"+id);
        }

        return userOptional.get().getPosts();
    }

}
