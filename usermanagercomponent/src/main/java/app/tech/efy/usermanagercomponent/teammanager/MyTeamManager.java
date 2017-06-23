package app.tech.efy.usermanagercomponent.teammanager;

import android.os.Bundle;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import app.tech.efy.componentbus.component.Component;
import app.tech.efy.componentbus.componentBus.routeManagement.RouteType;
import app.tech.efy.componentbus.messageReceiver.ComponentIntent;
import app.tech.efy.datamanagerutil.DataManagerUtils;
import app.tech.efy.httptranscomponentutils.HttpTrans;
import app.tech.efy.httptranscomponentutils.bean.JsonData;
import app.tech.efy.httptranscomponentutils.callback.EppoListCallBack;
import app.tech.efy.httptranscomponentutils.listener.EppoListListener;
import app.tech.efy.usermanagercomponent.TransUtils;
import app.tech.efy.usermanagercomponent.bean.Eppo;
import app.tech.efy.usermanagercomponent.bean.User;
import app.tech.efy.usermanagerutils.HttpListener;
import app.tech.efy.usermanagerutils.UserManagerUtils;

/**
 * Created by Administrator on 2017/6/13.
 */
public class MyTeamManager implements MyTeamImpl {
	private List<Eppo> list;
	private Eppo chooseEppo;
	private User mUser;
	private Component mComponent;

	public MyTeamManager(Component component, User user) {
		list = new ArrayList<>();
		this.mComponent = component;
		this.mUser = user;
	}

	@Override
	public void addTeam(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("eppo")) {
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			EppoListCallBack callBack = new EppoListCallBack(new EppoListListener() {
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
							case "teamName already exists":
								mListener.onFailed(UserManagerUtils.ErrorType.EPPO_NAME_EXIST);
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
				public void onResponse(List<app.tech.efy.httptranscomponentutils.bean.Eppo> eppo) {
					Eppo data = TransUtils.getLogicEppo(eppo.get(0));
					list.add(data);
					mListener.onSuccess(list);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_ADD_EPPO);
			parameter.putSerializable("callback", callBack);
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public void queryTeam(Bundle parameter) {
		if (parameter.containsKey("listener") && parameter.containsKey("content")) {
			String content = parameter.getString("content");
			final HttpListener mListener = (HttpListener) parameter.getSerializable("listener");
			EppoListCallBack callBack = new EppoListCallBack(new EppoListListener() {
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
							case "teamName already exists":
								mListener.onFailed(UserManagerUtils.ErrorType.EPPO_NAME_EXIST);
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
				public void onResponse(List<app.tech.efy.httptranscomponentutils.bean.Eppo> data) {
					List<Eppo> list = new ArrayList<>();
					for (app.tech.efy.httptranscomponentutils.bean.Eppo eppo : data) {
						list.add(TransUtils.getLogicEppo(eppo));
					}
					mListener.onSuccess(list);
				}

			});
			Bundle data = new Bundle();
			data.putString("componentName", "HttpTransComponent");
			data.putString("method", HttpTrans.ConnectMethods.METHOD_GET_EPPO);
			parameter.putSerializable("callback", callBack);
			parameter.putString("content", content);
			data.putBundle("parameter", parameter);
			ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
			mComponent.excuteActionSystrnicInner(intent);
		}
	}

	@Override
	public void setTeams(String teamIdsVsRoleIds) {
		list.clear();
		try {
			if (!TextUtils.isEmpty(teamIdsVsRoleIds)) {
				String[] splitString = teamIdsVsRoleIds.split(";");
				for (String s : splitString) {
					String[] singleLatLongAlt = s.split(",");
					if (singleLatLongAlt.length == 4) {
						Eppo eppo = new Eppo(mComponent);
						eppo.setId(singleLatLongAlt[0]);
						eppo.setTeamName(singleLatLongAlt[1]);
						eppo.setMyRoleId(singleLatLongAlt[2]);
						eppo.setMyRole(singleLatLongAlt[3]);
						list.add(eppo);
					}
				}
				if (list.size() == 0) {
					chooseEppo = null;
					saveChooseEppo(null);
				} else if (list.size() == 1) {
					chooseEppo = list.get(0);
					saveChooseEppo(list.get(0));
				}
			}
		} catch (Exception e) {
		}
	}

	public void saveChooseEppo(Eppo eppo) {
		String choose = Eppo.generateChooseEppo(eppo);
		Bundle data = new Bundle();
		data.putString("componentName", "DataManager");
		data.putString("method", DataManagerUtils.DataManagerMethods.METHOD_SET_CHOOSE_EPPO);
		Bundle parameter = new Bundle();
		parameter.putString("team", choose);
		data.putBundle("parameter", parameter);
		ComponentIntent intent = new ComponentIntent(RouteType.LOCAL, "excuteComponentAction", data);
		mComponent.excuteActionSystrnicInner(intent);
	}

	@Override
	public void loadTeams() {
		String eppoStr = loadEppoStr();
		if (list == null) {
			list = new ArrayList<>();
		}
		list.clear();
		list = Eppo.getMyEppo(mComponent, eppoStr);
		String chooseEppoStr = loadChooseEppoStr();
		List<Eppo> chooseList = Eppo.getMyEppo(mComponent, chooseEppoStr);
		Eppo chooseEppo = null;
		if (chooseList.size() == 1) {
			chooseEppo = chooseList.get(0);
			for (Eppo eppo : list) {
//			LOG.i("eppo=" + eppo.getId() + " " + eppo.getEpponame());
				if (String.valueOf(eppo.getId()).equals(String.valueOf(chooseEppo.getId()))) {
					if (eppo.getTeamName().equals(chooseEppo.getTeamName())) {
						this.chooseEppo = eppo;
						return;
					}
				}
			}
		}
		this.chooseEppo = null;
	}

	private String loadChooseEppoStr() {
		Bundle data = new Bundle();
		data.putString("componentName", "DataManager");
		data.putString("attribute", DataManagerUtils.DataManagerAttributes.ATTRIBUTE_CHOOSE_TEAM);
		ComponentIntent intent = new ComponentIntent(RouteType.DEFAULT, "excuteComponentAction", data);

		Bundle bundle = mComponent.getAttributeInner(intent);
		return bundle.getString("team");
	}

	@Override
	public List<Eppo> getMyTeams() {
		return list;
	}

	@Override
	public Eppo getMyChooseEppo() {
		return chooseEppo;
	}

	private String loadEppoStr() {
		Bundle data = new Bundle();
		data.putString("componentName", "DataManager");
		data.putString("attribute", DataManagerUtils.DataManagerAttributes.ATTRIBUTE_TEAM);
		ComponentIntent intent = new ComponentIntent(RouteType.DEFAULT, "excuteComponentAction", data);

		Bundle bundle = mComponent.getAttributeInner(intent);
		return bundle.getString("team");
	}
}
