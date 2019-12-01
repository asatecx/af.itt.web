package af.base.model;

/*************************************************************************
 * Copyright       Asatecx Co.,Ltd.<br/>
 * Package         pf.base.model.json<br/>
 * Class           JsonModel<br/>
 * Summary         ジェイソンモデル<br/><br/>
 *
 * Date             Author      Category     Version     Note<br/>
 * 2013/07/29       tang-xf       Add          1.0.0       新規作成<br/>
 ************************************************************************/
public class JsonModel {
     /**
     * successプロ―パティ
     */
    public boolean success = true;

    /**
     * messageプロ―パティ
     */
    public String message;

    /**
     * dataプロ―パティ
     */
    public Object data;

    /**
     * コンストラクタ
     */
    public JsonModel() {
    }

    /**
     * コンストラクタ
     * @param data
     */
    public JsonModel(Object data) {
        this.data = data;
    }

    /**
     * コンストラクタ
     * @param success
     * @param message
     */
    public JsonModel(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * コンストラクタ
     * @param success
     * @param message
     * @param data
     */
    public JsonModel(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
