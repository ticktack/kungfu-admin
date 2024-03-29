package io.kungfu.admin.modules.system.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;
import java.util.Date;

/**
 * Generated by KungFuPanda, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseSysDictItem<M extends BaseSysDictItem<M>> extends Model<M> implements IBean {

	/**
	 * 主键
	 */
	public void setId(Long id) {
		set("id", id);
	}

	public Long getId() {
		return getLong("id");
	}

	/**
	 * 字典编码
	 */
	public void setDictCode(String dictCode) {
		set("dict_code", dictCode);
	}

	public String getDictCode() {
		return getStr("dict_code");
	}

	/**
	 * 字典项键
	 */
	public void setItemKey(String itemKey) {
		set("item_key", itemKey);
	}

	public String getItemKey() {
		return getStr("item_key");
	}

	/**
	 * 字典项值
	 */
	public void setItemValue(String itemValue) {
		set("item_value", itemValue);
	}

	public String getItemValue() {
		return getStr("item_value");
	}

	/**
	 * 排序
	 */
	public void setDisplayNo(Integer displayNo) {
		set("display_no", displayNo);
	}

	public Integer getDisplayNo() {
		return getInt("display_no");
	}

	/**
	 * 备注
	 */
	public void setRemark(String remark) {
		set("remark", remark);
	}

	public String getRemark() {
		return getStr("remark");
	}

	/**
	 * 创建人ID
	 */
	public void setCreateUserId(Long createUserId) {
		set("create_user_id", createUserId);
	}

	public Long getCreateUserId() {
		return getLong("create_user_id");
	}

	/**
	 * 创建人
	 */
	public void setCreateUser(String createUser) {
		set("create_user", createUser);
	}

	public String getCreateUser() {
		return getStr("create_user");
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

	/**
	 * 更新人ID
	 */
	public void setUpdateUserId(Long updateUserId) {
		set("update_user_id", updateUserId);
	}

	public Long getUpdateUserId() {
		return getLong("update_user_id");
	}

	/**
	 * 更新人
	 */
	public void setUpdateUser(String updateUser) {
		set("update_user", updateUser);
	}

	public String getUpdateUser() {
		return getStr("update_user");
	}

	/**
	 * 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		set("update_time", updateTime);
	}

	public Date getUpdateTime() {
		return getDate("update_time");
	}

}