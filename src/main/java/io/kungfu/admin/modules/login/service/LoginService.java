package io.kungfu.admin.modules.login.service;


import com.jfinal.aop.Inject;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.ehcache.CacheKit;
import io.kungfu.admin.modules.system.model.SysUser;
import io.kungfu.admin.modules.system.service.SysUserService;
import io.kungfu.admin.util.Md5Kit;
import org.apache.log4j.Logger;
import org.kungfu.core.R;
import org.kungfu.util.KungfuKit;
import org.kungfu.util.TokenKit;

import javax.security.auth.login.LoginException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class LoginService {

	private static final Logger log = Logger.getLogger(LoginService.class);

	@Inject
    SysUserService sysUserService;

	public String getVerfiyCode(String userCode) {
		String verifyCode = "";

		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			verifyCode += rand;
		}

		CacheKit.put("verifyCode", userCode, verifyCode);

		return verifyCode;
	}


	public R doLogin(String userCode, String password, String verfiyCode) throws LoginException {
		//验证数据
		if ((userCode == null) || (userCode.trim().length() == 0) || (password == null) || (password.trim().length() == 0)) {
			return R.fail(631, "请输入用户名和密码");
		}
		//验证用户
		SysUser user = sysUserService.findByUserCodeOrUserName(userCode);
		if (user == null) {
			return R.fail(630, "用户不存在");
		}		
		//验证权限
		if ((user.getAllowLogin() != null) && (!user.getAllowLogin())) {
			return R.fail(620, "没有登录权限，请联系管理员");
		}		
		// 校验验证码
		if (StrKit.notBlank(verfiyCode)) {
			String newVerifyCode = checkVerifyCode(user, verfiyCode);

			if (StrKit.notBlank(newVerifyCode)) {
				return R.fail(630, "验证码不正确，请重新输入").set("newVerifyCode", newVerifyCode);
			}
		}

		// 验证密码
		if (user.getPassword().equals(Md5Kit.md5(password))) {

			String token = TokenKit.token(userCode, password);

			user.setLastLoginTime(new Date());
			user.update();

			log.info("login success.");

			user.remove("create_user_id", "create_user","create_time", "update_user","update_user_id", "update_time","password", "allow_login_time", "fail_num", "last_login_time");

			return R.ok("user", KungfuKit.toHump(user.toJson())).set("token", token);
		}


		return login3Fail(user);
	}


	private String checkVerifyCode(SysUser user, String verifyCode)  {
		Date allowLogTime = user.getAllowLoginTime();
		if (allowLogTime != null) {
			Date nowDate = new Date();
			Calendar cal = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal.setTime(allowLogTime);
			cal2.setTime(nowDate);
			float f = cal.getTimeInMillis() - cal2.getTimeInMillis();
			// 验证码验证
			if (f > 0) {
				user.setFailNum(0);
				user.update();

				String code = CacheKit.get("verifyCode", user.getUserCode());

				// 更换验证码！防止暴力破解
				String newVerifyCode = "";

				if ((verifyCode == null) || !verifyCode.equalsIgnoreCase(code)) {

					newVerifyCode = getVerfiyCode(user.getUserCode());
				}

				return newVerifyCode;
			}
		}

		return null;
	}

	private R login3Fail(SysUser user) throws LoginException {
		int failureNumber = user.getFailNum() == null ? 0 : user.getFailNum();
		user.setFailNum(failureNumber + 1);
		String msg = "帐号或密码错误";
		// 错误超过3次
		if (failureNumber > 3) {
			Date now = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);
			// 密码错误三次后要验证码
			cal.add(Calendar.DATE, 1);
			user.setAllowLoginTime(cal.getTime());
			msg = "密码错误3次,请输入验证码！";

			return R.fail(msg).set("displayVerfiyCode", 1);
		}

		user.update();

		return R.fail(msg).set("displayVerfiyCode", 0);
	}

}
