package web.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.Data;

@Entity
@Table(name="users")
@Data
public class User {
	@Id
	private String username;
	private String password;
	private String Role;
	
	
	
}
