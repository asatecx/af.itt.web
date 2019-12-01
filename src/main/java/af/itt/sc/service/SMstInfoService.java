package af.itt.sc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import af.itt.sc.bean.MstAuthorityInfo;
import af.itt.sc.mapper.MstAuthorityInfoMapper;

/**
 * <pre>
 * [機 能] MST情報処理。
 * [説 明] MST情報処理Service
 * @author [作 成]  asatecx
 * </pre>
 */
@Service("MstInfoService")
public class SMstInfoService{

    @Autowired
    private MstAuthorityInfoMapper mstAuthorityInfoMapper;

    /**
     * <pre>
     * [機 能]権限情報の取得処理
     * [説 明]権限情報の取得処理
     * @param
     * @return List<MstAuthorityInfo> 権限情報リスト
     * </pre>
     */
    @Transactional(readOnly=true)
    public List<MstAuthorityInfo> getMstAuthorityInfo(String authorityCd) {
        return mstAuthorityInfoMapper.selectMstAuthorityInfo(authorityCd);

    }

}