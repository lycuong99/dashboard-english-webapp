package web.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.app.dtos.UserDTO;
import web.app.entity.User;
import web.app.service.impl.UserServiceImpl;

@RestController()
@RequestMapping("/user-api")
public class UserAPIController {
    // TODO: Insert user

    @Autowired
    UserServiceImpl userService;

    @PutMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody UserDTO user)
    {
        try {
            userService.insert(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String username)
    {
        try {
            userService.delete(username);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //TODO: Insert campus
}
