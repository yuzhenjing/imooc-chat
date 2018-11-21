package com.chat.mapper;

import com.chat.pojo.MyFriends;
import com.chat.vo.MyFriendsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyFriendsMapper {
    int deleteByPrimaryKey(String id);

    int insert(MyFriends record);

    int insertSelective(MyFriends record);

    MyFriends selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MyFriends record);

    int updateByPrimaryKey(MyFriends record);

    /**
     * 查询是否是好友
     *
     * @param myId
     * @param friendId
     * @return
     */
    int findFriendsById(@Param("myId") String myId, @Param("friendId") String friendId);

    /**
     * 查询我的好友
     *
     * @param userId
     * @return
     */
    List<MyFriendsVO> queryMyfriends(String userId);
}
