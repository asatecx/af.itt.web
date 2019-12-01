package af.itt.sc.model;

import java.util.HashMap;
import java.util.Map;

/*************************************************************************
 * Copyright       Asatecx Co.,Ltd.<br/>
 * Package         pf.base.model<br/>
 * Class           SearchCondition<br/>
 * Summary         検索条件クラス<br/><br/>
 *
 * Date             Author      Category     Version     Note<br/>
 * 2013/07/29       tang-xf       Add          1.0.0       新規作成<br/>
 ************************************************************************/
public class SearchCondition {
    
    //フィルタ条件
    protected Map<String, Object> filterInfo = new HashMap<>();
    //ソート条件
    protected Map<String, Integer> sortInfo = new HashMap<>();
    //抽出開始位置
    protected int skipCount;
    //抽出件数
    protected int fetchCount;
    
    /**
     * フィルタ条件取得
     * @return フィルタ条件
     */
    public Map<String, Object> getFilterInfo() {
        return filterInfo;
    }
    /**
     * フィルタ条件設定
     * @param filterInfo フィルタ条件
     */
    public void setFilterInfo(Map<String, Object> filterInfo) {
        this.filterInfo = filterInfo;
    }
    /**
     * ソート条件取得
     * @return ソート条件
     */
    public Map<String, Integer> getSortInfo() {
        return sortInfo;
    }
    /**
     * ソート条件設定
     * @param sortInfo ソート条件
     */
    public void setSortInfo(Map<String, Integer> sortInfo) {
        this.sortInfo = sortInfo;
    }
    /**
     * 抽出開始位置取得
     * @return 抽出開始位置
     */
    public int getSkipCount() {
        return skipCount;
    }
    /**
     * 抽出開始位置設定
     * @param skipCount 出開始位置
     */
    public void setSkipCount(int skipCount) {
        this.skipCount = skipCount;
    }
    /**
     * 抽出件数取得
     * @return 抽出件数
     */
    public int getFetchCount() {
        return fetchCount;
    }
    /**
     * 抽出件数設定
     * @param fetchCount 抽出件数
     */
    public void setFetchCount(int fetchCount) {
        this.fetchCount = fetchCount;
    }
}
