package bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yongjie on 14-5-11.
 */
public class Role {
	private Integer id;
	private String roleName;
	private Set user = new HashSet();
	private Set power = new HashSet();
	private Set roleType = new HashSet();

	public Role() {
	}

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set getUser() {
		return user;
	}

	public void setUser(Set user) {
		this.user = user;
	}

	public Set getPower() {
		return power;
	}

	public void setPower(Set power) {
		this.power = power;
	}

	public Set getRoleType() {
		return roleType;
	}

	public void setRoleType(Set roleType) {
		this.roleType = roleType;
	}

	public void addUser(User user){
		this.getUser().add(user);
	}

	public void deleteUser(User user){
		this.getUser().remove(user);
		user.setRoleId(null);
	}

	public void addPower(Power power){
		this.getPower().add(power);
		power.getRole().add(this);
	}

	public void deletePower(Power power){
		this.getPower().remove(power);
		power.getRole().remove(this);
	}

	public void addRoleType(RoleType roleType){
		this.getRoleType().add(roleType);
		roleType.getRole().add(this);
	}

	public void deleteRoleType(RoleType roleType){
		this.getRoleType().remove(roleType);
		roleType.getRole().remove(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Role)) return false;

		Role role = (Role) o;

		if (!id.equals(role.id)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}