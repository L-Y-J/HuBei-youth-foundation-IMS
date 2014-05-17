package bean;

import org.apache.commons.lang.ObjectUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yongjie on 14-5-10.
 */
public class User {
	private Integer id;
	private String userName;
	private String name;
	private String innerId;
	private String password;
	private String leval;
	private Integer roleId;
	private Set power = new HashSet();
	private Set sendFile = new HashSet();
	private Set reciveFile = new HashSet();


	public User() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLeval() {
		return leval;
	}

	public void setLeval(String leval) {
		this.leval = leval;
	}

	public String getInnerId() {
		return innerId;
	}

	public void setInnerId(String innerId) {
		this.innerId = innerId;
	}

	public Set getPower() {
		return power;
	}

	public void setPower(Set power) {
		this.power = power;
	}

	public Set getSendFile() {
		return sendFile;
	}

	public void setSendFile(Set sendFile) {
		this.sendFile = sendFile;
	}

	public Set getReciveFile() {
		return reciveFile;
	}

	public void setReciveFile(Set reciveFile) {
		this.reciveFile = reciveFile;
	}

	public void addPower(Power power){
		this.getPower().add(power);
		power.getUser().add(this);
	}

	public void deletePower(Power power){
		this.getPower().remove(power);
		power.getUser().remove(this);
	}

	public void addSendFile(UserFile userFile){
		this.getSendFile().add(userFile);
	}

	public void deleteSendFile(UserFile userFile){
		this.getSendFile().remove(userFile);
		userFile.setFileFrom(null);
	}

	public void addReciveFile(UserFile userFile){
		this.getReciveFile().add(userFile);
	}

	public void deleteReciveFile(UserFile userFile){
		this.getReciveFile().remove(userFile);
		userFile.getFileTo().remove(this);
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;

		User user = (User) o;

		if (!id.equals(user.id)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
