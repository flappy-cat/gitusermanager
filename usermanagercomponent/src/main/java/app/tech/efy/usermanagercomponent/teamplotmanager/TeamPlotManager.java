package app.tech.efy.usermanagercomponent.teamplotmanager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import app.tech.efy.componentbus.component.Component;
import app.tech.efy.componentbus.componentBus.routeManagement.RouteType;
import app.tech.efy.componentbus.messageReceiver.ComponentIntent;
import app.tech.efy.httptranscomponentutils.HttpTrans;
import app.tech.efy.httptranscomponentutils.bean.JsonData;
import app.tech.efy.httptranscomponentutils.callback.JsonDataCallBack;
import app.tech.efy.httptranscomponentutils.callback.PlotCallBack;
import app.tech.efy.httptranscomponentutils.callback.PlotListCallBack;
import app.tech.efy.httptranscomponentutils.listener.JsonDataListener;
import app.tech.efy.httptranscomponentutils.listener.PlotListListener;
import app.tech.efy.httptranscomponentutils.listener.PlotListener;
import app.tech.efy.usermanagercomponent.TransUtils;
import app.tech.efy.usermanagercomponent.bean.Eppo;
import app.tech.efy.usermanagercomponent.bean.Plot;
import app.tech.efy.usermanagerutils.HttpListener;
import app.tech.efy.usermanagerutils.UserManagerUtils;

/**
 * Created by Administrator on 2017/6/17.
 */
public class TeamPlotManager implements TeamPlotImpl {
	private List<Plot> mPlotList;
	private Eppo mEppo;
	private Component mComponent;

	public TeamPlotManager(Component component, Eppo eppo) {
		this.mComponent = component;
		this.mEppo = eppo;
	}

	@Override
	public void getTeamPlot(Bundle parameter) {
		if (parameter.containsKey("listener")) {
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			PlotListCallBack callBack = new PlotListCallBack(new PlotListListener() {
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
				public void onResponse(List<app.tech.efy.httptranscomponentutils.bean.Plot> data) {
					List<Plot> newList = new ArrayList<>();
					for (app.tech.efy.httptranscomponentutils.bean.Plot plot : data) {
						newList.add(TransUtils.getLogicPlot(plot));
					}
					setTeamPlot(newList);
					mListener.onSuccess(newList);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_GET_CLOUD_PLOT);
			parameter.putSerializable("callback", callBack);
			parameter.putString("eppoId", mEppo.getId());
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public void addTeamPlot(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("plot")) {
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			Plot plot = (Plot) parameter.getSerializable("plot");
			PlotCallBack callBack = new PlotCallBack(new PlotListener() {
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
				public void onResponse(app.tech.efy.httptranscomponentutils.bean.Plot data) {
					Plot plot = TransUtils.getLogicPlot(data);
					addTeamPlot(plot);
					mListener.onSuccess(plot);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_UPDATE_PLOT);
			parameter.putSerializable("callback", callBack);
			parameter.putSerializable("plot", plot);
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}


	@Override
	public void editTeamPlot(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("plot")) {
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			final Plot plot = (Plot) parameter.getSerializable("plot");
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
					removePlot(plot);
					mListener.onSuccess(data);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_EDIT_PLOT);
			parameter.putSerializable("callback", callBack);
			parameter.putSerializable("plot", plot);
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public void cancelPlot(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("plot")) {
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			final Plot plot = (Plot) parameter.getSerializable("plot");
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
					addTeamPlot(plot);
					mListener.onSuccess(data);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_CANCEL_PLOT);
			parameter.putSerializable("callback", callBack);
			parameter.putSerializable("plot", plot);
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public void confirmPlot(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("plot")) {
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			final Plot plot = (Plot) parameter.getSerializable("plot");
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
					removePlot(plot);
					mListener.onSuccess(data);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_CONFIRM_PLOT);
			parameter.putSerializable("callback", callBack);
			parameter.putSerializable("plot", plot);
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}


	@Override
	public void finishPlot(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("plot")) {
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			final Plot plot = (Plot) parameter.getSerializable("plot");
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
					removePlot(plot);
					mListener.onSuccess(data);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_FINISH_PLOT);
			parameter.putSerializable("callback", callBack);
			parameter.putSerializable("plot", plot);
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	public void setTeamPlot(List<Plot> list) {
		if (mPlotList == null) {
			mPlotList = new ArrayList<>();
		}
		this.mPlotList.clear();
		for (Plot plot : list) {
			this.mPlotList.add(plot);
		}
	}

	private void addTeamPlot(Plot plot) {
		if (mPlotList == null) {
			mPlotList = new ArrayList<>();
		}
		mPlotList.add(plot);
	}

	private void removePlot(Plot plot) {
		mPlotList.remove(plot);
	}
}
