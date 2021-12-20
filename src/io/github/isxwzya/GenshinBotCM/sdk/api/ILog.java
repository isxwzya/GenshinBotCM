package io.github.isxwzya.GenshinBotCM.sdk.api;

import io.github.isxwzya.GenshinBotCM.sdk.enums.LogType;

public interface ILog {
    void LogAction(LogType type, String data);
}
