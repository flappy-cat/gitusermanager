package app.tech.efy.usermanagercomponent.bean;


import java.io.Serializable;

import app.tech.efy.httptranscomponentutils.bean.Land;

/**
 * Created by Administrator on 2017/6/13.
 */
public class Plot implements Serializable{
	private String id;
	private String taskName;
	private Land landInfo;
	private String crops;
	private String pesticide;
	private double serviceUnitCharge;
	private double flightSpeed;
	private String coverScale;//重喷覆盖比例
	private String flow;//流量
	private String length;
	private double flightHeight;//默认10米
	private double safeDistance;
	private User creator;//创建者
	private User flyerId;//飞手id
	private Eppo zhiBaoTeam;
	private Drone drone;
	private String workArea;
	private String taskStatus;
	private String lineType;
	private int lineLength;
	private float flyTime;
	private boolean isFaceLand;
}
