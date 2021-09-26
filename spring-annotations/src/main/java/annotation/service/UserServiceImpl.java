package annotation.service;

import org.springframework.stereotype.Service;

/**
 * @author WangChenHol
 * @date 2021-9-17 10:30
 **/
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String getUser() {
        return "userService";
    }
}
