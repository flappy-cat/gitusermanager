package app.tech.efy.usermanagerutils;

/**
 * Created by Administrator on 2017/6/7.
 */
public class UserManagerUtils {

	public static final String Version = "V1.0.1";

	public static class UserManagerAttributes {
		public static final String ATTRIBUTE_ISLOGIN = Utils.PACKAGE_NAME + Version + ".attribute.ATTRIBUTE_ISLOGIN";

	}

	public static class UserManagerEvents {
	}

	public static class UserManagerMethods {
		public static final String METHOD_LOGIN = Utils.PACKAGE_NAME + Version + ".method.METHOD_LOGIN";
		public static final String METHOD_GET_VERTIFY = Utils.PACKAGE_NAME + Version + ".method.METHOD_GET_VERTIFY";
		public static final String METHOD_VERTIFY = Utils.PACKAGE_NAME + Version + ".method.METHOD_VERTIFY";
		public static final String METHOD_REGIST = Utils.PACKAGE_NAME + Version + ".method.METHOD_REGIST";
		public static final String METHOD_GET_VERTIFY_FORGET_PWD = Utils.PACKAGE_NAME + Version + ".method.METHOD_GET_VERTIFY_FORGET_PWD";
		public static final String METHOD_VERTIFY_FORGET_PWD = Utils.PACKAGE_NAME + Version + ".method.METHOD_VERTIFY_FORGET_PWD";
		public static final String METHOD_UPDATE_PWD = Utils.PACKAGE_NAME + Version + ".method.METHOD_UPDATE_PWD";
		public static final String METHOD_GET_ISLOGIN = Utils.PACKAGE_NAME + Version + ".method.METHOD_GET_ISLOGIN";


		public static final String METHOD_GET_NEAR_CLOUD_LAND = Utils.PACKAGE_NAME + Version + ".method.METHOD_GET_NEAR_CLOUD_LAND";
		public static final String METHOD_GET_MY_CLOUD_LAND = Utils.PACKAGE_NAME + Version + ".method.METHOD_GET_MY_CLOUD_LAND";
		public static final String METHOD_GET_NEAR_DRONE = Utils.PACKAGE_NAME + Version + ".method.METHOD_GET_NEAR_DRONE";
		public static final String METHOD_UPDATE_LAND = Utils.PACKAGE_NAME + Version + ".method.METHOD_UPDATE_LAND";
		public static final String METHOD_EDIT_LAND = Utils.PACKAGE_NAME + Version + ".method.METHOD_EDIT_LAND";
		public static final String METHOD_DELETE_LAND = Utils.PACKAGE_NAME + Version + ".method.METHOD_DELETE_LAND";

		public static final String METHOD_UPLOAD_PLOT = Utils.PACKAGE_NAME + Version + ".method.METHOD_UPLOAD_PLOT";

		public static final String METHOD_EDIT_PLOT = Utils.PACKAGE_NAME + Version + ".method.METHOD_EDIT_PLOT";
		public static final String METHOD_CANCEL_PLOT = Utils.PACKAGE_NAME + Version + ".method.METHOD_CANCEL_PLOT";
		public static final String METHOD_GET_NEAR_OBSERVE_DRONE = Utils.PACKAGE_NAME + Version + ".method.METHOD_GET_NEAR_OBSERVE_DRONE";
		public static final String METHOD_SEARCH_NEAR_OBSERVE_DRONE = Utils.PACKAGE_NAME + Version + ".method.METHOD_SEARCH_NEAR_OBSERVE_DRONE";
		public static final String METHOD_UNBIND_DRONE = Utils.PACKAGE_NAME + Version + ".method.METHOD_UNBIND_DRONE";
		public static final String METHOD_BIND_DRONE = Utils.PACKAGE_NAME + Version + ".method.METHOD_BIND_DRONE";
		public static final String METHOD_LOGOUT = Utils.PACKAGE_NAME + Version + ".method.METHOD_LOGOUT";
		public static final String METHOD_ADD_EPPO = Utils.PACKAGE_NAME + Version + ".method.METHOD_ADD_EPPO";


