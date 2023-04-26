package io.kungfu.admin.modules.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel("系统日志DTO")
public class SysLogDTO {
    @ApiModelProperty(value = "暂无注释", example = "100", position= 1, required = true)
    private Long id;
    @ApiModelProperty(value = "接口地址", example = "示例值", position= 2, required = true)
    private String apiUrl;
    @ApiModelProperty(value = "接口请求方式", example = "示例值", position= 3, required = true)
    private String apiType;
    @ApiModelProperty(value = "接口方法", example = "示例值", position= 4, required = true)
    private String apiMethod;
    @ApiModelProperty(value = "请求参数", example = "示例值", position= 5)
    private String apiParam;
    @ApiModelProperty(value = "接口请求耗时", example = "100", position= 6, required = true)
    private Long apiCost;
    @ApiModelProperty(value = "响应结果", example = "示例值", position= 7)
    private String apiResult;
    @ApiModelProperty(value = "访问者ID", example = "100", position= 8, required = true)
    private Long visitorId;
    @ApiModelProperty(value = "访问者", example = "示例值", position= 9, required = true)
    private String visitor;
    @ApiModelProperty(value = "行为备注", example = "示例值", position= 10, required = true)
    private String remark;
    @ApiModelProperty(value = "访问IP", example = "示例值", position= 11, required = true)
    private String visitIp;
    @ApiModelProperty(value = "访问端口号", example = "1", position= 12, required = true)
    private Integer visitPort;
    @ApiModelProperty(value = "访问地址", example = "示例值", position= 13, required = true)
    private String address;
    @ApiModelProperty(value = "创建日期", example = "1", position= 14, required = true)
    private Integer createDay;
    @ApiModelProperty(value = "创建月份", example = "1", position= 15, required = true)
    private Integer createMonth;
    @ApiModelProperty(value = "创建年份", example = "1", position= 16, required = true)
    private Integer createYear;

    public Long getId() {
      return id;
    }
    public void setId(Long id) {
      this.id = id;
    }

    public String getApiUrl() {
      return apiUrl;
    }
    public void setApiUrl(String apiUrl) {
      this.apiUrl = apiUrl;
    }

    public String getApiType() {
      return apiType;
    }
    public void setApiType(String apiType) {
      this.apiType = apiType;
    }

    public String getApiMethod() {
      return apiMethod;
    }
    public void setApiMethod(String apiMethod) {
      this.apiMethod = apiMethod;
    }

    public String getApiParam() {
      return apiParam;
    }
    public void setApiParam(String apiParam) {
      this.apiParam = apiParam;
    }

    public Long getApiCost() {
      return apiCost;
    }
    public void setApiCost(Long apiCost) {
      this.apiCost = apiCost;
    }

    public String getApiResult() {
      return apiResult;
    }
    public void setApiResult(String apiResult) {
      this.apiResult = apiResult;
    }

    public Long getVisitorId() {
      return visitorId;
    }
    public void setVisitorId(Long visitorId) {
      this.visitorId = visitorId;
    }

    public String getVisitor() {
      return visitor;
    }
    public void setVisitor(String visitor) {
      this.visitor = visitor;
    }

    public String getRemark() {
      return remark;
    }
    public void setRemark(String remark) {
      this.remark = remark;
    }

    public String getVisitIp() {
      return visitIp;
    }
    public void setVisitIp(String visitIp) {
      this.visitIp = visitIp;
    }

    public Integer getVisitPort() {
      return visitPort;
    }
    public void setVisitPort(Integer visitPort) {
      this.visitPort = visitPort;
    }

    public String getAddress() {
      return address;
    }
    public void setAddress(String address) {
      this.address = address;
    }

    public Integer getCreateDay() {
      return createDay;
    }
    public void setCreateDay(Integer createDay) {
      this.createDay = createDay;
    }

    public Integer getCreateMonth() {
      return createMonth;
    }
    public void setCreateMonth(Integer createMonth) {
      this.createMonth = createMonth;
    }

    public Integer getCreateYear() {
      return createYear;
    }
    public void setCreateYear(Integer createYear) {
      this.createYear = createYear;
    }

}
