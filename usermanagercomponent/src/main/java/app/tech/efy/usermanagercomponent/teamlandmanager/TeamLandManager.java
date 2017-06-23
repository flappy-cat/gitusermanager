package app.tech.efy.usermanagercomponent.teamlandmanager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import app.tech.efy.componentbus.component.Component;
import app.tech.efy.componentbus.componentBus.routeManagement.RouteType;
import app.tech.efy.componentbus.messageReceiver.ComponentIntent;
import app.tech.efy.httptranscomponentutils.HttpTrans;
import app.tech.efy.httptranscomponentutils.bean.JsonData;
import app.tech.efy.httptranscomponentutils.callback.LandListCallBack;
import app.tech.efy.httptranscomponentutils.listener.LandListListener;
import app.tech.efy.usermanagercomponent.TransUtils;
import app.tech.efy.usermanagercomponent.bean.Eppo;
import app.tech.efy.usermanagercomponent.bean.Land;
import app.tech.efy.usermanagercomponent.bean.LatLong;
import app.tech.efy.usermanagerutils.HttpListener;
import app.tech.efy.usermanagerutils.UserManagerUtils;

/**
 * Created by Administrator on 2017/6/16.
 */
public class TeamLandManager implements TeamLandImpl {
	private List<Land> mLandList;
	private Eppo mEppo;
	private Component mComponent;

	public TeamLandManager(Component component, Eppo eppo) {
		this.mComponent = component;
		this.mEppo = eppo;
	}

	@Override
	public void getTeamLand(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("latlong")) {
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			LatLong latlong = (LatLong) parameter.getSerializable("latlong");
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
				public void onResponse(List<app.tech.efy.httptranscomponentutils.bean.Land> data) {
					List<Land> newList = new ArrayList<>();
					for (app.tech.efy.httptranscomponentutils.bean.Land land : data) {
						newList.add(TransUtils.getLogicLand(land));
					}
					setTeamLand(newList);
					mListener.onSuccess(mLandList);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_GET_NEAR_CLOUD_LAND);
			parameter.putSerializable("callback", callBack);
			parameter.putString("id", mEppo.getId());
			parameter.putDouble("latitude", latlong.getLatitude());
			parameter.putDouble("longitude", latlong.getLongitude());
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	public void setTeamLand(List<Land> list) {
		if (mLandList == null) {
			mLandList = new ArrayList<>();
		}
		this.mLandList.clear();
		for (Land land : list) {
			this.mLandList.add(land);
		}
	}

}
