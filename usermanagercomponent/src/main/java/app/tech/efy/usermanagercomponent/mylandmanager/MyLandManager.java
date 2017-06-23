package app.tech.efy.usermanagercomponent.mylandmanager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import app.tech.efy.componentbus.component.Component;
import app.tech.efy.componentbus.componentBus.routeManagement.RouteType;
import app.tech.efy.componentbus.messageReceiver.ComponentIntent;
import app.tech.efy.httptranscomponentutils.HttpTrans;
import app.tech.efy.httptranscomponentutils.bean.JsonData;
import app.tech.efy.httptranscomponentutils.callback.JsonDataCallBack;
import app.tech.efy.httptranscomponentutils.callback.LandCallBack;
import app.tech.efy.httptranscomponentutils.callback.LandListCallBack;
import app.tech.efy.httptranscomponentutils.listener.JsonDataListener;
import app.tech.efy.httptranscomponentutils.listener.LandListListener;
import app.tech.efy.httptranscomponentutils.listener.LandListener;
import app.tech.efy.usermanagercomponent.TransUtils;
import app.tech.efy.usermanagercomponent.bean.Eppo;
import app.tech.efy.usermanagercomponent.bean.Land;
import app.tech.efy.usermanagercomponent.bean.User;
import app.tech.efy.usermanagerutils.HttpListener;
import app.tech.efy.usermanagerutils.UserManagerUtils;


/**
 * Created by Administrator on 2017/6/13.
 */
public class MyLandManager implements MyLandImpl {
	private List<Land> list;

	private User mUser;
	private Component mComponent;

	public MyLandManager(Component component, User user) {
		list = new ArrayList<>();
		this.mComponent = component;
		this.mUser = user;
	}


	@Override
	public void addLand(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("land")) {
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			Land land = (Land) parameter.getSerializable("land");
			LandCallBack callBack = new LandCallBack(new LandListener() {
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
					mListener.onFailed(UserManagerUtils.ErrorType.DEFAULT);
				}

				@Override
				public void onResponse(app.tech.efy.httptranscomponentutils.bean.Land land) {
					Land transland = TransUtils.getLogicLand(land);
					addLand(transland);
					mListener.onSuccess(land);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_UPDATE_LAND);
			parameter.putSerializable("callback", callBack);
			parameter.putSerializable("land", TransUtils.getHttpLand(land));
			parameter.putString("id", mUser.getId());
			Eppo eppo = mUser.getMyTeamManager().getMyChooseEppo();
			if (eppo == null) {
				mListener.onFailed(UserManagerUtils.ErrorType.ON_CHOOSE_EPPO);
				return;
			}
			parameter.putString("eppoid", String.valueOf(eppo.getId()));
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public void editLand(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("land")) {
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			final Land land = (Land) parameter.getSerializable("land");
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
					mListener.onFailed(UserManagerUtils.ErrorType.DEFAULT);
				}

				@Override
				public void onResponse(JsonData data) {
					editLand(land);
					mListener.onSuccess(land);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_EDIT_LAND);
			parameter.putSerializable("callback", callBack);
			parameter.putSerializable("land", TransUtils.getHttpLand(land));
			parameter.putString("id", mUser.getId());
			Eppo eppo = mUser.getMyTeamManager().getMyChooseEppo();
			if (eppo == null) {
				mListener.onFailed(UserManagerUtils.ErrorType.ON_CHOOSE_EPPO);
				return;
			}
			parameter.putString("eppoid", String.valueOf(eppo.getId()));
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public void deleteLand(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("land")) {
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			final Land land = (Land) parameter.getSerializable("land");
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
					mListener.onFailed(UserManagerUtils.ErrorType.DEFAULT);
				}

				@Override
				public void onResponse(JsonData data) {
					deleteLand(land);
					mListener.onSuccess(land);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_DELETE_LAND);
			parameter.putSerializable("callback", callBack);
			parameter.putString("id", String.valueOf(land.getId()));
			Eppo eppo = mUser.getMyTeamManager().getMyChooseEppo();
			if (eppo == null) {
				mListener.onFailed(UserManagerUtils.ErrorType.ON_CHOOSE_EPPO);
				return;
			}
			parameter.putString("eppoid", String.valueOf(eppo.getId()));
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public void getMyLand(Bundle parameter) {
		if (parameter.containsKey("listener")) {
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			LandListCallBack callBack = new LandListCallBack(new LandListListener() {
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
					mListener.onFailed(UserManagerUtils.ErrorType.DEFAULT);
				}

				@Override
				public void onResponse(List<app.tech.efy.httptranscomponentutils.bean.Land> data) {
					List<Land> newList = new ArrayList<>();
					for (app.tech.efy.httptranscomponentutils.bean.Land land : data) {
						newList.add(TransUtils.getLogicLand(land));
					}
					setMyLand(newList);
					mListener.onSuccess(newList);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_GET_MY_CLOUD_LAND);
			parameter.putSerializable("callback", callBack);
			parameter.putString("id", mUser.getId());
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public void shareLand(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("land") && parameter.containsKey("eppo")) {
			final Land land = (Land) parameter.getSerializable("land");
			final List<Eppo> list = (List<Eppo>) parameter.getSerializable("eppo");
			land.setShareLevel("n");
			land.setZhiBaoTeams(list);
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
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
					mListener.onFailed(UserManagerUtils.ErrorType.DEFAULT);
				}

				@Override
				public void onResponse(JsonData data) {
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_SHARE_LAND);
			data.putSerializable("land", TransUtils.getHttpLand(land));
			parameter.putSerializable("callback", callBack);
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	public void setMyLand(List<Land> list) {
		if (list == null) {
			list = new ArrayList<>();
		}
		this.list.clear();
		for (Land land : list) {
			this.list.add(land);
		}
	}

	public void addLand(Land land) {
		if (list == null) {
			list = new ArrayList<>();
		}
		list.add(land);
	}

	public void editLand(Land land) {
		for (int i = 0; i < list.size(); i++) {
			Land data = list.get(i);
			if (String.valueOf(data.getId()).equals(String.valueOf(land.getId()))) {
				list.set(i, land);
			}
		}
	}

	public void deleteLand(Land land) {
		for (int i = 0; i < list.size(); i++) {
			Land data = list.get(i);
			if (data.getId().equals(land.getId())) {
				list.remove(i);
			}
		}
	}
}
