package af.base.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

/*************************************************************************
 * Copyright     Asatecx Co.,Ltd.<br/>
 * Package       af.base.dao<br/>
 * Class         BaseDao<br/>
 * Summary       基本となるDAO<br/>
 *
 * Date             Author      Category     Version     Note<br/>
 * 2019/11/17       tang-xf       Add          1.0.0       新規作成<br/>
 ************************************************************************/
@Repository
public class BaseDao {

    private static Log log = LogFactory.getLog(BaseDao.class);

    protected static ThreadLocal<String> OpAsTL = new ThreadLocal<String>();
    protected static ThreadLocal<String> OpByTL = new ThreadLocal<String>();

    public static void setOpAs(String opAs) {
        OpAsTL.set(opAs);
    }
    public static String getOpAs() {
        return OpAsTL.get();
    }

    public static void setOpBy(String opBy) {
        OpByTL.set(opBy);
    }
    public static String getOpBy() {
        return OpByTL.get();
    }

    /**
     * SQLセション
     */
    protected SqlSessionTemplate sqlSession;

    /**
     * SQLセション取得
     * @return SqlSessionTemplate
     */
    public SqlSessionTemplate getSqlSession() {
        return sqlSession;
    }

    /**
     * SQLセション設定
     * @param sqlSession
     */
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    /**
     * 一つのレコードを検索
     * @param statementName
     * @param parameter
     * @param order
     * @return レコード
     */
    public <T> T selectOne(String statementName, Object parameter, String order) {

        try {
            if (log.isDebugEnabled()) {
                // Log
            }
            T res = sqlSession.selectOne(statementName, parameter);
            if (log.isDebugEnabled()) log.debug("<==      Total: " + (res == null ? 0 : 1));
            return res;
        } catch(Exception e) {
            log.error("selectOne " + statementName + ": " + e);
            throw e;
        }
    }

    /**
     * 一覧を検索
     * @param statementName
     * @param parameter
     * @param order
     * @return 一覧
     */
    public <T> List<T> selectList(String statementName, Object parameter, String order) {
        try {
            if (log.isDebugEnabled()) {
            	// Log
            }
            List<T> res = sqlSession.selectList(statementName, parameter);
            if (log.isDebugEnabled()) log.debug("<==      Total: " + res.size());
            return res;
        } catch(Exception e) {
            log.error("selectList " + statementName + ": " + e);
            throw e;
        }
    }

    /**
     * 一覧を検索
     * @param statementName
     * @param parameter
     * @param start
     * @param limit
     * @param order
     * @return 一覧
     */
    public <T> List<T> selectList(String statementName, Object parameter, int start, int limit, String order) {
        RowBounds rowBounds = new RowBounds(start, limit);
        try {
            if (log.isDebugEnabled()) {
            	// Log
            }
            List<T> res = sqlSession.selectList(statementName, parameter, rowBounds);
            if (log.isDebugEnabled()) log.debug("<==      Total: " + res.size());
            return res;
        } catch(Exception e) {
            log.error("selectList " + statementName + ": " + e);
            throw e;
        }
    }

    /**
     * 一覧を検索
     * @param statementName
     * @param parameter
     * @param start
     * @param limit
     * @param order
     * @param container 結果を置く一覧
     * @return 総件数
     */
    public <T> int selectList(String statementName, Object parameter, int start, int limit, String order, List<T> container) {
        RowBounds rowBounds = new RowBounds(start, limit);
        try {
            if (log.isDebugEnabled()) {
            	// Log
            }
            List<T> list = sqlSession.selectList(statementName, parameter, rowBounds);
            if (log.isDebugEnabled()) log.debug("<==      Total(data): " + list.size());
            container.addAll(list);
            Object res = sqlSession.selectOne(statementName, parameter);
            int cnt = ((Map<String, Number>)res).get("CNT").intValue();
            if (log.isDebugEnabled()) log.debug("<==      Total(count): " + cnt);
            return cnt;
        } catch(Exception e) {
            log.error("selectList " + statementName + ": " + e);
            throw e;
        }
    }

    /**
     * 関連SQLを実行
     * @param statementName
     * @param parameter
     * @return 件数
     */
    public int execute(String statementName, Object parameter) {
        try {
            if (log.isDebugEnabled()) {
            	// Log
            }
            int rowCount = sqlSession.update(statementName, parameter);
            if (log.isDebugEnabled()) log.debug("<==      effected: " + rowCount);
            return rowCount;
        } catch(Exception e) {
            log.error("execute " + statementName + ": " + e);
            throw e;
        }
    }
}
