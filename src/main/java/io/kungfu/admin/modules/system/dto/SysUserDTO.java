package io.kungfu.admin.modules.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel("系统用户表DTO")
public class SysUserDTO {
    @ApiModelProperty(value = "主键", example = "100", position= 1, required = true)
    private Long id;
    @ApiModelProperty(value = "用户编号默认手机号", example = "示例值", position= 2, required = true)
    private String userCode;
    @ApiModelProperty(value = "用户名称", example = "示例值", position= 3, required = true)
    private String userName;
    @ApiModelProperty(value = "密码", example = "示例值", position= 4, required = true)
    private String password;
    @ApiModelProperty(value = "部门编号", example = "示例值", position= 5, required = true)
    private String orgCode;
    @ApiModelProperty(value = "工号", example = "示例值", position= 6, required = true)
    private String jobNo;
    @ApiModelProperty(value = "职务 字典", example = "示例值", position= 7, required = true)
    private String post;
    @ApiModelProperty(value = "性别 字典", example = "1", position= 8, required = true)
    private Integer sex;
    @ApiModelProperty(value = "手机号码", example = "示例值", position= 9, required = true)
    private String mobile;
    @ApiModelProperty(value = "EMAIL", example = "示例值", position= 10, required = true)
    private String email;
    @ApiModelProperty(value = "家庭地址", example = "示例值", position= 11, required = true)
    private String address;
    @ApiModelProperty(value = "登录错误次数", example = "1", position= 12, required = true)
    private Integer failNum;
    @ApiModelProperty(value = "允许登录", example = "1", position= 13, required = true)
    private Boolean allowLogin;

    public Long getId() {
      return id;
    }
    public void setId(Long id) {
      this.id = id;
    }

    public String getUserCode() {
      return userCode;
    }
    public void setUserCode(String userCode) {
      this.userCode = userCode;
    }

    public String getUserName() {
      return userName;
    }
    public void setUserName(String userName) {
      this.userName = userName;
    }

    public String getPassword() {
      return password;
    }
    public void setPassword(String password) {
      this.password = password;
    }

    public String getOrgCode() {
      return orgCode;
    }
    public void setOrgCode(String orgCode) {
      this.orgCode = orgCode;
    }

    public String getJobNo() {
      return jobNo;
    }
    public void setJobNo(String jobNo) {
      this.jobNo = jobNo;
    }

    public String getPost() {
      return post;
    }
    public void setPost(String post) {
      this.post = post;
    }

    public Integer getSex() {
      return sex;
    }
    public void setSex(Integer sex) {
      this.sex = sex;
    }

    public String getMobile() {
      return mobile;
    }
    public void setMobile(String mobile) {
      this.mobile = mobile;
    }

    public String getEmail() {
      return email;
    }
    public void setEmail(String email) {
      this.email = email;
    }

    public String getAddress() {
      return address;
    }
    public void setAddress(String address) {
      this.address = address;
    }

    public Integer getFailNum() {
      return failNum;
    }
    public void setFailNum(Integer failNum) {
      this.failNum = failNum;
    }

    public Boolean getAllowLogin() {
      return allowLogin;
    }
    public void setAllowLogin(Boolean allowLogin) {
      this.allowLogin = allowLogin;
    }
}
