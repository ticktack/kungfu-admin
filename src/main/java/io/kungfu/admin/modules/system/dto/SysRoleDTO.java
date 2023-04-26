package io.kungfu.admin.modules.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel("角色表DTO")
public class SysRoleDTO {
    @ApiModelProperty(value = "暂无注释", example = "100", position= 1, required = true)
    private Long id;
    @ApiModelProperty(value = "角色编码", example = "示例值", position= 2, required = true)
    private String roleCode;
    @ApiModelProperty(value = "角色名称", example = "示例值", position= 3, required = true)
    private String roleName;
    @ApiModelProperty(value = "父角色编码", example = "示例值", position= 4, required = true)
    private String parentCode;
    @ApiModelProperty(value = "父角色名称", example = "示例值", position= 5, required = true)
    private String parentName;
    @ApiModelProperty(value = "排序", example = "1", position= 6, required = true)
    private Integer displayNo;
    @ApiModelProperty(value = "是否启用(1:是,0:否)", example = "1", position= 7, required = true)
    private Boolean isEnabled;
    @ApiModelProperty(value = "备注", example = "示例值", position= 8, required = true)
    private String remark;

    public Long getId() {
      return id;
    }
    public void setId(Long id) {
      this.id = id;
    }

    public String getRoleCode() {
      return roleCode;
    }
    public void setRoleCode(String roleCode) {
      this.roleCode = roleCode;
    }

    public String getRoleName() {
      return roleName;
    }
    public void setRoleName(String roleName) {
      this.roleName = roleName;
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

    public Integer getDisplayNo() {
      return displayNo;
    }
    public void setDisplayNo(Integer displayNo) {
      this.displayNo = displayNo;
    }

    public Boolean getIsEnabled() {
      return isEnabled;
    }
    public void setIsEnabled(Boolean isEnabled) {
      this.isEnabled = isEnabled;
    }

    public String getRemark() {
      return remark;
    }
    public void setRemark(String remark) {
      this.remark = remark;
    }

}
