package io.kungfu.admin.modules.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel("系统菜单表DTO")
public class SysMenuDTO {
    @ApiModelProperty(value = "主键", example = "100", position= 1, required = true)
    private Long id;
    @ApiModelProperty(value = "上级编码", example = "示例值", position= 2, required = true)
    private String parentCode;
    @ApiModelProperty(value = "上级名称", example = "示例值", position= 3, required = true)
    private String parentName;
    @ApiModelProperty(value = "编码", example = "示例值", position= 4, required = true)
    private String menuCode;
    @ApiModelProperty(value = "功能名称", example = "示例值", position= 5, required = true)
    private String menuName;
    @ApiModelProperty(value = "是否启用(1:是,0:否)", example = "1", position= 6, required = true)
    private Boolean isEnabled;
    @ApiModelProperty(value = "菜单地址", example = "示例值", position= 7, required = true)
    private String linkPage;
    @ApiModelProperty(value = "菜单类型(0:菜单,1:按钮)", example = "1", position= 8, required = true)
    private Integer menuType;
    @ApiModelProperty(value = "图标", example = "示例值", position= 9, required = true)
    private String icon;
    @ApiModelProperty(value = "排序", example = "1", position= 10, required = true)
    private Integer displayNo;
    @ApiModelProperty(value = "注释", example = "示例值", position= 11, required = true)
    private String remark;
    @ApiModelProperty(value = "组件名称", example = "示例值", position= 12, required = true)
    private String component;

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

    public String getMenuCode() {
      return menuCode;
    }
    public void setMenuCode(String menuCode) {
      this.menuCode = menuCode;
    }

    public String getMenuName() {
      return menuName;
    }
    public void setMenuName(String menuName) {
      this.menuName = menuName;
    }

    public Boolean getIsEnabled() {
      return isEnabled;
    }
    public void setIsEnabled(Boolean isEnabled) {
      this.isEnabled = isEnabled;
    }

    public String getLinkPage() {
      return linkPage;
    }
    public void setLinkPage(String linkPage) {
      this.linkPage = linkPage;
    }

    public Integer getMenuType() {
      return menuType;
    }
    public void setMenuType(Integer menuType) {
      this.menuType = menuType;
    }

    public String getIcon() {
      return icon;
    }
    public void setIcon(String icon) {
      this.icon = icon;
    }

    public Integer getDisplayNo() {
      return displayNo;
    }
    public void setDisplayNo(Integer displayNo) {
      this.displayNo = displayNo;
    }

    public String getRemark() {
      return remark;
    }
    public void setRemark(String remark) {
      this.remark = remark;
    }

    public String getComponent() {
      return component;
    }
    public void setComponent(String component) {
      this.component = component;
    }

}
