package ghaya.spring.controller;

import ghaya.spring.service.UserService;
import lombok.Data;

@Data
public class UserController {

    private UserService userService;
}
