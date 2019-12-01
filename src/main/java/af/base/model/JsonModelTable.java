package af.base.model;

/*************************************************************************
 * Copyright       Asatecx Co.,Ltd.<br/>
 * Package         pf.base.model.json<br/>
 * Class           JsonModelTable<br/>
 * Summary         ジェイソンモデルテーブル<br/><br/>
 *
 * Date             Author      Category     Version     Note<br/>
 * 2013/07/29       tang-xf       Add          1.0.0       新規作成<br/>
 ************************************************************************/
public class JsonModelTable extends JsonModel {
    /**
     * totalCountプロ―パティ
     */
    public int totalCount;

    /**
     * コンストラクタ
     */
    public JsonModelTable() {
    }

    /**
     * コンストラクタ
     * @param totalCount
     * @param dataTable
     */
    public JsonModelTable(int totalCount, Object data) {
        super(data);
        this.totalCount = totalCount;
    }

    /**
     * コンストラクタ
     * @param success
     * @param message
     * @param totalCount
     * @param dataTable
     */
    public JsonModelTable(boolean success, String message, int totalCount, Object data) {
        super(success, message, data);
        this.totalCount = totalCount;
    }
}

