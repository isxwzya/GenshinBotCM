package io.github.isxwzya.GenshinBotCM.sdk.pack.from;

import io.github.isxwzya.GenshinBotCM.sdk.pack.PackBase;

/*
76 [插件]从本地文件加载图片发送到群私聊
id:群号
fid:群员QQ号
file:文件路径
 */
public class SendGroupPrivateImageFilePack extends PackBase {
    public long id;
    public long fid;
    public String file;
}
