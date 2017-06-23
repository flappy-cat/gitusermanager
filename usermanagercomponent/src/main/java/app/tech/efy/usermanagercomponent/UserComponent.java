package app.tech.efy.usermanagercomponent;

import android.os.Bundle;

import app.tech.efy.componentbus.commonMessageReceiver.CommonIntentFiler;
import app.tech.efy.componentbus.component.Component;
import app.tech.efy.componentbus.messageReceiver.ComponentIntent;
import app.tech.efy.componentbus.messageReceiver.IMessageReceiver;
import app.tech.efy.usermanagercomponent.bean.Eppo;
import app.tech.efy.usermanagercomponent.usermanager.UserManager;
import app.tech.efy.usermanagercomponent.usermanager.UserManagerImpl;
import app.tech.efy.usermanagerutils.UserManagerUtils;


/**
 * Created by ck on 2017/4/28.
 */
public class UserComponent extends Component {
	private final String testAction = "testAction";
	UserManagerImpl mUserManager;

	private CommonIntentFiler intentFiler = new CommonIntentFiler();

	public UserComponent() {
		intentFiler.addComponentMessageAction("Test");
		initComponentMessageReceiver(messageReceiver, intentFiler);
		mUserManager = new UserManager(this);
	}

	@Override
	public ComponentIntent provideComponentAction(String actionName, Bundle parameter) {//调用
		Bundle bundle = new Bundle();
		switch (actionName) {
			case UserManagerUtils.UserManagerMethods.METHOD_LOGIN:
				mUserManager.login(parameter);
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_GET_VERTIFY:
				mUserManager.getVertify(parameter);
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_VERTIFY:
				mUserManager.vertify(parameter);
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_REGIST:
				mUserManager.regist(parameter);
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_GET_VERTIFY_FORGET_PWD:
				mUserManager.getVertifyForForgetPwd(parameter);
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_VERTIFY_FORGET_PWD:
				mUserManager.vertifyForForgetPwd(parameter);
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_UPDATE_PWD:
				mUserManager.resetPwd(parameter);
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_GET_ISLOGIN:
				bundle.putBoolean("isLogin", mUserManager.isLogin());
			case UserManagerUtils.UserManagerMethods.METHOD_GET_NEAR_CLOUD_LAND:
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_GET_MY_CLOUD_LAND:
				mUserManager.getMyUser().getMyLandManager().getMyLand(parameter);
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_GET_NEAR_DRONE:
				mUserManager.getMyUser().getControlDrone(parameter);
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_UPDATE_LAND:
				mUserManager.getMyUser().getMyLandManager().addLand(parameter);
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_EDIT_LAND:
				mUserManager.getMyUser().getMyLandManager().editLand(parameter);
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_DELETE_LAND:
				mUserManager.getMyUser().getMyLandManager().deleteLand(parameter);
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_UPLOAD_PLOT:
				mUserManager.getMyUser().getMyTeamManager().getMyChooseEppo().getTeamPlotManager().addTeamPlot(parameter);
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_EDIT_PLOT:
				mUserManager.getMyUser().getMyTeamManager().getMyChooseEppo().getTeamPlotManager().editTeamPlot(parameter);
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_CANCEL_PLOT:
				mUserManager.getMyUser().getMyTeamManager().getMyChooseEppo().getTeamPlotManager().cancelPlot(parameter);
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_GET_NEAR_OBSERVE_DRONE:
				mUserManager.getMyUser().getObserveDrone(parameter);
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_SEARCH_NEAR_OBSERVE_DRONE:
				mUserManager.getMyUser().searchObserveDrone(parameter);
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_UNBIND_DRONE:
				mUserManager.getMyUser().unbindDrone(parameter);
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_BIND_DRONE:
				mUserManager.getMyUser().bindDrone(parameter);
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_LOGOUT:
				mUserManager.logout(parameter);
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_ADD_EPPO:
				mUserManager.getMyUser().getMyTeamManager().addTeam(parameter);
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_GET_EPPO:
				mUserManager.getMyUser().getMyTeamManager().getMyTeams();
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_SEARCH_USER:
				if (parameter.containsKey("eppo")) {
					Eppo eppo = (Eppo) parameter.getSerializable("eppo");
					eppo.getTeamUserManager().queryUser(parameter);
				}
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_SEARCH_DRONE:
				if (parameter.containsKey("eppo")) {
					Eppo eppo = (Eppo) parameter.getSerializable("eppo");
					eppo.getTeamDroneManager().queryDrone(parameter);
				}
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_ADD_MEMBER_TO_EPPO:
				if (parameter.containsKey("eppo")) {
					Eppo eppo = (Eppo) parameter.getSerializable("eppo");
					eppo.getTeamUserManager().addUser(parameter);
				}
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_DELETE_MEMBER_TO_EPPO:
				if (parameter.containsKey("eppo")) {
					Eppo eppo = (Eppo) parameter.getSerializable("eppo");
					eppo.getTeamUserManager().deleteUser(parameter);
				}
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_ADD_DRONE_TO_EPPO:
				if (parameter.containsKey("eppo") && parameter.containsKey("isUsb")) {
					Eppo eppo = (Eppo) parameter.getSerializable("eppo");
					boolean isUsb = parameter.getInt("isUsb") == 1 ? true : false;
					if (isUsb) {
						eppo.getTeamDroneManager().addDroneByUsb(parameter);
					} else {
						eppo.getTeamDroneManager().addDroneByNormal(parameter);
					}
				}
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_DELETE_DRONE_TO_EPPO:
				if (parameter.containsKey("eppo")) {
					Eppo eppo = (Eppo) parameter.getSerializable("eppo");
					eppo.getTeamDroneManager().deleteDrone(parameter);
				}
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_EDIT_DRONE_NAME:
				if (parameter.containsKey("eppo")) {
					Eppo eppo = (Eppo) parameter.getSerializable("eppo");
					eppo.getTeamDroneManager().editDroneName(parameter);
				}
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_GET_CLOUD_PLOT:
				mUserManager.getMyUser().getMyTeamManager().getMyChooseEppo().getTeamPlotManager().getTeamPlot(parameter);
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_GET_MEMBER_OF_EPPO:
				if (parameter.containsKey("eppo")) {
					Eppo eppo = (Eppo) parameter.getSerializable("eppo");
					eppo.getTeamUserManager().getTeamUser(parameter);
				}
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_CONFIRM_PLOT:
				mUserManager.getMyUser().getMyTeamManager().getMyChooseEppo().getTeamPlotManager().confirmPlot(parameter);
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_FINISH_PLOT:
				mUserManager.getMyUser().getMyTeamManager().getMyChooseEppo().getTeamPlotManager().finishPlot(parameter);
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_GET_DRONE_OF_EPPO:
				if (parameter.containsKey("eppo")) {
					Eppo eppo = (Eppo) parameter.getSerializable("eppo");
					eppo.getTeamDroneManager().getTeamDrone(parameter);
				}
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_SHARE_LAND:
				mUserManager.getMyUser().getMyLandManager().shareLand(parameter);
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_CHOOSE_EPPO:
				if (parameter.containsKey("eppo")) {
					Eppo eppo = (Eppo) parameter.get("eppo");
					mUserManager.getMyUser().getMyTeamManager().saveChooseEppo(eppo);
				}
				break;
			case UserManagerUtils.UserManagerMethods.METHOD_LOAD_EPPO:
				mUserManager.getMyUser().loadLoginStatus();
				break;
		}
		return new ComponentIntent("LOCAL", "Result", bundle);
	}


	@Override
	public Bundle provideComponentAttribute(String attrName) {
		Bundle bundle = new Bundle();
		switch (attrName) {
			case UserManagerUtils.UserManagerAttributes.ATTRIBUTE_ISLOGIN:
				bundle.putBoolean("isLogin", mUserManager.getMyUser().isLogin());
				break;
		}
		return bundle;
	}

	@Override
	public Object provideComponentAttributeInner(String s) {
		return null;
	}

	public IMessageReceiver messageReceiver = new IMessageReceiver() {
		@Override
		public void OnComponentMessageReceive(ComponentIntent intent) {
			if (intent.getData() == null)
				return;
		}
	};


}
