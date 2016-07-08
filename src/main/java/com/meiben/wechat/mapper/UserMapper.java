package com.meiben.wechat.mapper;

import com.meiben.wechat.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by joker on 2016/4/26.
 */
public interface UserMapper {

    @Select("SELECT * FROM wx_user WHERE openid = #{openid}")
    public User queryByOpenid(@Param("openid") String openid);

    @Insert("INSERT INTO wx_user(uid, openid, nickname, sex, city" +
            ", country, province, language, headimgurl" +
            ",subscribe_time, unionid, remark, groupid) values(" +
            "#{uid}, #{openid}, #{nickname}, #{sex}, #{city}, #{country}" +
            ", #{province}, #{language}, #{headimgurl}" +
            ",#{subscribe_time}, #{unionid}, #{remark}, #{groupid})")
    public void insert(User user);

    @Update("UPDATE wx_user set nickname=#{nickname}, sex=#{sex}, country=#{country}" +
            ", province=#{province}, language=#{language}, headimgurl=#{headimgurl}" +
            ", subscribe_time=#{subscribe_time}, unionid=#{unionid}, remark=#{remark}" +
            ", groupid=#{groupid} where openid=#{openid}")
    public void updateUserAllInfo(User user);

    @Delete("DELETE FROM wx_user where openid=#{openid} ")
    public void delete(@Param("openid") String openid);

    @Select("SELECT *FROM wx_user")
    public List<User> queryAll();
}
