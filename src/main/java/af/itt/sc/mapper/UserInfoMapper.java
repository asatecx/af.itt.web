package af.itt.sc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import af.itt.sc.bean.UserInfo;
@Mapper
public interface UserInfoMapper {

    int deleteByPrimaryKey(String userId);

    int insert(UserInfo record);

    List<UserInfo>  getUserList(@Param("userId")String userId, @Param("password")String password);

    List<UserInfo> selectUserInfo(@Param("userId") String userId,@Param("userName") String userName,@Param("userRoot") String userRoot,@Param("delFlag") String delFlag);

    UserInfo selectByPrimaryKey(String userId);


    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}