package io.kungfu.admin.modules.system.validate;

import io.kungfu.admin.modules.system.model.SysMenu;
import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.validate.Validator;
import org.kungfu.core.KungfuConstant;
import org.kungfu.util.KungfuKit;

public class SysMenuValidator extends Validator {

    @Override
    protected void validate(Controller c) {
        setShortCircuit(true);
        setRet(Ret.fail().set("code", 680));

        String json = c.getAttr(KungfuConstant.JSON_REQUEST_BODY);
        try {
            SysMenu sysMenu = KungfuKit.toModelValidator(json, SysMenu.class);

            if (sysMenu == null) {
                addError(KungfuConstant.MASSAGE, "SysMenu对象不能为空");
            }
            else {
                if (StrKit.isBlank(sysMenu.getParentCode())) {
                    addError(KungfuConstant.MASSAGE, "SysMenu对象属性parentCode不能为空");
                }
                if (StrKit.isBlank(sysMenu.getParentName())) {
                    addError(KungfuConstant.MASSAGE, "SysMenu对象属性parentName不能为空");
                }
                if (StrKit.isBlank(sysMenu.getMenuCode())) {
                    addError(KungfuConstant.MASSAGE, "SysMenu对象属性menuCode不能为空");
                }
                if (StrKit.isBlank(sysMenu.getMenuName())) {
                    addError(KungfuConstant.MASSAGE, "SysMenu对象属性menuName不能为空");
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
