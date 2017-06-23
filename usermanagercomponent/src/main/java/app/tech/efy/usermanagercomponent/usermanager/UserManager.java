package app.tech.efy.usermanagercomponent.usermanager;

import android.os.Bundle;

import app.tech.efy.componentbus.componentBus.routeManagement.RouteType;
import app.tech.efy.componentbus.messageReceiver.ComponentIntent;
import app.tech.efy.httptranscomponentutils.HttpTrans;
import app.tech.efy.httptranscomponentutils.bean.JsonData;
import app.tech.efy.httptranscomponentutils.callback.JsonDataCallBack;
import app.tech.efy.httptranscomponentutils.callback.UserCallBack;
import app.tech.efy.httptranscomponentutils.listener.JsonDataListener;
import app.tech.efy.httptranscomponentutils.listener.UserListener;
import app.tech.efy.usermanagercomponent.TransUtils;
import app.tech.efy.usermanagercomponent.UserComponent;
import app.tech.efy.usermanagercomponent.bean.User;
import app.tech.efy.usermanagerutils.HttpListener;
import app.tech.efy.usermanagerutils.UserManagerUtils;

/**
 * Created by Administrator on 2017/6/6.
 */
public class UserManager implements UserManagerImpl {
	private UserImpl mUser;

	private UserComponent mComponent;

	public UserManager(UserComponent component) {
		mComponent = component;
		mUser = new User(component);
	}


	@Override
	public void login(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("usn") && parameter.containsKey("pwd")) {
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			UserCallBack callBack = new UserCallBack(new UserListener() {
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
							case "Login Error,can not find this user by username":
								mListener.onFailed(UserManagerUtils.ErrorType.USER_NO_EXIST);
								break;
							case "Login Error,passWord is wrong":
								mListener.onFailed(UserManagerUtils.ErrorType.PWD_ERROR);
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
				public void onResponse(app.tech.efy.httptranscomponentutils.bean.User data) {
					User user= TransUtils.getLogicUser(data);
					mUser.loginSuccess(data);
					mListener.onSuccess(user);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_LOGIN);
			parameter.putSerializable("callback", callBack);
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public void getVertify(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("phone")) {
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
					if (error != null) {
						switch (error.getMessage()) {
							case "phoneNumber used":
								mListener.onFailed(UserManagerUtils.ErrorType.PHONE_ISREGIST);
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
					mListener.onSuccess(data);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_GET_VERTIFY);
			parameter.putSerializable("callback", callBack);
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public void vertify(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("phone") && parameter.containsKey("verifycode")) {
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

					if (error != null) {
						switch (error.getMessage()) {
							case "verifycode incorrect":
								mListener.onFailed(UserManagerUtils.ErrorType.VERTIFYCODE_ERROR);
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
					mListener.onSuccess(data);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_VERTIFY);
			parameter.putSerializable("callback", callBack);
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public void regist(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("phone") && parameter.containsKey("pwd") && parameter.containsKey("usn") && parameter.containsKey("name")) {
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			UserCallBack callBack = new UserCallBack(new UserListener() {
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
							case "userName repeat":
								mListener.onFailed(UserManagerUtils.ErrorType.USN_REPEAT);
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
				public void onResponse(app.tech.efy.httptranscomponentutils.bean.User data) {
					mListener.onSuccess(data);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_REGIST);
			parameter.putSerializable("callback", callBack);
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}


	@Override
	public void getVertifyForForgetPwd(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("phone")) {
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
					if (error != null) {
						switch (error.getMessage()) {
							case "phoneNumber not in used":
								mListener.onFailed(UserManagerUtils.ErrorType.PHONE_NOREGIST);
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
					mListener.onSuccess(data);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_GET_VERTIFY_FORGET_PWD);
			parameter.putSerializable("callback", callBack);
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public void vertifyForForgetPwd(Bundle parameter) {
		if (parameter.containsKey("callback") && parameter.containsKey("phone") && parameter.containsKey("verifycode")) {
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
					if (error != null) {
						switch (error.getMessage()) {
							case "verifycode incorrect":
								mListener.onFailed(UserManagerUtils.ErrorType.VERTIFYCODE_ERROR);
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
					mListener.onSuccess(data);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_VERTIFY_FORGET_PWD);
			parameter.putSerializable("callback", callBack);
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public void resetPwd(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("phone") && parameter.containsKey("pwd")) {
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
					mListener.onSuccess(data);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_UPDATE_PWD);
			parameter.putSerializable("callback", callBack);
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public void logout(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("usn")) {
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
					mUser.logout();
					mListener.onSuccess(data);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_LOGOUT);
			parameter.putSerializable("callback", callBack);
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public UserImpl getMyUser() {
		return mUser;
	}

	@Override
	public boolean isLogin() {
		boolean isLogin = false;
		if (mUser != null && mUser.isLogin())
			isLogin = true;
		return isLogin;
	}



}
