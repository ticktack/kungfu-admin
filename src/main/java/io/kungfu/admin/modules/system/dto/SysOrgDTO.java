package io.kungfu.admin.modules.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel("部门表DTO")
public class SysOrgDTO {
    @ApiModelProperty(value = "主键", example = "100", position= 1, required = true)
    private Long id;
    @ApiModelProperty(value = "上级部门编号", example = "示例值", position= 2, required = true)
    private String parentCode;
    @ApiModelProperty(value = "上级部门名称", example = "示例值", position= 3, required = true)
    private String parentName;
    @ApiModelProperty(value = "部门编号", example = "示例值", position= 4, required = true)
    private String orgCode;
    @ApiModelProperty(value = "部门名称", example = "示例值", position= 5, required = true)
    private String orgName;
    @ApiModelProperty(value = "部门负责人姓名", example = "示例值", position= 6, required = true)
    private String headName;
    @ApiModelProperty(value = "部门负责人手机号", example = "示例值", position= 7, required = true)
    private String headMobile;
    @ApiModelProperty(value = "部门负责人邮件", example = "示例值", position= 8, required = true)
    private String headEmail;
    @ApiModelProperty(value = "备注", example = "示例值", position= 9, required = true)
    private String remark;
    @ApiModelProperty(value = "是否启用(1:是,0:否)", example = "1", position= 10, required = true)
    private Boolean isEnabled;
    @ApiModelProperty(value = "排序", example = "1", position= 11, required = true)
    private Integer displayNo;

    public Long getId() {
      return id;
    }
    public void setId(Long id) {
      this.id = id;
    }

    public String getParentCode() {
      return parentCode;
    }
    public void setParentCode(String parentCode) {
      this.parentCode = parentCode;
    }

    public String getParentName() {
      return parentName;
    }
    public void setParentName(String parentName) {
      this.parentName = parentName;
    }

    public String getOrgCode() {
      return orgCode;
    }
    public void setOrgCode(String orgCode) {
      this.orgCode = orgCode;
    }

    public String getOrgName() {
      return orgName;
    }
    public void setOrgName(String orgName) {
      this.orgName = orgName;
    }

    public String getHeadName() {
      return headName;
    }
    public void setHeadName(String headName) {
      this.headName = headName;
    }

    public String getHeadMobile() {
      return headMobile;
    }
    public void setHeadMobile(String headMobile) {
      this.headMobile = headMobile;
    }

    public String getHeadEmail() {
      return headEmail;
    }
    public void setHeadEmail(String headEmail) {
      this.headEmail = headEmail;
    }

    public String getRemark() {
      return remark;
    }
    public void setRemark(String remark) {
      this.remark = remark;
    }

    public Boolean getIsEnabled() {
      return isEnabled;
    }
    public void setIsEnabled(Boolean isEnabled) {
      this.isEnabled = isEnabled;
    }

    public Integer getDisplayNo() {
      return displayNo;
    }
    public void setDisplayNo(Integer displayNo) {
      this.displayNo = displayNo;
    }

}
