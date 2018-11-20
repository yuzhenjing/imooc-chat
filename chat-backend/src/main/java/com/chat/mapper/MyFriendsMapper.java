package com.chat.mapper;

import com.chat.pojo.MyFriends;
import org.apache.ibatis.annotations.Param;

public interface MyFriendsMapper {
    int deleteByPrimaryKey(String id);

    int insert(MyFriends record);

    int insertSelective(MyFriends record);

    MyFriends selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MyFriends record);

    int updateByPrimaryKey(MyFriends record);

    /**
     * 查询是否是好友
     * @param myId
     * @param friendId
     * @return
     */
    int findFriendsById(@Param("myId") String myId, @Param("friendId") String friendId);
}
