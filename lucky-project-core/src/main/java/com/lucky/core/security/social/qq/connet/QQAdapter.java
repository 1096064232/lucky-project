/**
 * 
 */
package com.lucky.core.security.social.qq.connet;

import com.lucky.core.security.social.qq.api.QQ;
import com.lucky.core.security.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * QQApi适配器
 * 
 * @author lucky ouyang
 *
 * @version 1.0
 */
public class QQAdapter implements ApiAdapter<QQ> {

	/**
	 * 测试qq的服务是否可用
	 */
	@Override
	public boolean test(QQ api) {
		return true;
	}

	
	/**
	 * Connection和qqApi的数据字段做适配
	 */
	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
		QQUserInfo userInfo = api.getUserInfo();

		values.setDisplayName(userInfo.getNickname()); //用户名称（昵称）
		values.setImageUrl(userInfo.getFigureurl_qq_1()); // 用户头像
		values.setProfileUrl(null); //个人主页
		values.setProviderUserId(userInfo.getOpenId());  // 用户openid
	}

	@Override
	public UserProfile fetchUserProfile(QQ api) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 个人主页消息更新，
	 */
	@Override
	public void updateStatus(QQ api, String message) {
		// do noting
	}

}
