package io.github.isxwzya.GenshinBotCM.sdk.pack.re;

import io.github.isxwzya.GenshinBotCM.sdk.objs.UserProfile;
import io.github.isxwzya.GenshinBotCM.sdk.pack.PackBase;

/*
92 [插件]获取朋友信息
id:QQ号
img:头像图片
remark:好友备注
userProfile:用户详细资料
 */
public class FriendInfoPack extends PackBase {
    public long id;
    public String img;
    public String remark;
    public UserProfile userProfile;
}
