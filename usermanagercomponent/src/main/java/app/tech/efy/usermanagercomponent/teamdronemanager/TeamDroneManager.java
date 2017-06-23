package app.tech.efy.usermanagercomponent.teamdronemanager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import app.tech.efy.componentbus.component.Component;
import app.tech.efy.componentbus.componentBus.routeManagement.RouteType;
import app.tech.efy.componentbus.messageReceiver.ComponentIntent;
import app.tech.efy.httptranscomponentutils.HttpTrans;
import app.tech.efy.httptranscomponentutils.bean.JsonData;
import app.tech.efy.httptranscomponentutils.callback.DroneListCallBack;
import app.tech.efy.httptranscomponentutils.callback.JsonDataCallBack;
import app.tech.efy.httptranscomponentutils.listener.DroneListListener;
import app.tech.efy.httptranscomponentutils.listener.JsonDataListener;
import app.tech.efy.usermanagercomponent.TransUtils;
import app.tech.efy.usermanagercomponent.bean.Drone;
import app.tech.efy.usermanagercomponent.bean.Eppo;
import app.tech.efy.usermanagerutils.HttpListener;
import app.tech.efy.usermanagerutils.UserManagerUtils;

/**
 * Created by Administrator on 2017/6/17.
 */
public class TeamDroneManager implements TeamDroneImpl {
	private Component mComponent;
	private Eppo mEppo;
	private List<Drone> mDroneList;


	public TeamDroneManager(Component component, Eppo eppo) {
		this.mComponent = component;
		this.mEppo = eppo;
	}

	@Override
	public void getTeamDrone(Bundle parameter) {
		if (parameter.containsKey("listener")) {
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
					setTeamDrone(newList);
					mListener.onSuccess(newList);
				}
			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_GET_DRONE_OF_EPPO);
			parameter.putSerializable("callback", callBack);
			parameter.putString("eppoId", mEppo.getId());
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public void queryDrone(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("iccid")) {
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			final String iccid = parameter.getString("iccid");
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
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_SEARCH_DRONE);
			parameter.putSerializable("callback", callBack);
			parameter.putString("iccid", iccid);
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public void addDroneByUsb(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("iccid")) {
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			final String iccid = parameter.getString("iccid");
			JsonDataCallBack callBack = new JsonDataCallBack(new JsonDataListener() {
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
							case "already linked by other team":
								mListener.onFailed(UserManagerUtils.ErrorType.ALREADY_ADD_DRONE_BY_OTHERS);
								break;
							case "already linked":
								mListener.onFailed(UserManagerUtils.ErrorType.ALREADY_ADD_DRONE);
								break;
							default:
								mListener.onFailed(UserManagerUtils.ErrorType.DEFAULT);
								break;
						}
					} else {
						mListener.onFailed(UserManagerUtils.ErrorType.DEFAULT);
					}
				}

				@Override
				public void onResponse(JsonData data) {
					Drone drone = new Drone();
					drone.setIccid(iccid);
					addDrone(drone);
					mListener.onSuccess(data);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_ADD_DRONE_TO_EPPO);
			parameter.putSerializable("callback", callBack);
			parameter.putString("iccid", iccid);
			parameter.putString("eppoId", mEppo.getId());
			parameter.putInt("isUsb", 1);
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public void addDroneByNormal(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("drone")) {
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			final Drone drone = (Drone) parameter.getSerializable("drone");
			JsonDataCallBack callBack = new JsonDataCallBack(new JsonDataListener() {
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
							case "already linked by other team":
								mListener.onFailed(UserManagerUtils.ErrorType.ALREADY_ADD_DRONE_BY_OTHERS);
								break;
							case "already linked":
								mListener.onFailed(UserManagerUtils.ErrorType.ALREADY_ADD_DRONE);
								break;
							default:
								mListener.onFailed(UserManagerUtils.ErrorType.DEFAULT);
								break;
						}
					} else {
						mListener.onFailed(UserManagerUtils.ErrorType.DEFAULT);
					}
				}

				@Override
				public void onResponse(JsonData data) {
					addDrone(drone);
					mListener.onSuccess(data);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_ADD_DRONE_TO_EPPO);
			parameter.putSerializable("callback", callBack);
			parameter.putString("iccid", drone.getIccid());
			parameter.putString("eppoId", mEppo.getId());
			parameter.putInt("isUsb", 0);
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	private void addDrone(Drone drone) {
		if (mDroneList == null) {
			mDroneList = new ArrayList<>();
		}
		mDroneList.add(drone);
	}

	@Override
	public void deleteDrone(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("drone")) {
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			final Drone drone = (Drone) parameter.getSerializable("drone");
			JsonDataCallBack callBack = new JsonDataCallBack(new JsonDataListener() {
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
				public void onResponse(JsonData data) {
					deleteDrone(drone);
					mListener.onSuccess(data);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_DELETE_DRONE_TO_EPPO);
			parameter.putSerializable("callback", callBack);
			parameter.putString("iccid", drone.getIccid());
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	private void deleteDrone(Drone drone) {
		if (mDroneList != null) {
			if (mDroneList.contains(drone)) {
				mDroneList.remove(drone);
			}
		}
	}

	@Override
	public void editDroneName(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("drone") && parameter.containsKey("name")) {
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			final Drone drone = (Drone) parameter.getSerializable("drone");
			final String name = parameter.getString("name");
			JsonDataCallBack callBack = new JsonDataCallBack(new JsonDataListener() {
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
				public void onResponse(JsonData data) {
					editDroneName(drone, name);
					mListener.onSuccess(data);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_EDIT_DRONE_NAME);
			parameter.putSerializable("callback", callBack);
			parameter.putString("droneId", drone.getId());
			parameter.putString("name", name);
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	private void editDroneName(Drone drone, String name) {
		if (mDroneList != null) {
			int index = mDroneList.indexOf(drone);
			if (index >= 0) {
				drone.setTeamUavName(name);
				mDroneList.set(index, drone);
			}
		}
	}

	public void setTeamDrone(List<Drone> list) {
		if (mDroneList == null) {
			mDroneList = new ArrayList<>();
		}
		this.mDroneList.clear();
		for (Drone drone : list) {
			this.mDroneList.add(drone);
		}
	}
}
