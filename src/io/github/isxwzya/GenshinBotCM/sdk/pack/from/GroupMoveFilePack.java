package io.github.isxwzya.GenshinBotCM.sdk.pack.from;

import io.github.isxwzya.GenshinBotCM.sdk.pack.PackBase;

/*
102 [插件]移动群文件
id:群号
fid:原群文件ID
dir:新群文件路径
 */
public class GroupMoveFilePack extends PackBase {
    public long id;
    public String fid;
    public String dir;
}
