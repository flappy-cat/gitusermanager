package app.tech.efy.usermanagercomponent.teammanager;

import android.os.Bundle;

import java.util.List;

import app.tech.efy.usermanagercomponent.bean.Eppo;

/**
 * Created by Administrator on 2017/6/13.
 */
public interface MyTeamImpl {

	void addTeam(Bundle parameter);

	void queryTeam(Bundle parameter);

	void setTeams(String teamIdVsRoleId);

	List<Eppo> getMyTeams();

	Eppo getMyChooseEppo();

	void saveChooseEppo(Eppo eppo);

	void loadTeams();
}
