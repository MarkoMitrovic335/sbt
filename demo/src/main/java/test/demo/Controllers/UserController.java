package test.demo.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.demo.Models.Role;
import test.demo.Models.User;
import test.demo.Repository.RoleRepository;
import test.demo.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/createRoles")
    public ResponseEntity<String> addRoles(){
        Role role = new Role("USER");
        roleRepository.save(role);
        Role role2 = new Role("ADMIN");
        roleRepository.save(role2);
        String str = "Roles are created";
        return new ResponseEntity<String>(str, HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){

        Optional<Role> role = roleRepository.findById(1);
        Role roleOne = role.get();
        user.setRole(roleOne);
        userRepository.save(user);
        return new ResponseEntity<User>(user,HttpStatus.CREATED);

    }

    @PostMapping("/createAdmin")
    public ResponseEntity<User> createAdmin(@RequestBody User user){

        Optional<Role> role = roleRepository.findById(2);
        Role roleOne = role.get();
        user.setRole(roleOne);
        userRepository.save(user);
        return new ResponseEntity<User>(user,HttpStatus.CREATED);

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAll(){
        List<User> userList = userRepository.findAll();
        return new ResponseEntity<List<User>>(userList, HttpStatus.FOUND);
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<List<User>> getByRole(@PathVariable int id){
        List<User> userList = new ArrayList<User>();
        try {
            Role role = roleRepository.findById(id).get();
            userList = userRepository.findAllByRole(role);
            return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Role id must be 1 or 2",HttpStatus.NOT_FOUND);
        }


    }


}