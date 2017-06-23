package app.tech.efy.usermanagercomponent.teamplotmanager;

import android.os.Bundle;

/**
 * Created by Administrator on 2017/6/17.
 */
public interface TeamPlotImpl {
	void getTeamPlot(Bundle bundle);

	void addTeamPlot(Bundle bundle);

	void editTeamPlot(Bundle bundle);

	void cancelPlot(Bundle bundle);

	void confirmPlot(Bundle bundle);

	void finishPlot(Bundle bundle);

}
