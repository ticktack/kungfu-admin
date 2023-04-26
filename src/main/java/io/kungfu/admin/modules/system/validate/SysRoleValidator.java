package io.kungfu.admin.modules.system.validate;

import io.kungfu.admin.modules.system.model.SysRole;
import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.validate.Validator;
import org.kungfu.core.KungfuConstant;
import org.kungfu.util.KungfuKit;

public class SysRoleValidator extends Validator {

    @Override
    protected void validate(Controller c) {
        setShortCircuit(true);
        setRet(Ret.fail().set("code", 680));

        String json = c.getAttr(KungfuConstant.JSON_REQUEST_BODY);
        try {
            SysRole sysRole = KungfuKit.toModelValidator(json, SysRole.class);

            if (sysRole == null) {
                addError(KungfuConstant.MASSAGE, "SysRole对象不能为空");
            }
            else {
                if (StrKit.isBlank(sysRole.getRoleCode())) {
                    addError(KungfuConstant.MASSAGE, "SysRole对象属性roleCode不能为空");
                }
                if (StrKit.isBlank(sysRole.getRoleName())) {
                    addError(KungfuConstant.MASSAGE, "SysRole对象属性roleName不能为空");
                }
                if (StrKit.isBlank(sysRole.getParentCode())) {
                    addError(KungfuConstant.MASSAGE, "SysRole对象属性parentCode不能为空");
                }
                if (StrKit.isBlank(sysRole.getParentName())) {
                    addError(KungfuConstant.MASSAGE, "SysRole对象属性parentName不能为空");
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
