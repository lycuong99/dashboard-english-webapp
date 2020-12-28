package web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.app.dtos.UserDTO;
import web.app.entity.User;
import web.app.repos.UserRepo;
import web.app.service.IUserService;
import web.app.transfer.TransferUtilities;

import java.util.List;


@Component
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepo userRepo;

    @Override
    public void changePassword(String username, String password) {
        User user = userRepo.getOne(username);
        user.setPassword(password);
        userRepo.saveAndFlush(user);

    }

    @Override
    public List<User> getModUsers(String expectUser) {
        return  userRepo.findByUsernameNotLike(expectUser);
    }

    @Override
    public User get(String username) {
        return userRepo.getOne(username);
    }

    @Override
    public void insert(UserDTO dto) throws Exception {
        if(userRepo.existsById(dto.getUsername()))
        {
            throw new Exception("User đã tồn tại");
        }

        userRepo.saveAndFlush(TransferUtilities.transferToEntity(dto));
    }

    @Override
    public void delete(String username) {
        if(userRepo.existsById(username))
        {
            if(userRepo.getOne(username).getRole().equalsIgnoreCase("ADMIN")) return;

            userRepo.deleteById(username);
        }
    }
}
