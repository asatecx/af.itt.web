/**
 * @システム名: 契約管理システム
 * @ファイル名: sUserService.java
 * @更新日付: 2018/10/01
 * @Copyright: 2018
 */
package af.itt.sc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import af.itt.sc.bean.UserInfo;
import af.itt.sc.mapper.UserInfoMapper;

/**
 * <pre>
 * [機 能] ユーザーログイン。
 * [説 明] ユーザーログインSUserServiceImpl
 * @author [作 成] 2018/10/01 東山(ITTraining)
 * </pre>
 */
@Service("SUserService")
public class SUserService{

    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * <pre>
     * [機 能]ログインユーザ検索処理
     * [説 明]ログインユーザ情報によってユーザ検索します
     * @param userId ユーザーＩＤ
     * @param password パスワード
     * @return int ログインユーザ件数
     * </pre>
     */
    public List<UserInfo> getUserInfoList(String userId, String password) {

        List<UserInfo> userInfoList = userInfoMapper.getUserList(userId, password);
        return userInfoList;
    }

    /**
     * <pre>
     * [機 能]ユーザ情報検索処理
     * [説 明]ユーザ情報検索処理
     * &#64;param
     * &#64;return  List<UserInfo> ユーザ情報リスト
     * </pre>
     */
    @Transactional(readOnly = true)
    public List<UserInfo> getUserInfo(String userId, String userName, String authorityCd, String delFlag) {
        List<UserInfo> userInfoList = userInfoMapper.selectUserInfo(userId, userName, authorityCd, delFlag);
        return userInfoList;
    }

    /**
     * <pre>
     * [機 能]ユーザ情報検索処理
     * [説 明]ユーザーIDにより、ユーザ情報検索処理
     * @param
     * @return UserInfo ユーザ情報
     * </pre>
     */
    @Transactional(readOnly = true)
    public UserInfo getUserInfoByPrimaryKey(String userId) {
        UserInfo userInfo= userInfoMapper.selectByPrimaryKey(userId);
        return userInfo;
    };

    /**
     * <pre>
     * [機 能]ユーザ情報作成処理
     * [説 明]ユーザ情報作成処理
     * @param userInfo
     * @return
     * </pre>
     */
    public void addUserInfo(UserInfo userInfo) {
        userInfoMapper.insert(userInfo);
    }

    /**
     * <pre>
     * [機 能]ユーザ情報更新処理
     * [説 明]ユーザ情報更新処理
     * @param userInfo
     * @return
     * </pre>
     */
    public int updateUserInfo(UserInfo userInfo) {
        return userInfoMapper.updateByPrimaryKey(userInfo);
    }

    /**
     * <pre>
     * [機 能]ユーザ情報削除処理
     * [説 明]ユーザ情報削除処理
     * @param userInfo
     * @return
     * </pre>
     */
    public int deleteUserInfo(String  userId) {
         return userInfoMapper.deleteByPrimaryKey(userId);
    }
}
