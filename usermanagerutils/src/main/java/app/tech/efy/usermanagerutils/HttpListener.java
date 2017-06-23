package app.tech.efy.usermanagerutils;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/12.
 */
public interface HttpListener extends Serializable {
	void onBefore();

	void onSuccess(Object data);

	void onAfter();

	void onFailed(String errorType);
}
