package ghaya.learn.Base;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用返回结果集
 */
public class GhayaRes {
    private List<Map<String, Object>> resList;
    private Map<String, Object> resMap;
    private String resString;
    private String msg;
    private StringBuilder All = new StringBuilder("");
    private String splitStr = " / ";

    public GhayaRes() {
        this.msg = "kong";
        this.resString = "kong";
        this.All.append("");
    }

    public String putAll(GhayaRes res) {
        this.All.append(res.getMsg() + this.splitStr);
        return this.All.toString();
    }

    public GhayaRes(String msg) {
        this.msg = msg;
        this.All.append(msg + this.splitStr);
    }

    public List<Map<String, Object>> getResList() {
        return resList;
    }

    public void setResList(List<Map<String, Object>> resList) {
        this.resList = resList;
    }

    public Map<String, Object> getResMap() {
        return resMap;
    }

    public void setResMap(Map<String, Object> resMap) {
        this.resMap = resMap;
    }

    public String getResString() {
        return resString;
    }

    public void setResString(String resString) {
        this.resString = resString;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAll() {
        return All.toString();
    }

    public void setAll(StringBuilder all) {
        All = all;
    }

    public String getSplitStr() {
        return splitStr;
    }

    public void setSplitStr(String splitStr) {
        this.splitStr = splitStr;
    }
}
