package net.sinyoo.center.service.mapper;

import net.sinyoo.center.service.domain.UserDo;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.mapping.StatementType;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    @SelectKey(before = false, keyProperty = "userId", resultType = Integer.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID() AS userId")
    int insert(UserDo record);

    @SelectKey(before = false, keyProperty = "userId", resultType = Integer.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID() AS userId")
    int insertSelective(UserDo record);

    UserDo selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserDo record);

    int updateByPrimaryKey(UserDo record);


    /**
     * 根据手机号码获取用户
     * @param phone
     * @return
     */
    UserDo selectByPhone(String phone);
}