package bean;

/**
 * Created by yongjie on 14-5-21.
 */
public class Photo {
	public int PhotoId;
	public String PhotoName;
	public java.lang.String Information;
	public java.lang.String PhotoType;
	public int Number;
	public Integer applicationId;
	public Integer monthlyProgressId;

	public Photo() {
	}

	public int getPhotoId() {
		return PhotoId;
	}

	public void setPhotoId(int photoId) {
		PhotoId = photoId;
	}

	public String getPhotoName() {
		return PhotoName;
	}

	public void setPhotoName(String photoName) {
		PhotoName = photoName;
	}

	public String getInformation() {
		return Information;
	}

	public void setInformation(String information) {
		Information = information;
	}

	public String getPhotoType() {
		return PhotoType;
	}

	public void setPhotoType(String photoType) {
		PhotoType = photoType;
	}

	public int getNumber() {
		return Number;
	}

	public void setNumber(int number) {
		Number = number;
	}

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	public Integer getMonthlyProgressId() {
		return monthlyProgressId;
	}

	public void setMonthlyProgressId(Integer monthlyProgressId) {
		this.monthlyProgressId = monthlyProgressId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Photo)) return false;

		Photo photo = (Photo) o;

		if (PhotoId != photo.PhotoId) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return PhotoId;
	}
}
