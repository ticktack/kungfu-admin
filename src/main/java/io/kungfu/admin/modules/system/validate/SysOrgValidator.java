package io.kungfu.admin.modules.system.validate;

import io.kungfu.admin.modules.system.model.SysOrg;
import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.validate.Validator;
import org.kungfu.core.KungfuConstant;
import org.kungfu.util.KungfuKit;

public class SysOrgValidator extends Validator {

    @Override
    protected void validate(Controller c) {
        setShortCircuit(true);
        setRet(Ret.fail().set("code", 680));

        String json = c.getAttr(KungfuConstant.JSON_REQUEST_BODY);
        try {
            SysOrg sysOrg = KungfuKit.toModelValidator(json, SysOrg.class);

            if (sysOrg == null) {
                addError(KungfuConstant.MASSAGE, "SysOrg对象不能为空");
            }
            else {
                if (StrKit.isBlank(sysOrg.getParentCode())) {
                    addError(KungfuConstant.MASSAGE, "SysOrg对象属性parentCode不能为空");
                }
                if (StrKit.isBlank(sysOrg.getParentName())) {
                    addError(KungfuConstant.MASSAGE, "SysOrg对象属性parentName不能为空");
                }
                if (StrKit.isBlank(sysOrg.getOrgCode())) {
                    addError(KungfuConstant.MASSAGE, "SysOrg对象属性orgCode不能为空");
                }
                if (StrKit.isBlank(sysOrg.getOrgName())) {
                    addError(KungfuConstant.MASSAGE, "SysOrg对象属性orgName不能为空");
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
