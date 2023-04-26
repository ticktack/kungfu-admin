package io.kungfu.admin.modules.system.validate;

import io.kungfu.admin.modules.system.model.SysDict;
import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.validate.Validator;
import org.kungfu.core.KungfuConstant;
import org.kungfu.util.KungfuKit;

public class SysDictValidator extends Validator {

    @Override
    protected void validate(Controller c) {
        setShortCircuit(true);
        setRet(Ret.fail().set("code", 680));

        String json = c.getAttr(KungfuConstant.JSON_REQUEST_BODY);
        try {
            SysDict sysDict = KungfuKit.toModelValidator(json, SysDict.class);

            if (sysDict == null) {
                addError(KungfuConstant.MASSAGE, "SysDict对象不能为空");
            }
            else {
                if (StrKit.isBlank(sysDict.getDictCode())) {
                    addError(KungfuConstant.MASSAGE, "SysDict对象属性dictCode不能为空");
                }
                if (StrKit.isBlank(sysDict.getDictName())) {
                    addError(KungfuConstant.MASSAGE, "SysDict对象属性dictName不能为空");
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
