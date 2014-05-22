package bean;

import java.util.Set;

/**
 * Created by yongjie on 14-5-17.
 */
public class FileFoder {

	Integer foderId;
	String foderName;
	Integer fatherFoderId;

	public FileFoder() {
	}

	public Integer getFoderId() {
		return foderId;
	}

	public void setFoderId(Integer foderId) {
		this.foderId = foderId;
	}

	public String getFoderName() {
		return foderName;
	}

	public void setFoderName(String foderName) {
		this.foderName = foderName;
	}

	public Integer getFatherFoderId() {
		return fatherFoderId;
	}

	public void setFatherFoderId(Integer fatherFoderId) {
		this.fatherFoderId = fatherFoderId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof FileFoder)) return false;

		FileFoder fileFoder = (FileFoder) o;

		if (!foderId.equals(fileFoder.foderId)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return foderId.hashCode();
	}
}
