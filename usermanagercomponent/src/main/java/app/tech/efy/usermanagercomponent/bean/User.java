package app.tech.efy.usermanagercomponent.bean;

import android.os.Bundle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import app.tech.efy.componentbus.component.Component;
import app.tech.efy.componentbus.componentBus.routeManagement.RouteType;
import app.tech.efy.componentbus.messageReceiver.ComponentIntent;
import app.tech.efy.datamanagerutil.DataManagerUtils;
import app.tech.efy.httptranscomponentutils.HttpTrans;
import app.tech.efy.httptranscomponentutils.bean.JsonData;
import app.tech.efy.httptranscomponentutils.callback.DroneListCallBack;
import app.tech.efy.httptranscomponentutils.listener.DroneListListener;
import app.tech.efy.usermanagercomponent.TransUtils;
import app.tech.efy.usermanagercomponent.mylandmanager.MyLandImpl;
import app.tech.efy.usermanagercomponent.mylandmanager.MyLandManager;
import app.tech.efy.usermanagercomponent.teammanager.MyTeamImpl;
import app.tech.efy.usermanagercomponent.teammanager.MyTeamManager;
import app.tech.efy.usermanagercomponent.usermanager.UserImpl;
import app.tech.efy.usermanagerutils.HttpListener;
import app.tech.efy.usermanagerutils.UserManagerUtils;

/**
 * Created by dmx on 2016/8/10.
 */
public class User implements Serializable, UserImpl {
	private String id;
	private String userName;
	private String trueName;
	private String phoneNumber;
	private String password;
	private String deleteMark;
	private String roles;
	private int enabled;
	private MyTeamImpl mTeamManager;

	private MyLandImpl mLandManager;
	Component mComponent;

	public User(Component component) {
		this.mComponent = component;
		mTeamManager = new MyTeamManager(component, this);
		mLandManager = new MyLandManager(component, this);
	}

	private boolean isLogin;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPwd() {
		return password;
	}

