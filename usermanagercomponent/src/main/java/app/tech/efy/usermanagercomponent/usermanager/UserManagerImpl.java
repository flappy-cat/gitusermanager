package app.tech.efy.usermanagercomponent.usermanager;

import android.os.Bundle;

/**
 * Created by Administrator on 2017/6/7.
 */
public interface UserManagerImpl {

	void login(Bundle params);

	void getVertify(Bundle params);

	void vertify(Bundle params);

	void getVertifyForForgetPwd(Bundle params);

	void vertifyForForgetPwd(Bundle params);

	void regist(Bundle params);

	void resetPwd(Bundle params);

	void logout(Bundle params);

	UserImpl getMyUser();

	boolean isLogin();

}
