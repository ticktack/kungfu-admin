package io.kungfu.admin.modules.system.validate;

import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.validate.Validator;
import org.kungfu.core.KungfuConstant;

public class DeleteUserRoleValidator extends Validator {

    @Override
    protected void validate(Controller c) {
        setShortCircuit(true);
        setRet(Ret.fail().set("code", 680));

        String userCode = c.getAttr("userCode");
        String roleCode = c.getAttr("roleCode");

        if (StrKit.isBlank(userCode)) {
            addError(KungfuConstant.MASSAGE, "GET 参数roleCode不能为空");
        }
        if (StrKit.isBlank(roleCode)) {
            addError(KungfuConstant.MASSAGE, "GET 参数userCode不能为空");
        }
    }

    @Override
    protected void handleError(Controller c) {
        c.renderJson(getRet());
    }
}
