package io.github.isxwzya.GenshinBotCM.sdk.pack.from;

import io.github.isxwzya.GenshinBotCM.sdk.pack.PackBase;

/*
64 [插件]删除群员
id:群号
fid:群员QQ号
black:黑名单
 */
public class GroupKickMemberPack extends PackBase {
    public long id;
    public long fid;
    public boolean black;
}
