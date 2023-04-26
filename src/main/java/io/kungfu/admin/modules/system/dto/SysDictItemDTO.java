package io.kungfu.admin.modules.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel("字典数据表DTO")
public class SysDictItemDTO {
    @ApiModelProperty(value = "主键", example = "100", position= 1, required = true)
    private Long id;
    @ApiModelProperty(value = "字典编码", example = "示例值", position= 2, required = true)
    private String dictCode;
    @ApiModelProperty(value = "字典项键", example = "示例值", position= 3, required = true)
    private String itemKey;
    @ApiModelProperty(value = "字典项值", example = "示例值", position= 4, required = true)
    private String itemValue;
    @ApiModelProperty(value = "排序", example = "1", position= 5, required = true)
    private Integer displayNo;
    @ApiModelProperty(value = "备注", example = "示例值", position= 6, required = true)
    private String remark;

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

    public String getItemKey() {
      return itemKey;
    }
    public void setItemKey(String itemKey) {
      this.itemKey = itemKey;
    }

    public String getItemValue() {
      return itemValue;
    }
    public void setItemValue(String itemValue) {
      this.itemValue = itemValue;
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

}
