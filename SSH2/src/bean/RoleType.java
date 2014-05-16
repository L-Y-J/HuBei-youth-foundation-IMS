package bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yongjie on 14-5-12.
 */
public class RoleType {

	private Integer id;
	private String typeName;
	private Set role = new HashSet();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Set getRole() {
		return role;
	}

	public void setRole(Set role) {
		this.role = role;
	}

	public void addRole(Role role){
		this.getRole().add(role);
		role.getRoleType().add(this);
	}

	public void deleteRole(Role role){
		this.getRole().remove(role);
		role.getRoleType().remove(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof RoleType)) return false;

		RoleType roleType = (RoleType) o;

		if (!id.equals(roleType.id)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}