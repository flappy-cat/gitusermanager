package app.tech.efy.usermanagercomponent.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/13.
 */
public class UavType implements Serializable {
	private Long id;
	private String rotorType;//旋翼类型：直升机or多旋翼
	private String typeName;//机型型号 如MG-1
	private double spray;//喷幅

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRotorType() {
		return rotorType;
	}

	public void setRotorType(String rotorType) {
		this.rotorType = rotorType;
	}


	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public double getSpray() {
		return spray;
	}

	public void setSpray(double spray) {
		this.spray = spray;
	}

}
