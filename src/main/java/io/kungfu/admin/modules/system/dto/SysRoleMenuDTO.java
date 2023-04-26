package io.kungfu.admin.modules.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel("角色菜单关系表DTO")
public class SysRoleMenuDTO {
    @ApiModelProperty(value = "暂无注释", example = "100", position= 1, required = true)
    private Long id;
    @ApiModelProperty(value = "角色编码", example = "示例值", position= 2, required = true)
    private String roleCode;
    @ApiModelProperty(value = "菜单编码", example = "示例值", position= 3, required = true)
    private String menuCode;

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

    public String getMenuCode() {
      return menuCode;
    }
    public void setMenuCode(String menuCode) {
      this.menuCode = menuCode;
    }

}
