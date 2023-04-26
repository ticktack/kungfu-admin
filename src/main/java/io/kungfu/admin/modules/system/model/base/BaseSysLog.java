package io.kungfu.admin.modules.system.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;
import java.util.Date;

/**
 * Generated by KungFuPanda, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseSysLog<M extends BaseSysLog<M>> extends Model<M> implements IBean {

	/**
	 * 
	 */
	public void setId(Long id) {
		set("id", id);
	}

	public Long getId() {
		return getLong("id");
	}

	/**
	 * 接口地址
	 */
	public void setApiUrl(String apiUrl) {
		set("api_url", apiUrl);
	}

	public String getApiUrl() {
		return getStr("api_url");
	}

	/**
	 * 接口请求方式
	 */
	public void setApiType(String apiType) {
		set("api_type", apiType);
	}

	public String getApiType() {
		return getStr("api_type");
	}

	/**
	 * 接口方法
	 */
	public void setApiMethod(String apiMethod) {
		set("api_method", apiMethod);
	}

	public String getApiMethod() {
		return getStr("api_method");
	}

	/**
	 * 请求参数
	 */
	public void setApiParam(String apiParam) {
		set("api_param", apiParam);
	}

	public String getApiParam() {
		return getStr("api_param");
	}

	/**
	 * 接口请求耗时
	 */
	public void setApiCost(Long apiCost) {
		set("api_cost", apiCost);
	}

	public Long getApiCost() {
		return getLong("api_cost");
	}

	/**
	 * 响应结果
	 */
	public void setApiResult(String apiResult) {
		set("api_result", apiResult);
	}

	public String getApiResult() {
		return getStr("api_result");
	}

	/**
	 * 访问者ID
	 */
	public void setVisitorId(Long visitorId) {
		set("visitor_id", visitorId);
	}

	public Long getVisitorId() {
		return getLong("visitor_id");
	}

	/**
	 * 访问者
	 */
	public void setVisitor(String visitor) {
		set("visitor", visitor);
	}

	public String getVisitor() {
		return getStr("visitor");
	}

	/**
	 * 行为备注
	 */
	public void setRemark(String remark) {
		set("remark", remark);
	}

	public String getRemark() {
		return getStr("remark");
	}

	/**
	 * 访问IP
	 */
	public void setVisitIp(String visitIp) {
		set("visit_ip", visitIp);
	}

	public String getVisitIp() {
		return getStr("visit_ip");
	}

	/**
	 * 访问端口号
	 */
	public void setVisitPort(Integer visitPort) {
		set("visit_port", visitPort);
	}

	public Integer getVisitPort() {
		return getInt("visit_port");
	}

	/**
	 * 访问地址
	 */
	public void setAddress(String address) {
		set("address", address);
	}

	public String getAddress() {
		return getStr("address");
	}

	/**
	 * 创建日期
	 */
	public void setCreateDay(Integer createDay) {
		set("create_day", createDay);
	}

	public Integer getCreateDay() {
		return getInt("create_day");
	}

	/**
	 * 创建月份
	 */
	public void setCreateMonth(Integer createMonth) {
		set("create_month", createMonth);
	}

	public Integer getCreateMonth() {
		return getInt("create_month");
	}

	/**
	 * 创建年份
	 */
	public void setCreateYear(Integer createYear) {
		set("create_year", createYear);
	}

	public Integer getCreateYear() {
		return getInt("create_year");
	}

	/**
	 * 创建时间
	 */
	public void setCreateTime(Date createTime) {
		set("create_time", createTime);
	}

	public Date getCreateTime() {
		return getDate("create_time");
	}

}