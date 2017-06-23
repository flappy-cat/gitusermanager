package app.tech.efy.usermanagercomponent.bean;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import app.tech.efy.httptranscomponentutils.bean.LatLong;
import app.tech.efy.httptranscomponentutils.bean.LatLongAlt;

/**
 * Created by Administrator on 2016/12/30.
 */
public class Land implements Serializable {
	private Long id;
	private String landPos;//= "37.567889,117.456789,0;37.456784,117.456789,1;37.567889,117.456789,0;37.456784,117.456789,1";      //0高德  1Gps
	private String creatorId;//="userId";

	private String creatorName;//="userId";

	private String landName;//="老王的菜地";
	private String landMaster;//="隔壁老王";
	private String phoneNumber;//="13882888228";
	private String remark;//="老王remark";
	private long createTime;//="20170107";
	private String createPos;//="37.567889,117.456789";
	private Long companyInfoId;
	private User creator;
	private String landArea;

	private String shareLevel;
	private List<Eppo> zhiBaoTeams;

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public User getCreator() {
		return creator;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<LatLongAlt> getLandPos() {
		List<LatLongAlt> list = new ArrayList<>();
		try {
			if (!TextUtils.isEmpty(landPos)) {
				String[] splitString = landPos.split(";");
				for (String s : splitString) {
					String[] singleLatLongAlt = s.split(",");
					boolean isGps = false;
					if (singleLatLongAlt[2].equals("1")) {
						isGps = true;
					}
					LatLongAlt latLongAlt = new LatLongAlt(Double.valueOf(singleLatLongAlt[0]), Double.valueOf(singleLatLongAlt[1]), 20, isGps);
					list.add(latLongAlt);
				}
			}
		} catch (Exception e) {
		}
		return list;
	}

	public void setLandPos(List<LatLongAlt> landPos) {
		StringBuilder mMessageBuilder = new StringBuilder();
		for (LatLongAlt latlong : landPos) {
			String isGps = "0";
			if (latlong.getIsGps()) {
				isGps = "1";
			}
			mMessageBuilder.append("" + latlong.getLatitude() + "," + latlong.getLongitude() + "," + isGps + ";");
		}
		this.landPos = mMessageBuilder.substring(0, mMessageBuilder.length() - 1);

	}

	public String getPosString() {
		return landPos;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creator) {
		this.creatorId = creator;
	}

	public String getLandName() {
		return landName;
	}

	public void setLandName(String landName) {
		this.landName = landName;
	}

	public String getLandMaster() {
		return landMaster;
	}

	public void setLandMaster(String landMaster) {
		this.landMaster = landMaster;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public LatLong getCreatePosition() {
		LatLong latlong = null;
		String[] singleLatLongAlt = createPos.split(",");
		if (singleLatLongAlt.length == 3) {
			latlong = new LatLong(Double.valueOf(singleLatLongAlt[0]), Double.valueOf(singleLatLongAlt[1]));
		}

		return latlong;
	}

	public String getCreatePos() {
		return createPos;
	}

	public void setCreatePos(String createPos) {
		this.createPos = createPos;
	}

	public void setCreatePosition(LatLong location) {
		if (location != null) {
			this.createPos = location.getLatitude() + "," + location.getLongitude() + ",0";
		} else {
			this.createPos = "";
		}
	}

	public Long getCompanyInfoId() {
		return companyInfoId;
	}

	public void setCompanyInfoId(Long companyInfoId) {
		this.companyInfoId = companyInfoId;
	}

	public String getLandArea() {
		return landArea;
	}

	public void setLandArea(String landArea) {
		this.landArea = landArea;
	}

	public String getShareLevel() {
		return shareLevel;
	}

	public void setShareLevel(String shareLevel) {
		this.shareLevel = shareLevel;
	}

	public List<Eppo> getZhiBaoTeams() {
		return zhiBaoTeams;
	}

	public Long[] generateList() {
		Long[] ids = new Long[zhiBaoTeams.size()];
		for (int i = 0; i < zhiBaoTeams.size(); i++) {
			ids[i] = Long.parseLong(zhiBaoTeams.get(i).getId());
		}
		return ids;
	}

	public void setZhiBaoTeams(List<Eppo> zhiBaoTeams) {
		this.zhiBaoTeams = zhiBaoTeams;
		Long[] ids = new Long[zhiBaoTeams.size()];
		for (int i = 0; i < zhiBaoTeams.size(); i++) {
			ids[i] = Long.parseLong(zhiBaoTeams.get(i).getId());
		}
//		setZhiBaoTeamIds(ids);
	}


	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	@Override
	public String toString() {
		return "Land{" +
				", id=" + id +
				", landPos='" + landPos + '\'' +
				", creatorId='" + creatorId + '\'' +
				", creatorName='" + creatorName + '\'' +
				", landName='" + landName + '\'' +
				", landMaster='" + landMaster + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", remark='" + remark + '\'' +
				", createTime=" + createTime +
				", createPos='" + createPos + '\'' +
				", companyInfoId=" + companyInfoId +
				", creator=" + creator +
				", landArea='" + landArea + '\'' +
				", shareLevel='" + shareLevel + '\'' +
				", zhiBaoTeams=" + zhiBaoTeams +
				'}';
	}
}

