package af.itt.sc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import af.itt.sc.bean.MstAuthorityInfo;
@Mapper
public interface MstAuthorityInfoMapper {

    int deleteByPrimaryKey(String authorityCd);

    int insert(MstAuthorityInfo record);

    int insertSelective(MstAuthorityInfo record);

    List<MstAuthorityInfo> selectMstAuthorityInfo(@Param("authorityCd") String authorityCd);

    MstAuthorityInfo selectByPrimaryKey(String authorityCd);

    int updateByPrimaryKeySelective(MstAuthorityInfo record);

    int updateByPrimaryKey(MstAuthorityInfo record);
}