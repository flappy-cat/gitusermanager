package app.tech.efy.usermanagercomponent.teamusermanager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import app.tech.efy.componentbus.component.Component;
import app.tech.efy.componentbus.componentBus.routeManagement.RouteType;
import app.tech.efy.componentbus.messageReceiver.ComponentIntent;
import app.tech.efy.httptranscomponentutils.HttpTrans;
import app.tech.efy.httptranscomponentutils.bean.JsonData;
import app.tech.efy.httptranscomponentutils.callback.JsonDataCallBack;
import app.tech.efy.httptranscomponentutils.callback.UserListCallBack;
import app.tech.efy.httptranscomponentutils.listener.JsonDataListener;
import app.tech.efy.httptranscomponentutils.listener.UserListListener;
import app.tech.efy.usermanagercomponent.TransUtils;
import app.tech.efy.usermanagercomponent.bean.Eppo;
import app.tech.efy.usermanagercomponent.bean.User;
import app.tech.efy.usermanagerutils.HttpListener;
import app.tech.efy.usermanagerutils.UserManagerUtils;

/**
 * Created by Administrator on 2017/6/16.
 */
public class TeamUserManager implements TeamUserImpl {
	private Component mComponent;
	private Eppo mEppo;
	private List<User> mUserList;

	public TeamUserManager(Component component, Eppo eppo) {
		this.mComponent = component;
		this.mEppo = eppo;
	}

	@Override
	public void getTeamUser(Bundle parameter) {
		if (parameter.containsKey("listener")) {
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			UserListCallBack callBack = new UserListCallBack(new UserListListener() {
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
				public void onResponse(List<app.tech.efy.httptranscomponentutils.bean.User> data) {
					List<User> newList = new ArrayList<>();
					for (app.tech.efy.httptranscomponentutils.bean.User user : data) {
						newList.add(TransUtils.getLogicUser(user));
					}
					setTeamUser(newList);
					mListener.onSuccess(newList);
				}
			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_GET_MEMBER_OF_EPPO);
			parameter.putSerializable("callback", callBack);
			parameter.putString("eppoId", mEppo.getId());
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public void deleteUser(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("user")) {
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			final User user = (User) parameter.getSerializable("user");
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
					deleteuser(user);
					mListener.onSuccess(data);
				}


			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_DELETE_MEMBER_TO_EPPO);
			parameter.putSerializable("callback", callBack);
			parameter.putString("eppoId", mEppo.getId());
			parameter.putString("userId", user.getId());
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public void queryUser(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("str")) {
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			final String content = parameter.getString("str");
			UserListCallBack callBack = new UserListCallBack(new UserListListener() {
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
				public void onResponse(List<app.tech.efy.httptranscomponentutils.bean.User> data) {
					List<User> newList = new ArrayList<>();
					for (app.tech.efy.httptranscomponentutils.bean.User user : data) {
						newList.add(TransUtils.getLogicUser(user));
					}
//					setTeamUser(newList);
					mListener.onSuccess(newList);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_SEARCH_USER);
			parameter.putSerializable("callback", callBack);
			parameter.putString("str", content);
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public void addUser(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("user")) {
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			final User user = (User) parameter.getSerializable("user");
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
							case "Players already exist":
								mListener.onFailed(UserManagerUtils.ErrorType.ALREADY_ADD_MEMBER);
								return;
							case "More than ten":
								mListener.onFailed(UserManagerUtils.ErrorType.MORE_THEN_TEN);
								return;
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
					adduser(user);
//					setTeamUser(newList);
					mListener.onSuccess(data);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_ADD_MEMBER_TO_EPPO);
			parameter.putSerializable("callback", callBack);
			parameter.putString("userId", user.getId());
			parameter.putString("eppoId", mEppo.getId());
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	private void deleteuser(User user) {
		if (mUserList != null) {
			if (mUserList.contains(user)) {
				mUserList.remove(user);
			}
		}
	}

	private void adduser(User user) {
		if (mUserList == null) {
			mUserList = new ArrayList<>();
		}
		mUserList.add(user);
	}

	public void setTeamUser(List<User> list) {
		if (mUserList == null) {
			mUserList = new ArrayList<>();
		}
		this.mUserList.clear();
		for (User user : list) {
			this.mUserList.add(user);
		}
	}
}
