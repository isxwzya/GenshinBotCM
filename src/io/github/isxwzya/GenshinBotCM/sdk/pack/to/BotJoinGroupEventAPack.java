package io.github.isxwzya.GenshinBotCM.sdk.pack.to;

import io.github.isxwzya.GenshinBotCM.sdk.pack.PackBase;

/*
5 [机器人]成功加入了一个新群（不确定. 可能是主动加入）（事件）
id:群号
 */
public class BotJoinGroupEventAPack extends PackBase {
    public long id;

    public BotJoinGroupEventAPack(long qq, long id) {
        this.qq = qq;
        this.id = id;
    }
}
