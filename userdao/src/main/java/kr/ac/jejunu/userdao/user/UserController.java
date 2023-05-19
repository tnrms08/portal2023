package kr.ac.jejunu.userdao.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

    @RequestMapping
    public User getUser(@RequestParam Long id){
        return userDao.findById(id);
    }
}