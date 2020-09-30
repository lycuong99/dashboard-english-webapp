package web.app.service;

import web.app.dtos.UserDTO;
import web.app.entity.User;

import java.util.List;

public interface IUserService {
	public void changePassword(String user, String password);
	public List<User> getModUsers(String expectUser);
	public User get(String  username);
	public void insert(UserDTO dto) throws Exception;
	public void delete(String username);
}
