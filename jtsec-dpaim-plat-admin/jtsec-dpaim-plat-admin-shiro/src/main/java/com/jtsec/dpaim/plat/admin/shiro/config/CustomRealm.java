package com.jtsec.dpaim.plat.admin.shiro.config;

import com.jtsec.common.util.datahandle.DataConvert;
import com.jtsec.manager.pojo.model.User;
import com.jtsec.manager.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author NovLi
 * @Title: what
 * @ProjectName manager-parent
 * @Description: TODO
 * @date 2018/7/1010:41
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {


	@Autowired
	private IUserService iUserService;

	/**
	 * @param
	 * @return
	 * @throws
	 * @Description: 授权
	 * @author NovLi
	 * @date 2018/7/10 10:49
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo (PrincipalCollection principals) {
		SimpleAccount simpleAccount = new SimpleAccount ();
		return simpleAccount;
	}

	/**
	 * @param token
	 * @throws
	 * @Description: 登陆
	 * @author NovLi
	 * @date 2018/7/10 10:51
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo (AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken user = (UsernamePasswordToken) token;
		Map<String, Object> map = DataConvert.ConvertDataToMapWithoutException (user);
		User users = iUserService.queryUserbyVarible (map);
		return new SimpleAuthenticationInfo (users, users.getPassword (), getName ());
	}
}
