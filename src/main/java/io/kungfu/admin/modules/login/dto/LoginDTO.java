package io.kungfu.admin.modules.login.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "LoginDTO", description = "登录DOT")
public class LoginDTO {
    @ApiModelProperty(value = "用户编码", required = true)
    private String userCode;
    @ApiModelProperty(value = "用户密码", required = true)
    private String password;
    @ApiModelProperty(value = "验证码")
    private String verfiyCode;
    @ApiModelProperty(value = "跳转地址")
    private String skipPath;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerfiyCode() {
        return verfiyCode;
    }

    public void setVerfiyCode(String verfiyCode) {
        this.verfiyCode = verfiyCode;
    }

    public String getSkipPath() {
        return skipPath;
    }

    public void setSkipPath(String skipPath) {
        this.skipPath = skipPath;
    }
}
