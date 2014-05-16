package bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yongjie on 14-5-11.
 */
public class Power {
	private Integer id;
	private String powerName;
	private String mark;
	private Set user = new HashSet();
	private Set role = new HashSet();

	public Power() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPowerName() {
		return powerName;
	}

	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Set getUser() {
		return user;
	}

	public void setUser(Set user) {
		this.user = user;
	}

	public Set getRole() {
		return role;
	}

	public void setRole(Set role) {
		this.role = role;
	}

	public void addUser(User user){
		this.getUser().add(user);
		user.getPower().add(this);
	}

	public void deleteUser(User user){
		this.getUser().remove(user);
		user.getPower().remove(this);
	}

	public void addRole(Role role){
		this.getRole().add(role);
		role.getPower().add(this);
	}

	public void deleteRole(Role role){
		this.getRole().remove(role);
		role.getPower().remove(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Power)) return false;

		Power power = (Power) o;

		if (!id.equals(power.id)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
