package app.tech.efy.usermanagercomponent.teamdronemanager;

import android.os.Bundle;

/**
 * Created by Administrator on 2017/6/17.
 */
public interface TeamDroneImpl {

	void getTeamDrone(Bundle bundle);

	void queryDrone(Bundle bundle);

	void addDroneByUsb(Bundle bundle);

	void addDroneByNormal(Bundle bundle);

	void deleteDrone(Bundle bundle);

	void editDroneName(Bundle bundle);

}