		public static final String METHOD_GET_EPPO = Utils.PACKAGE_NAME + Version + ".method.METHOD_GET_EPPO";
		public static final String METHOD_SEARCH_USER = Utils.PACKAGE_NAME + Version + ".method.METHOD_SEARCH_USER";
		public static final String METHOD_SEARCH_DRONE = Utils.PACKAGE_NAME + Version + ".method.METHOD_SEARCH_DRONE";
		public static final String METHOD_ADD_MEMBER_TO_EPPO = Utils.PACKAGE_NAME + Version + ".method.METHOD_ADD_MEMBER_TO_EPPO";
		public static final String METHOD_DELETE_MEMBER_TO_EPPO = Utils.PACKAGE_NAME + Version + ".method.METHOD_DELETE_MEMBER_TO_EPPO";
		public static final String METHOD_ADD_DRONE_TO_EPPO = Utils.PACKAGE_NAME + Version + ".method.METHOD_ADD_DRONE_TO_EPPO";

		public static final String METHOD_DELETE_DRONE_TO_EPPO = Utils.PACKAGE_NAME + Version + ".method.METHOD_DELETE_DRONE_TO_EPPO";
		public static final String METHOD_EDIT_DRONE_NAME = Utils.PACKAGE_NAME + Version + ".method.METHOD_EDIT_DRONE_NAME";
		public static final String METHOD_GET_CLOUD_PLOT = Utils.PACKAGE_NAME + Version + ".method.METHOD_GET_CLOUD_PLOT";
		public static final String METHOD_GET_MEMBER_OF_EPPO = Utils.PACKAGE_NAME + Version + ".method.METHOD_GET_MEMBER_OF_EPPO";
		public static final String METHOD_CONFIRM_PLOT = Utils.PACKAGE_NAME + Version + ".method.METHOD_CONFIRM_PLOT";
		public static final String METHOD_FINISH_PLOT = Utils.PACKAGE_NAME + Version + ".method.METHOD_FINISH_PLOT";

		public static final String METHOD_GET_DRONE_OF_EPPO = Utils.PACKAGE_NAME + Version + ".method.METHOD_GET_DRONE_OF_EPPO";
		public static final String METHOD_SHARE_LAND = Utils.PACKAGE_NAME + Version + ".method.METHOD_SHARE_LAND";
		public static final String METHOD_CHOOSE_EPPO = Utils.PACKAGE_NAME + Version + ".method.METHOD_CHOOSE_EPPO";
		public static final String METHOD_LOAD_EPPO = Utils.PACKAGE_NAME + Version + ".method.METHOD_LOAD_EPPO";

	}

	public static class ErrorType {
		public static final String DEFAULT = Utils.PACKAGE_NAME + Version + ".error.DEFAULT";
		public static final String USER_NO_EXIST = Utils.PACKAGE_NAME + Version + ".error.USER_NO_EXIST";
		public static final String PWD_ERROR = Utils.PACKAGE_NAME + Version + ".error.PWD_ERROR";

		public static final String PHONE_ISREGIST = Utils.PACKAGE_NAME + Version + ".error.PHONE_ISREGIST";
		public static final String VERTIFYCODE_ERROR = Utils.PACKAGE_NAME + Version + ".error.VERTIFYCODE_ERROR";
		public static final String USN_REPEAT = Utils.PACKAGE_NAME + Version + ".error.USN_REPEAT";
		public static final String PHONE_NOREGIST = Utils.PACKAGE_NAME + Version + ".error.PHONE_NOREGIST";

		public static final String ALREADY_ADD_MEMBER = Utils.PACKAGE_NAME + Version + ".error.ALREADY_ADD_MEMBER";
		public static final String MORE_THEN_TEN = Utils.PACKAGE_NAME + Version + ".error.MORE_THEN_TEN";

		public static final String ALREADY_ADD_DRONE = Utils.PACKAGE_NAME + Version + ".error.ALREADY_ADD_DRONE";
		public static final String ALREADY_ADD_DRONE_BY_OTHERS = Utils.PACKAGE_NAME + Version + ".error.ALREADY_ADD_DRONE_BY_OTHERS";


		public static final String ON_CHOOSE_EPPO = Utils.PACKAGE_NAME + Version + ".error.ON_CHOOSE_EPPO";

		public static final String EPPO_NAME_EXIST = Utils.PACKAGE_NAME + Version + ".error.EPPO_NAME_EXIST";


	}
}
