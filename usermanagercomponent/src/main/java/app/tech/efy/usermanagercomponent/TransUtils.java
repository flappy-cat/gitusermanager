package app.tech.efy.usermanagercomponent;

import com.google.gson.Gson;

import app.tech.efy.usermanagercomponent.bean.Drone;
import app.tech.efy.usermanagercomponent.bean.Eppo;
import app.tech.efy.usermanagercomponent.bean.Land;
import app.tech.efy.usermanagercomponent.bean.Plot;
import app.tech.efy.usermanagercomponent.bean.User;

/**
 * Created by Administrator on 2017/6/13.
 */
public class TransUtils {
	public static User getLogicUser(app.tech.efy.httptranscomponentutils.bean.User oldData) {
		String jsonData = new Gson().toJson(oldData);
		User newData = new Gson().fromJson(jsonData, User.class);
		return newData;
	}

	public static app.tech.efy.httptranscomponentutils.bean.User getHttpUser(User oldData) {
		String jsonData = new Gson().toJson(oldData);
		app.tech.efy.httptranscomponentutils.bean.User newData = new Gson().fromJson(jsonData, app.tech.efy.httptranscomponentutils.bean.User.class);
		return newData;
	}

	public static Land getLogicLand(app.tech.efy.httptranscomponentutils.bean.Land oldData) {
		String jsonData = new Gson().toJson(oldData);
		Land newData = new Gson().fromJson(jsonData, Land.class);
		return newData;
	}

	public static app.tech.efy.httptranscomponentutils.bean.Land getHttpLand(Land oldData) {
		String jsonData = new Gson().toJson(oldData);
		app.tech.efy.httptranscomponentutils.bean.Land newData = new Gson().fromJson(jsonData, app.tech.efy.httptranscomponentutils.bean.Land.class);

		newData.setZhiBaoTeamIds(oldData.generateList());
		return newData;
	}


	public static Eppo getLogicEppo(app.tech.efy.httptranscomponentutils.bean.Eppo oldData) {
		String jsonData = new Gson().toJson(oldData);
		Eppo newData = new Gson().fromJson(jsonData, Eppo.class);
		return newData;
	}

	public static app.tech.efy.httptranscomponentutils.bean.Eppo getHttpEppo(Eppo oldData) {
		String jsonData = new Gson().toJson(oldData);
		app.tech.efy.httptranscomponentutils.bean.Eppo newData = new Gson().fromJson(jsonData, app.tech.efy.httptranscomponentutils.bean.Eppo.class);
		return newData;
	}

	public static Drone getLogicDrone(app.tech.efy.httptranscomponentutils.bean.Drone oldData) {
		String jsonData = new Gson().toJson(oldData);
		Drone newData = new Gson().fromJson(jsonData, Drone.class);
		return newData;
	}

	public static app.tech.efy.httptranscomponentutils.bean.Drone getHttpDrone(Drone oldData) {
		String jsonData = new Gson().toJson(oldData);
		app.tech.efy.httptranscomponentutils.bean.Drone newData = new Gson().fromJson(jsonData, app.tech.efy.httptranscomponentutils.bean.Drone.class);
		return newData;
	}

	public static Plot getLogicPlot(app.tech.efy.httptranscomponentutils.bean.Plot oldData) {
		String jsonData = new Gson().toJson(oldData);
		Plot newData = new Gson().fromJson(jsonData, Plot.class);
		return newData;
	}

	public static app.tech.efy.httptranscomponentutils.bean.Plot getHttpPlot(Plot oldData) {
		String jsonData = new Gson().toJson(oldData);
		app.tech.efy.httptranscomponentutils.bean.Plot newData = new Gson().fromJson(jsonData, app.tech.efy.httptranscomponentutils.bean.Plot.class);
		return newData;
	}

}

