package cn.itsource.util;

/**
 * ajax请求结果封装的工具类
 */
public class AjaxResult {

    private Boolean success=true;

    private String massage="操作成功";

    private Object resultBack;

    private AjaxResult() { }


    public static AjaxResult me(){
        return new AjaxResult();
    }


    public Boolean getSuccess() {
        return success;
    }

    public AjaxResult setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public String getMassage() {
        return massage;
    }

    public AjaxResult setMassage(String massage) {
        this.massage = massage;
        return this;
    }

    public Object getResultBack() {
        return resultBack;
    }

    public AjaxResult setResultBack(Object resultBack) {
        this.resultBack = resultBack;
        return  this;
    }
}
