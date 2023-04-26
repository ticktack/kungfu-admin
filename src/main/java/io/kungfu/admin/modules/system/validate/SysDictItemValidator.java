package io.kungfu.admin.modules.system.validate;

import io.kungfu.admin.modules.system.model.SysDictItem;
import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.validate.Validator;
import org.kungfu.core.KungfuConstant;
import org.kungfu.util.KungfuKit;

public class SysDictItemValidator extends Validator {

    @Override
    protected void validate(Controller c) {
        setShortCircuit(true);
        setRet(Ret.fail().set("code", 680));

        String json = c.getAttr(KungfuConstant.JSON_REQUEST_BODY);
        try {
            SysDictItem sysDictItem = KungfuKit.toModelValidator(json, SysDictItem.class);

            if (sysDictItem == null) {
                addError(KungfuConstant.MASSAGE, "SysDictItem对象不能为空");
            }
            else {
                if (StrKit.isBlank(sysDictItem.getDictCode())) {
                    addError(KungfuConstant.MASSAGE, "SysDictItem对象属性dictCode不能为空");
                }
                if (StrKit.isBlank(sysDictItem.getItemKey())) {
                    addError(KungfuConstant.MASSAGE, "SysDictItem对象属性itemKey不能为空");
                }
                if (StrKit.isBlank(sysDictItem.getItemValue())) {
                    addError(KungfuConstant.MASSAGE, "SysDictItem对象属性itemValue不能为空");
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
