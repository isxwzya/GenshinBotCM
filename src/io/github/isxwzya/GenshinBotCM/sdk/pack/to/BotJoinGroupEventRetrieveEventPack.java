package io.github.isxwzya.GenshinBotCM.sdk.pack.to;

import io.github.isxwzya.GenshinBotCM.sdk.pack.PackBase;

/*
80 [机器人]机器人群恢复（事件）
id:群号
 */
public class BotJoinGroupEventRetrieveEventPack extends PackBase {
    public long id;

    public BotJoinGroupEventRetrieveEventPack(long qq, long id) {
        this.qq = qq;
        this.id = id;
    }
}
