package app.tech.efy.usermanagercomponent.bean;


import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/13.
 */
public class Drone implements Serializable{
	private String id;
	private String teamUavName;//别名
	private UavType uavType;//无人机型号
	private int controlled;//被接管 0可以接管 1 已经被接管
	private User controller;
	private int observed;//被观察 0可以观察 1不能被观察
	private int observes;//观察人数
	private String iccid;
	private String sn;
	private String online;
	private long endTime;

	public int getControlled() {
		return controlled;
	}

	public void setControlled(int controlled) {
		this.controlled = controlled;
	}

	public User getController() {
		return controller;
	}

	public void setController(User controller) {
		this.controller = controller;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getObserved() {
		return observed;
	}

	public void setObserved(int observed) {
		this.observed = observed;
	}

	public int getObserves() {
		return observes;
	}

	public void setObserves(int observes) {
		this.observes = observes;
	}

	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getTeamUavName() {
		return teamUavName;
	}

	public void setTeamUavName(String teamUavName) {
		this.teamUavName = teamUavName;
	}

	public UavType getUavType() {
		return uavType;
	}

	public void setUavType(UavType uavType) {
		this.uavType = uavType;
	}
}
