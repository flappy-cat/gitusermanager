package app.tech.efy.usermanagercomponent.usermanager;

import android.os.Bundle;

import app.tech.efy.httptranscomponentutils.bean.User;
import app.tech.efy.usermanagercomponent.mylandmanager.MyLandImpl;
import app.tech.efy.usermanagercomponent.teammanager.MyTeamImpl;

/**
 * Created by Administrator on 2017/6/13.
 */
public interface UserImpl {

	void loginSuccess(User user);

	void logout();

	boolean isLogin();

	void getControlDrone(Bundle params);

	void getObserveDrone(Bundle params);

	void searchObserveDrone(Bundle params);

	void bindDrone(Bundle params);

	void unbindDrone(Bundle params);

	void loadLoginStatus();

	MyLandImpl getMyLandManager();

	MyTeamImpl getMyTeamManager();

}