	public void setPwd(String pwd) {
		this.password = pwd;
	}


	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}


	public String getDeleteMark() {
		return deleteMark;
	}

	public void setDeleteMark(String deleteMark) {
		this.deleteMark = deleteMark;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public boolean isLogin() {
		return isLogin;
	}

	@Override
	public void getControlDrone(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("latlong")) {
			LatLong latlong = (LatLong) parameter.getSerializable("latlong");
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			DroneListCallBack callBack = new DroneListCallBack(new DroneListListener() {
				@Override
				public void onBefore() {
					mListener.onBefore();
				}

				@Override
				public void onAfter() {

				}

				@Override
				public void inProgress(float v) {

				}

				@Override
				public void onError(JsonData error) {
					if (error != null) {
						switch (error.getMessage()) {
							default:
								mListener.onFailed(UserManagerUtils.ErrorType.DEFAULT);
								break;
						}
					} else {
						mListener.onFailed(UserManagerUtils.ErrorType.DEFAULT);
					}
				}

				@Override
				public void onResponse(List<app.tech.efy.httptranscomponentutils.bean.Drone> data) {
					List<Drone> newList = new ArrayList<>();
					for (app.tech.efy.httptranscomponentutils.bean.Drone drone : data) {
						newList.add(TransUtils.getLogicDrone(drone));
					}
					mListener.onSuccess(newList);
				}
			});
			Eppo mEppo = mTeamManager.getMyChooseEppo();
			if (mEppo == null) {
				mListener.onFailed(UserManagerUtils.ErrorType.ON_CHOOSE_EPPO);
				return;
			}
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_GET_NEAR_DRONE);
			parameter.putSerializable("callback", callBack);
			parameter.putString("id", getId());
			parameter.putString("eppoId", mEppo.getId());
			parameter.putDouble("latitude", latlong.getLatitude());
			parameter.putDouble("longitude", latlong.getLongitude());
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public void getObserveDrone(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("latlong")) {
			LatLong latlong = (LatLong) parameter.getSerializable("latlong");
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			DroneListCallBack callBack = new DroneListCallBack(new DroneListListener() {
				@Override
				public void onBefore() {
					mListener.onBefore();
				}

				@Override
				public void onAfter() {

				}

				@Override
				public void inProgress(float v) {

				}

				@Override
				public void onError(JsonData error) {
					if (error != null) {
						switch (error.getMessage()) {
							default:
								mListener.onFailed(UserManagerUtils.ErrorType.DEFAULT);
								break;
						}
					} else {
						mListener.onFailed(UserManagerUtils.ErrorType.DEFAULT);
					}
				}

				@Override
				public void onResponse(List<app.tech.efy.httptranscomponentutils.bean.Drone> data) {
					List<Drone> newList = new ArrayList<>();
					for (app.tech.efy.httptranscomponentutils.bean.Drone drone : data) {
						newList.add(TransUtils.getLogicDrone(drone));
					}
					mListener.onSuccess(newList);
				}
			});
			Eppo mEppo = mTeamManager.getMyChooseEppo();
			if (mEppo == null) {
				mListener.onFailed(UserManagerUtils.ErrorType.ON_CHOOSE_EPPO);
				return;
			}
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_GET_NEAR_OBSERVE_DRONE);
			parameter.putSerializable("callback", callBack);
			parameter.putString("id", getId());
			parameter.putString("eppoId", mEppo.getId());
			parameter.putDouble("latitude", latlong.getLatitude());
			parameter.putDouble("longitude", latlong.getLongitude());
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public void searchObserveDrone(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("latlong") && parameter.containsKey("content")) {
			LatLong latlong = (LatLong) parameter.getSerializable("latlong");
			final String content = parameter.getString("content");
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			DroneListCallBack callBack = new DroneListCallBack(new DroneListListener() {
				@Override
				public void onBefore() {
					mListener.onBefore();
				}

				@Override
				public void onAfter() {

				}

				@Override
				public void inProgress(float v) {

				}

				@Override
				public void onError(JsonData error) {
					if (error != null) {
						switch (error.getMessage()) {
							default:
								mListener.onFailed(UserManagerUtils.ErrorType.DEFAULT);
								break;
						}
					} else {
						mListener.onFailed(UserManagerUtils.ErrorType.DEFAULT);
					}
				}

				@Override
				public void onResponse(List<app.tech.efy.httptranscomponentutils.bean.Drone> data) {
					List<Drone> newList = new ArrayList<>();
					for (app.tech.efy.httptranscomponentutils.bean.Drone drone : data) {
						newList.add(TransUtils.getLogicDrone(drone));
					}
					mListener.onSuccess(filterDroneList(content, newList));
				}
			});
			Eppo mEppo = mTeamManager.getMyChooseEppo();
			if (mEppo == null) {
				mListener.onFailed(UserManagerUtils.ErrorType.ON_CHOOSE_EPPO);
				return;
			}
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_SEARCH_NEAR_OBSERVE_DRONE);
			parameter.putSerializable("callback", callBack);
			parameter.putString("id", getId());
			parameter.putString("eppoId", mEppo.getId());
			parameter.putDouble("latitude", latlong.getLatitude());
			parameter.putDouble("longitude", latlong.getLongitude());
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public void bindDrone(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("drone")) {
			Drone drone = (Drone) parameter.getSerializable("drone");
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			DroneListCallBack callBack = new DroneListCallBack(new DroneListListener() {
				@Override
				public void onBefore() {
					mListener.onBefore();
				}

				@Override
				public void onAfter() {

				}

				@Override
				public void inProgress(float v) {

				}

				@Override
				public void onError(JsonData error) {
					if (error != null) {
						switch (error.getMessage()) {
							default:
								mListener.onFailed(UserManagerUtils.ErrorType.DEFAULT);
								break;
						}
					} else {
						mListener.onFailed(UserManagerUtils.ErrorType.DEFAULT);
					}
				}

				@Override
				public void onResponse(List<app.tech.efy.httptranscomponentutils.bean.Drone> data) {
					List<Drone> newList = new ArrayList<>();
					for (app.tech.efy.httptranscomponentutils.bean.Drone drone : data) {
						newList.add(TransUtils.getLogicDrone(drone));
					}
					mListener.onSuccess(newList.get(0));
				}
			});
			Eppo mEppo = mTeamManager.getMyChooseEppo();
			if (mEppo == null) {
				mListener.onFailed(UserManagerUtils.ErrorType.ON_CHOOSE_EPPO);
				return;
			}
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_BIND_DRONE);
			parameter.putSerializable("callback", callBack);
			parameter.putString("id", getId());
			parameter.putString("droneId", drone.getId());
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public void unbindDrone(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("drone") && parameter.containsKey("latlong")) {
			LatLong latlong = (LatLong) parameter.getSerializable("latlong");
			Drone drone = (Drone) parameter.getSerializable("drone");
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			DroneListCallBack callBack = new DroneListCallBack(new DroneListListener() {
				@Override
				public void onBefore() {
					mListener.onBefore();
				}

				@Override
				public void onAfter() {

				}

				@Override
				public void inProgress(float v) {

				}

				@Override
				public void onError(JsonData error) {
					if (error != null) {
						switch (error.getMessage()) {
							default:
								mListener.onFailed(UserManagerUtils.ErrorType.DEFAULT);
								break;
						}
					} else {
						mListener.onFailed(UserManagerUtils.ErrorType.DEFAULT);
					}
				}

				@Override
				public void onResponse(List<app.tech.efy.httptranscomponentutils.bean.Drone> data) {
					List<Drone> newList = new ArrayList<>();
					for (app.tech.efy.httptranscomponentutils.bean.Drone drone : data) {
						newList.add(TransUtils.getLogicDrone(drone));
					}
					mListener.onSuccess(newList.get(0));
				}
			});
			Eppo mEppo = mTeamManager.getMyChooseEppo();
			if (mEppo == null) {
				mListener.onFailed(UserManagerUtils.ErrorType.ON_CHOOSE_EPPO);
				return;
			}
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_UNBIND_DRONE);
			parameter.putSerializable("callback", callBack);
			parameter.putString("droneId", drone.getId());
			parameter.putString("eppoid", mEppo.getId());
			parameter.putDouble("latitude", latlong.getLatitude());
			parameter.putDouble("longitude", latlong.getLongitude());
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public void loadLoginStatus() {
		isLogin = loadLogin();
		id = loadUID();
		userName = loadUSN();
		trueName = loadTrueName();
		password = loadPwd();
		mTeamManager.loadTeams();
	}

	private boolean loadLogin() {
		Bundle data = new Bundle();
		data.putString("componentName", "DataManager");
		data.putString("attribute", DataManagerUtils.DataManagerAttributes.ATTRIBUTE_ISLOGIN);
		ComponentIntent intent = new ComponentIntent(RouteType.DEFAULT, "excuteComponentAction", data);
		Bundle bundle = mComponent.getAttributeInner(intent);
		return bundle.getBoolean("login");
	}

	private String loadUID() {
		Bundle data = new Bundle();
		data.putString("componentName", "DataManager");
		data.putString("attribute", DataManagerUtils.DataManagerAttributes.ATTRIBUTE_UID);
		ComponentIntent intent = new ComponentIntent(RouteType.DEFAULT, "excuteComponentAction", data);

		Bundle bundle = mComponent.getAttributeInner(intent);
		return bundle.getString("uid");
	}

	private String loadUSN() {
		Bundle data = new Bundle();
		data.putString("componentName", "DataManager");
		data.putString("attribute", DataManagerUtils.DataManagerAttributes.ATTRIBUTE_USN);
		ComponentIntent intent = new ComponentIntent(RouteType.DEFAULT, "excuteComponentAction", data);

		Bundle bundle = mComponent.getAttributeInner(intent);
		return bundle.getString("usn");
	}

	private String loadTrueName() {
		Bundle data = new Bundle();
		data.putString("componentName", "DataManager");
		data.putString("attribute", DataManagerUtils.DataManagerAttributes.ATTRIBUTE_TRUENAME);
		ComponentIntent intent = new ComponentIntent(RouteType.DEFAULT, "excuteComponentAction", data);

		Bundle bundle = mComponent.getAttributeInner(intent);
		return bundle.getString("trueName");
	}

	private String loadPwd() {
		Bundle data = new Bundle();
		data.putString("componentName", "DataManager");
		data.putString("attribute", DataManagerUtils.DataManagerAttributes.ATTRIBUTE_PWD);
		ComponentIntent intent = new ComponentIntent(RouteType.DEFAULT, "excuteComponentAction", data);

		Bundle bundle = mComponent.getAttributeInner(intent);
		return bundle.getString("pwd");
	}

	@Override
	public MyLandImpl getMyLandManager() {
		return mLandManager;
	}

	@Override
	public MyTeamImpl getMyTeamManager() {
		return mTeamManager;
	}

	private void setUser(app.tech.efy.httptranscomponentutils.bean.User user) {
		this.id = user.getId();
		this.userName = user.getUserName();
		this.trueName = user.getTrueName();
		this.phoneNumber = user.getPhoneNumber();
		this.password = user.getPwd();
		this.deleteMark = user.getDeleteMark();
		this.roles = user.getRoles();
		this.enabled = user.getEnabled();
		mTeamManager.setTeams(user.getTeamIdsVsRoleIds());
		saveLoginData(user);
	}


	public void loginSuccess(app.tech.efy.httptranscomponentutils.bean.User user) {
		setUser(user);
		this.isLogin = true;
	}

	public void logout() {
		this.isLogin = false;
		saveLoginStatus(false);
	}

	private List<Drone> filterDroneList(String searchContent, List<Drone> response) {
		List<Drone> list = new ArrayList<>();
		if (response != null) {
			for (Drone drone : response) {
				if (((drone.getIccid() != null)
						&& (drone.getIccid().contains(searchContent))) |
						((drone.getTeamUavName() != null)
								&& (drone.getTeamUavName().contains(searchContent))) |
						((drone.getSn() != null)
								&& (drone.getSn().contains(searchContent)))) {
					list.add(drone);
				}
			}
		}
		return list;
	}

	private void saveLoginData(app.tech.efy.httptranscomponentutils.bean.User user) {
		saveLoginStatus(true);
		saveLoginUID(user.getId());
		saveLoginUSN(user.getUserName());
		saveLoginTrueName(user.getTrueName());
		saveLoginPWD(user.getPwd());
		saveTeams(user.getTeamIdsVsRoleIds());
	}

	private void saveTeams(String teamIdsVsRoleIds) {
		Bundle data = new Bundle();
		data.putString("componentName", "DataManager");
		data.putString("method", DataManagerUtils.DataManagerMethods.METHOD_SET_TEAMS);
		Bundle parameter = new Bundle();
		parameter.putString("team", teamIdsVsRoleIds);
		data.putBundle("parameter", parameter);
		ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
		mComponent.excuteActionSystrnicInner(intent);
	}

	private void saveLoginPWD(String pwd) {
		Bundle data = new Bundle();
		data.putString("componentName", "DataManager");
		data.putString("method", DataManagerUtils.DataManagerMethods.METHOD_SET_PWD);
		Bundle parameter = new Bundle();
		parameter.putString("pwd", pwd);
		data.putBundle("parameter", parameter);
		ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
		mComponent.excuteActionSystrnicInner(intent);
	}

	private void saveLoginTrueName(String trueName) {
		Bundle data = new Bundle();
		data.putString("componentName", "DataManager");
		data.putString("method", DataManagerUtils.DataManagerMethods.METHOD_SET_TRUENAME);
		Bundle parameter = new Bundle();
		parameter.putString("trueName", trueName);
		data.putBundle("parameter", parameter);
		ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
		mComponent.excuteActionSystrnicInner(intent);
	}

	private void saveLoginUSN(String usn) {
		Bundle data = new Bundle();
		data.putString("componentName", "DataManager");
		data.putString("method", DataManagerUtils.DataManagerMethods.METHOD_SET_USN);
		Bundle parameter = new Bundle();
		parameter.putString("usn", usn);
		data.putBundle("parameter", parameter);
		ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
		mComponent.excuteActionSystrnicInner(intent);
	}

	private void saveLoginUID(String uid) {
		Bundle data = new Bundle();
		data.putString("componentName", "DataManager");
		data.putString("method", DataManagerUtils.DataManagerMethods.METHOD_SET_UID);
		Bundle parameter = new Bundle();
		parameter.putString("uid", uid);
		data.putBundle("parameter", parameter);
		ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
		mComponent.excuteActionSystrnicInner(intent);
	}

	private void saveLoginStatus(boolean isLogin) {
		Bundle data = new Bundle();
		data.putString("componentName", "DataManager");
		data.putString("method", DataManagerUtils.DataManagerMethods.METHOD_SET_LOGIN_STATUS);
		Bundle parameter = new Bundle();
		parameter.putBoolean("login", isLogin);
		data.putBundle("parameter", parameter);
		ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
		mComponent.excuteActionSystrnicInner(intent);
	}


	@Override
	public String toString() {
		return "User{" +
				", id='" + id + '\'' +
				", userName='" + userName + '\'' +
				", trueName='" + trueName + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", password='" + password + '\'' +
				", deleteMark='" + deleteMark + '\'' +
				", roles='" + roles + '\'' +
				", enabled=" + enabled +
				'}';
	}

}
