package io.kungfu.admin.modules.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel("系统字典表DTO")
public class SysDictDTO {
    @ApiModelProperty(value = "主键", example = "100", position= 1, required = true)
    private Long id;
    @ApiModelProperty(value = "字典编号", example = "示例值", position= 3, required = true)
    private String dictCode;
    @ApiModelProperty(value = "字典名称", example = "示例值", position= 4, required = true)
    private String dictName;
    @ApiModelProperty(value = "备注", example = "示例值", position= 5, required = true)
    private String remark;
    @ApiModelProperty(value = "排序", example = "1", position= 6, required = true)
    private Integer displayNo;
    @ApiModelProperty(value = "是否启用(1:是,0:否)", example = "1", position= 7, required = true)
    private Boolean isEnabled;

    public Long getId() {
      return id;
    }
    public void setId(Long id) {
      this.id = id;
    }

    public String getDictCode() {
      return dictCode;
    }
    public void setDictCode(String dictCode) {
      this.dictCode = dictCode;
    }

    public String getDictName() {
      return dictName;
    }
    public void setDictName(String dictName) {
      this.dictName = dictName;
    }

    public String getRemark() {
      return remark;
    }
    public void setRemark(String remark) {
      this.remark = remark;
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

}
