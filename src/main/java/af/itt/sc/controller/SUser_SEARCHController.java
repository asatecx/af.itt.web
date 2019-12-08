/**
 * @システム名: 契約管理システム
 * @ファイル名: SUser_SEARCHController.java
 * @更新日付: 2018/10/01
 * @Copyright: 2018.
 */
package af.itt.sc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import af.base.model.JsonModel;
import af.base.model.JsonModelTable;
import af.itt.sc.bean.MstAuthorityInfo;
import af.itt.sc.bean.UserInfo;
import af.itt.sc.comon.Message;
import af.itt.sc.comon.PropertiesFileLoader;
import af.itt.sc.model.SearchCondition;
import af.itt.sc.service.SMstInfoService;
import af.itt.sc.service.SUserService;

/**
 * <pre>
 * [機 能] ユーザー検索。
 * [説 明] ユーザー検索Controller
 * @author [作 成] 2018/10/01 東山(ITT)
 * </pre>
 */
@Controller
public class SUser_SEARCHController {

    @Autowired
    private SMstInfoService sMstInfoService;

    @Autowired
    private SUserService sUserService;

    /**
     * <pre>
     * [機 能]ユーザー検索画面初期化処理
     * [説 明]ユーザー検索画面初期化処理
     * @param
     * @return String 	遷移先
     * </pre>
     */
    @RequestMapping(value = "/UserSearchInit", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonModel userSearchInit(@RequestBody SearchCondition searchCondition) {
        // マスタ情報を取得
        List<MstAuthorityInfo> mstAuthorityInfoList = sMstInfoService.getMstAuthorityInfo(null);
        return new JsonModelTable(mstAuthorityInfoList.size(), mstAuthorityInfoList);
    }

    /**
     * <pre>
     * [機 能]ユーザ情報検索処理
     * [説 明]ユーザ情報検索処理
     * @param mv
     * @param userId
     * @param userName
     * @param userRoot
     * @param delFlag
     * @return String 	遷移先
     * </pre>
     */
    @RequestMapping("UserSearch_old")
    public ModelAndView  userSearch(ModelAndView mv, HttpSession session, String userId, String userName, String userRoot, String delFlag) {
        // ユーザ検索処理
        // 画面入力項目を取得して、検索条件として、ユーザー情報を検索する
//        List<UserInfo> userInfoList = sUserService.getUserInfo(userId, userName, userRoot, delFlag);
    	List<UserInfo> userInfoList = new ArrayList<UserInfo>();
        // 検索結果が存在しない場合
        if(userInfoList.size()==0) {
            // システム情報を設定する
            String[] param = {"ユーザー情報"};
            mv.addObject("message", new Message("E", PropertiesFileLoader.getProperty("errors.salecontractupdate.nodata", param)));
        // 検索結果が存在してる場合
        }else {
            // システム情報を設定する
            mv.addObject("message", new Message("I", PropertiesFileLoader.getProperty("info.usersearch_success")));
            // ユーザー情報を設定する
            mv.addObject("userInfoList", userInfoList);
        }
        // 画面検索条件情報を保持する
        mv.addObject("userId", userId);// ユーザーID
        mv.addObject("userName", userName);// ユーザー名
        mv.addObject("authorityCd", userRoot);// 権限
        mv.addObject("delFlag", delFlag);// 削除フラグ

        // 他画面から遷移した場合、システム情報を再設定する
        // 追加処理成功した場合
        if("info.useradd_success".equals(session.getAttribute("result"))) {
            mv.addObject("message", new Message("I", PropertiesFileLoader.getProperty("info.useradd_success")));
        }
        // 更新処理成功した場合
        if("info.userupdate_success".equals(session.getAttribute("result"))) {
            mv.addObject("message", new Message("I", PropertiesFileLoader.getProperty("info.userupdate_success")));
        }
        // 削除処理成功した場合
        if("info.userdelete_success".equals(session.getAttribute("result"))) {
            mv.addObject("message", new Message("I", PropertiesFileLoader.getProperty("info.userdelete_success")));
        }
        // 削除処理失敗した場合
        if("errors.userdelete_update".equals(session.getAttribute("result"))) {
            String[] param = {session.getAttribute("param").toString()};
            mv.addObject("message", new Message("E", PropertiesFileLoader.getProperty("errors.userdelete_update", param)));
        }
        // 他画面の処理結果をクリアする
        session.removeAttribute("result");

        // ユーザー検索画面IDを設定
        mv.setViewName("User/UserList");
        return mv;
    }

    @RequestMapping(value = "/UserSearch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JsonModel getUserList(@RequestBody SearchCondition searchCondition) {

        // 入力画面と一致のユーザIDレコードを検索する
//        List<Map<String, Object>> userInfoList = mainSimpleDaoService.selectList("T_PF_HR_PORTAL.selectUserList", searchCondition, null);
    	List<UserInfo> userInfoList = sUserService.getUserInfo("", "", "", "");
//        return  new JsonModelTable(userInfoList.size(), userInfoList);
        return  new JsonModelTable(userInfoList.size(), userInfoList);
    }

}
