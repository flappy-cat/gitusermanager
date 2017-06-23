package app.tech.efy.usermanagercomponent.bean;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import app.tech.efy.componentbus.component.Component;
import app.tech.efy.httptranscomponentutils.bean.Drone;
import app.tech.efy.httptranscomponentutils.bean.Plot;
import app.tech.efy.usermanagercomponent.teamdronemanager.TeamDroneImpl;
import app.tech.efy.usermanagercomponent.teamdronemanager.TeamDroneManager;
import app.tech.efy.usermanagercomponent.teamlandmanager.TeamLandImpl;
import app.tech.efy.usermanagercomponent.teamlandmanager.TeamLandManager;
import app.tech.efy.usermanagercomponent.teamplotmanager.TeamPlotImpl;
import app.tech.efy.usermanagercomponent.teamplotmanager.TeamPlotManager;
import app.tech.efy.usermanagercomponent.teamusermanager.TeamUserImpl;
import app.tech.efy.usermanagercomponent.teamusermanager.TeamUserManager;

/**
 * Created by Administrator on 2017/6/13.
 */
public class Eppo implements Serializable {
	private String id;
	private String teamName;
	private String creatorId;
	private String leaderId;
	private List<User> userList;
	private List<Drone> droneList;
	private String myRoleId;
	private String myRole;
	private TeamLandImpl landImpl;
	private TeamUserImpl userImpl;
	private TeamDroneImpl droneImpl;
	private TeamPlotImpl plotImpl;
	private List<Plot> plot;
	private Component mComponent;

	public Eppo(Component component) {
		this.mComponent = component;
		landImpl = new TeamLandManager(component, this);
		userImpl = new TeamUserManager(component, this);
		droneImpl = new TeamDroneManager(component, this);
		plotImpl = new TeamPlotManager(component, this);
	}

	public List<Drone> getDroneList() {
		return droneList;
	}

	public void setDroneList(List<Drone> droneList) {
		this.droneList = droneList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}

	public List<Plot> getPlot() {
		return plot;
	}

	public void setPlot(List<Plot> plot) {
		this.plot = plot;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getMyRole() {
		return myRole;
	}

	public void setMyRole(String myRole) {
		this.myRole = myRole;
	}

	public String getMyRoleId() {
		return myRoleId;
	}

	public void setMyRoleId(String myRoleId) {
		this.myRoleId = myRoleId;
	}

	public TeamLandImpl getTeamLandManager() {
		return landImpl;
	}

	public TeamUserImpl getTeamUserManager() {
		return userImpl;
	}

	public TeamDroneImpl getTeamDroneManager() {
		return droneImpl;
	}

	public TeamPlotImpl getTeamPlotManager() {
		return plotImpl;
	}

	public static String generateChooseEppo(Eppo eppo) {
		String choose = "";
		if (eppo != null) {
			choose = eppo.getId() + "," + eppo.getTeamName() + "," + eppo.getMyRoleId() + "," + eppo.getMyRole();
		}
		return choose;
	}

	public static List<Eppo> getMyEppo(Component component, String teamIdsVsRoleIds) {
		List<Eppo> list = new ArrayList<>();
		try {
			if (!TextUtils.isEmpty(teamIdsVsRoleIds)) {
				String[] splitString = teamIdsVsRoleIds.split(";");
				for (String s : splitString) {
					String[] singleLatLongAlt = s.split(",");
					if (singleLatLongAlt.length == 4) {
						Eppo eppo = new Eppo(component);
						eppo.setId(singleLatLongAlt[0]);
						eppo.setTeamName(singleLatLongAlt[1]);
						eppo.setMyRoleId(singleLatLongAlt[2]);
						eppo.setMyRole(singleLatLongAlt[3]);
						list.add(eppo);
					}
				}
			}
		} catch (Exception e) {
		}
		return list;
	}
}
