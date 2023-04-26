package io.kungfu.admin.modules.login.controller;

import com.alibaba.fastjson.JSON;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
import com.jfinal.core.Path;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.Kv;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheKit;
import com.lastb7.swagger.annotation.ApiResCustom;
import com.lastb7.swagger.enumeration.ApiEnum;
import io.kungfu.admin.common.Constant;
import io.kungfu.admin.modules.login.dto.LoginDTO;
import io.kungfu.admin.modules.login.service.LoginService;
import io.kungfu.admin.modules.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.kungfu.core.KungfuController;
import org.kungfu.core.R;
import org.kungfu.core.ResultVO;

import javax.security.auth.login.LoginException;
import java.util.List;

@Api(tags = "账户登录")
@Path("/user-login")
public class LoginController extends KungfuController {

    @Inject
    private LoginService loginService;

    @Inject
    private SysMenuService sysMenuService;

    @ApiOperation(value = "获取验证码", notes = "获取后台产生的随机验证码")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userCode", value = "用户编码", dataType = "String")
    })
    
    public void getVerfiyCode() {
        String userCode = get("userCode");

        if (StrKit.isBlank(userCode)) {
            renderJson(R.fail("请先输入用户名"));
        }

        renderJson(R.ok("verfiyCode", loginService.getVerfiyCode(userCode)));
    }

    @ApiOperation(value = "账户登录", notes = "根据用户Code及密码登录，三次密码验证错误，显示验证码，并传输验证码到后端。", httpMethod = ApiEnum.METHOD_POST, produces = ApiEnum.PRODUCES_JSON)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "loginDTO", value = "短信发送信息", dataTypeClass = LoginDTO.class,  paramType = ApiEnum.PARAM_TYPE_BODY)
    })
    @ApiResCustom(ResultVO.class)
    @Clear
    public void login() throws LoginException {
        String jsonRequest = HttpKit.readData(getRequest());
        LoginDTO loginDto = JSON.parseObject(jsonRequest, LoginDTO.class);

        R ret = loginService.doLogin(loginDto.getUserCode(), loginDto.getPassword(), loginDto.getVerfiyCode());

        if (ret.isFail()) {
            renderJson(ret);
            return;
        }

        // 缓存
        List<Record> userMenuTree = CacheKit.get("sysMenu", "userMenuTree"+ loginDto.getUserCode(), () -> sysMenuService.getUserMenuTree(loginDto.getUserCode(), "root"));

        Kv kv = Kv.by("user", ret.get("user"))
                .set("token", ret.get("token"))
                .set("expireDate", Constant.EXPIRE_DATE)
                .set("userMenuTree", userMenuTree)
                .set("skipPath", loginDto.getSkipPath());

        ret.remove("user");
        ret.remove("token");

        renderJson(ret.set("data", kv));
    }

}
