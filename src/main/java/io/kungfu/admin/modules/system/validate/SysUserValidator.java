package io.kungfu.admin.modules.system.validate;

import io.kungfu.admin.modules.system.model.SysUser;
import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.validate.Validator;
import org.kungfu.core.KungfuConstant;
import org.kungfu.util.KungfuKit;

public class SysUserValidator extends Validator {

    @Override
    protected void validate(Controller c) {
        setShortCircuit(true);
        setRet(Ret.fail().set("code", 680));

        String json = c.getAttr(KungfuConstant.JSON_REQUEST_BODY);
        try {
            SysUser sysUser = KungfuKit.toModelValidator(json, SysUser.class);

            if (sysUser == null) {
                addError(KungfuConstant.MASSAGE, "SysUser对象不能为空");
            }
            else {
                if (StrKit.isBlank(sysUser.getUserCode())) {
                    addError(KungfuConstant.MASSAGE, "SysUser对象属性userCode不能为空");
                }
                if (StrKit.isBlank(sysUser.getUserName())) {
                    addError(KungfuConstant.MASSAGE, "SysUser对象属性userName不能为空");
                }
                if (StrKit.isBlank(sysUser.getOrgCode())) {
                    addError(KungfuConstant.MASSAGE, "SysUser对象属性orgCode不能为空");
                }
            }
        } catch (Exception e) {
            addError(KungfuConstant.MASSAGE, e.getMessage());
        }
    }

    @Override
    protected void handleError(Controller c) {
        c.renderJson(getRet());
    }
}
