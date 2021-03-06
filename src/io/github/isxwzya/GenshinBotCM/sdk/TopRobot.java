package io.github.isxwzya.GenshinBotCM.sdk;

import com.alibaba.fastjson.JSON;
import io.github.isxwzya.GenshinBotCM.sdk.api.*;
import io.github.isxwzya.GenshinBotCM.sdk.enums.FriendCallType;
import io.github.isxwzya.GenshinBotCM.sdk.enums.GroupCallType;
import io.github.isxwzya.GenshinBotCM.sdk.enums.MusicKind;
import io.github.isxwzya.GenshinBotCM.sdk.enums.SendToType;
import io.github.isxwzya.GenshinBotCM.sdk.pack.from.*;
import io.github.isxwzya.GenshinBotCM.sdk.pack.re.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopRobot extends BaseRobot {
    private final Map<Long, IListFriend> getFriendsMap = new HashMap<>();
    private final Map<QQGroup, IListMember> getMembersMap = new HashMap<>();
    private final Map<QQGroup, IGroupSetting> getGroupSettingMap = new HashMap<>();
    private final Map<Long, IListGroup> getGroupsMap = new HashMap<>();
    private final Map<String, IImageUrls> getImageUrlMap = new HashMap<>();
    private final Map<QQMember, IMemberInfo> getMemberInfoMap = new HashMap<>();
    private final Map<QQFriend, IFriendInfo> getFriendInfoMap = new HashMap<>();
    private final Map<QQGroup, IGroupFiles> getGroupFilesMap = new HashMap<>();
    private final Map<QQGroup, IGroupAnnouncements> getGroupAnnouncementsMap = new HashMap<>();

    @Override
    protected boolean callTop(byte index, String data) {
        switch (index) {
            case 55: {
                ListGroupPack pack = JSON.parseObject(data, ListGroupPack.class);
                if (getGroupsMap.containsKey(pack.qq)) {
                    IListGroup action = getGroupsMap.remove(pack.qq);
                    action.res(pack);
                }
                return true;
            }
            case 56: {
                ListFriendPack pack = JSON.parseObject(data, ListFriendPack.class);
                if (getFriendsMap.containsKey(pack.qq)) {
                    IListFriend action = getFriendsMap.remove(pack.qq);
                    action.res(pack);
                }
                return true;
            }
            case 57: {
                ListMemberPack pack = JSON.parseObject(data, ListMemberPack.class);
                QQGroup key = new QQGroup() {{
                    qq = pack.qq;
                    group = pack.id;
                }};
                if (getMembersMap.containsKey(key)) {
                    IListMember action = getMembersMap.remove(key);
                    action.res(pack);
                }
                return true;
            }
            case 58: {
                GroupSettingPack pack = JSON.parseObject(data, GroupSettingPack.class);
                QQGroup key = new QQGroup() {{
                    qq = pack.qq;
                    group = pack.id;
                }};
                if (getGroupSettingMap.containsKey(key)) {
                    IGroupSetting action = getGroupSettingMap.remove(key);
                    action.res(pack);
                }
                return true;
            }
            case 90: {
                ReImagePack pack = JSON.parseObject(data, ReImagePack.class);
                if (getImageUrlMap.containsKey(pack.uuid)) {
                    IImageUrls action = getImageUrlMap.remove(pack.uuid);
                    action.res(pack.url);
                }
                return true;
            }
            case 91: {
                MemberInfoPack pack = JSON.parseObject(data, MemberInfoPack.class);
                QQMember key = new QQMember() {{
                    qq = pack.qq;
                    group = pack.id;
                    member = pack.fid;
                }};
                if (getMemberInfoMap.containsKey(key)) {
                    IMemberInfo action = getMemberInfoMap.remove(key);
                    action.res(pack);
                }
                return true;
            }
            case 92: {
                FriendInfoPack pack = JSON.parseObject(data, FriendInfoPack.class);
                QQFriend key = new QQFriend() {{
                    qq = pack.qq;
                    friend = pack.id;
                }};
                if (getFriendInfoMap.containsKey(key)) {
                    IFriendInfo action = getFriendInfoMap.remove(key);
                    action.res(pack);
                }
                return true;
            }
            case 101: {
                GroupFilesPack pack = JSON.parseObject(data, GroupFilesPack.class);
                QQGroup key = new QQGroup() {{
                    qq = pack.qq;
                    group = pack.id;
                }};
                if (getGroupFilesMap.containsKey(key)) {
                    IGroupFiles action = getGroupFilesMap.remove(key);
                    action.res(pack);
                }
                return true;
            }
            case 109: {
                GroupAnnouncementsPack pack = JSON.parseObject(data, GroupAnnouncementsPack.class);
                QQGroup key = new QQGroup() {{
                    qq = pack.qq;
                    group = pack.id;
                }};
                if (getGroupAnnouncementsMap.containsKey(key)) {
                    IGroupAnnouncements action = getGroupAnnouncementsMap.remove(key);
                    action.res(pack);
                }
                return true;
            }
            default:
                return false;
        }
    }

    /**
     * 55 [??????]???????????????
     *
     * @param qq_ qq???
     * @param res ?????????????????????
     */
    public void getGroups(long qq_, IListGroup res) {
        getGroupsMap.put(qq_, res);
        byte[] data = BuildPack.build(new GetPack() {{
            this.qq = qq_;
        }}, 55);
        addTask(data);
    }

    /**
     * 56 [??????]??????????????????
     *
     * @param qq_ qq???
     * @param res ?????????????????????
     */
    public void getFriends(long qq_, IListFriend res) {
        getFriendsMap.put(qq_, res);
        byte[] data = BuildPack.build(new GetPack() {{
            this.qq = qq_;
        }}, 56);
        addTask(data);
    }

    /**
     * 57 [??????]???????????????
     *
     * @param qq_    qq???
     * @param group_ ??????
     * @param res    ?????????????????????
     */
    public void getMembers(long qq_, long group_, IListMember res) {
        QQGroup key = new QQGroup() {{
            this.qq = qq_;
            this.group = group_;
        }};
        getMembersMap.put(key, res);
        byte[] data = BuildPack.build(new GroupGetMemberInfoPack() {{
            this.qq = qq_;
            this.id = group_;
        }}, 57);
        addTask(data);
    }

    /**
     * 58 [??????]???????????????
     *
     * @param qq_    qq???
     * @param group_ ??????
     * @param res    ?????????????????????
     */
    public void getGroupSetting(long qq_, long group_, IGroupSetting res) {
        QQGroup key = new QQGroup() {{
            this.qq = qq_;
            this.group = group_;
        }};
        getGroupSettingMap.put(key, res);
        byte[] data = BuildPack.build(new GroupGetSettingPack() {{
            this.qq = qq_;
            this.id = group_;
        }}, 58);
        addTask(data);
    }

    /**
     * 52 [??????]???????????????
     *
     * @param qq_      qq???
     * @param group_   ??????
     * @param message_ ??????
     */
    public void sendGroupMessage(long qq_, long group_, List<String> message_) {
        byte[] data = BuildPack.build(new SendGroupMessagePack() {{
            this.qq = qq_;
            this.id = group_;
            this.message = message_;
        }}, 52);
        addTask(data);
    }

    /**
     * 53 [??????]??????????????????
     *
     * @param qq_      qq???
     * @param group_   ??????
     * @param member_  ??????
     * @param message_ ??????
     */
    public void sendGroupTempMessage(long qq_, long group_, long member_, List<String> message_) {
        byte[] data = BuildPack.build(new SendGroupPrivateMessagePack() {{
            this.qq = qq_;
            this.id = group_;
            this.fid = member_;
            this.message = message_;
        }}, 53);
        addTask(data);
    }

    /**
     * 54 [??????]??????????????????
     *
     * @param qq_      qq???
     * @param friend_  ??????QQ???
     * @param message_ ??????
     */
    public void sendFriendMessage(long qq_, long friend_, List<String> message_) {
        byte[] data = BuildPack.build(new SendFriendMessagePack() {{
            this.qq = qq_;
            this.id = friend_;
            this.message = message_;
        }}, 54);
        addTask(data);
    }

    /**
     * 59 [??????]????????????
     *
     * @param qq_      qq???
     * @param eventid_ ??????ID
     * @param dofun_   ????????????
     * @param arg_     ????????????
     */
    public void eventCall(long qq_, long eventid_, int dofun_, List<Object> arg_) {
        byte[] data = BuildPack.build(new EventCallPack() {{
            this.qq = qq_;
            this.eventid = eventid_;
            this.dofun = dofun_;
            this.arg = arg_;
        }}, 59);
        addTask(data);
    }

    /**
     * ??????????????????
     *
     * @param qq_      qq???
     * @param eventid_ ??????ID
     * @param type     ????????????
     */
    public void memberJoinRequestCall(long qq_, long eventid_, GroupCallType type) {
        this.memberJoinRequestCall(qq_, eventid_, type, false, "");
    }

    /**
     * ??????????????????
     *
     * @param qq_       qq???
     * @param eventid_  ??????ID
     * @param type      ????????????
     * @param blackList ?????????????????????
     */
    public void memberJoinRequestCall(long qq_, long eventid_, GroupCallType type, boolean blackList) {
        this.memberJoinRequestCall(qq_, eventid_, type, blackList, "");
    }

    /**
     * ??????????????????
     *
     * @param qq_      qq???
     * @param eventid_ ??????ID
     * @param type     ????????????
     * @param message  ????????????
     */
    public void memberJoinRequestCall(long qq_, long eventid_, GroupCallType type, String message) {
        this.memberJoinRequestCall(qq_, eventid_, type, false, message);
    }

    /**
     * ??????????????????
     *
     * @param qq_       qq???
     * @param eventid_  ??????ID
     * @param type      ????????????
     * @param blackList ?????????????????????
     * @param message   ????????????
     */
    public void memberJoinRequestCall(long qq_, long eventid_, GroupCallType type, boolean blackList, String message) {
        eventCall(qq_, eventid_, type.ordinal(), new ArrayList<Object>() {{
            this.add(blackList);
            this.add(message);
        }});
    }

    /**
     * ?????????????????????
     *
     * @param qq_      qq???
     * @param eventid_ ??????ID
     * @param type     ????????????
     */
    public void newFriendRequestCall(long qq_, long eventid_, FriendCallType type) {
        eventCall(qq_, eventid_, type.ordinal(), new ArrayList<Object>() {{
            this.add(false);
        }});
    }

    /**
     * ?????????????????????
     *
     * @param qq_       qq???
     * @param eventid_  ??????ID
     * @param type      ????????????
     * @param blackList ?????????????????????
     */
    public void newFriendRequestCall(long qq_, long eventid_, FriendCallType type, boolean blackList) {
        eventCall(qq_, eventid_, type.ordinal(), new ArrayList<Object>() {{
            this.add(blackList);
        }});
    }

    /**
     * 64 [??????]????????????
     *
     * @param qq_     qq???
     * @param group_  ??????
     * @param member_ ??????
     */
    public void groupDeleteMember(long qq_, long group_, long member_) {
        byte[] data = BuildPack.build(new GroupKickMemberPack() {{
            this.qq = qq_;
            this.id = group_;
            this.fid = member_;
        }}, 64);
        addTask(data);
    }

    /**
     * 65 [??????]????????????
     *
     * @param qq_     qq???
     * @param group_  ??????
     * @param member_ ??????
     * @param time_   ????????????
     */
    public void groupMuteMember(long qq_, long group_, long member_, int time_) {
        byte[] data = BuildPack.build(new GroupMuteMemberPack() {{
            this.qq = qq_;
            this.id = group_;
            this.fid = member_;
            this.time = time_;
        }}, 65);
        addTask(data);
    }

    /**
     * 66 [??????]????????????
     *
     * @param qq_     qq???
     * @param group_  ??????
     * @param member_ ??????
     */
    public void groupUnmuteMember(long qq_, long group_, long member_) {
        byte[] data = BuildPack.build(new GroupUnmuteMemberPack() {{
            this.qq = qq_;
            this.id = group_;
            this.fid = member_;
        }}, 66);
        addTask(data);
    }

    /**
     * 67 [??????]??????????????????
     *
     * @param qq_    qq???
     * @param group_ ??????
     */
    public void groupMuteAll(long qq_, long group_) {
        byte[] data = BuildPack.build(new GroupMuteAllPack() {{
            this.qq = qq_;
            this.id = group_;
        }}, 67);
        addTask(data);
    }

    /**
     * 68 [??????]??????????????????
     *
     * @param qq_    qq???
     * @param group_ ??????
     */
    public void groupUnmuteAll(long qq_, long group_) {
        byte[] data = BuildPack.build(new GroupUnmuteAllPack() {{
            this.qq = qq_;
            this.id = group_;
        }}, 68);
        addTask(data);
    }

    /**
     * 69 [??????]???????????????
     *
     * @param qq_     qq???
     * @param group_  ??????
     * @param member_ ??????
     * @param card_   ?????????
     */
    public void groupSetMember(long qq_, long group_, long member_, String card_) {
        byte[] data = BuildPack.build(new GroupSetMemberCard() {{
            this.qq = qq_;
            this.id = group_;
            this.fid = member_;
            this.card = card_;
        }}, 69);
        addTask(data);
    }

    /**
     * 70 [??????]????????????
     *
     * @param qq_    qq???
     * @param group_ ??????
     * @param name_  ?????????
     */
    public void groupSetName(long qq_, long group_, String name_) {
        byte[] data = BuildPack.build(new GroupSetNamePack() {{
            this.qq = qq_;
            this.id = group_;
            this.name = name_;
        }}, 70);
        addTask(data);
    }

    /**
     * 71 [??????]????????????
     *
     * @param qq_ qq???
     * @param id_ ??????ID
     */
    public void reCallMessage(long qq_, int id_) {
        byte[] data = BuildPack.build(new ReCallMessagePack() {{
            this.qq = qq_;
            this.id = id_;
        }}, 71);
        addTask(data);
    }

    /**
     * 75 [??????]???????????????????????????????????????
     *
     * @param qq_    qq???
     * @param group_ ??????
     * @param file_  ????????????
     */
    public void sendGroupImageFile(long qq_, long group_, String file_) {
        byte[] data = BuildPack.build(new SendGroupImageFilePack() {{
            this.qq = qq_;
            this.id = group_;
            this.file = file_;
        }}, 75);
        addTask(data);
    }

    /**
     * 76 [??????]??????????????????????????? ??????????????????
     *
     * @param qq_     qq???
     * @param group_  ??????
     * @param member_ ??????
     * @param file_   ????????????
     */
    public void sendGroupPrivateImageFile(long qq_, long group_, long member_, String file_) {
        byte[] data = BuildPack.build(new SendGroupPrivateImageFilePack() {{
            this.qq = qq_;
            this.id = group_;
            this.fid = member_;
            this.file = file_;
        }}, 76);
        addTask(data);
    }

    /**
     * 77 [??????]??????????????????????????????????????????
     *
     * @param qq_    qq???
     * @param group_ ??????QQ???
     * @param file_  ????????????
     */
    public void sendFriendImageFile(long qq_, long group_, String file_) {
        byte[] data = BuildPack.build(new SendFriendImageFilePack() {{
            this.qq = qq_;
            this.id = group_;
            this.file = file_;
        }}, 77);
        addTask(data);
    }

    /**
     * 78 [??????]???????????????????????????????????????
     *
     * @param qq_    qq???
     * @param group_ ??????
     * @param file_  ????????????
     */
    public void sendGroupSoundFile(long qq_, long group_, String file_) {
        byte[] data = BuildPack.build(new SendGroupSoundFilePack() {{
            this.qq = qq_;
            this.id = group_;
            this.file = file_;
        }}, 78);
        addTask(data);
    }

    /**
     * 83 [??????]?????????????????????
     *
     * @param qq_     qq???
     * @param friend_ ??????QQ???
     */
    public void sendFriendNudge(long qq_, long friend_) {
        byte[] data = BuildPack.build(new SendFriendNudgePack() {{
            this.qq = qq_;
            this.id = friend_;
        }}, 83);
        addTask(data);
    }

    /**
     * 84 [??????]????????????????????????
     *
     * @param qq_     qq???
     * @param group_  ??????
     * @param member_ ??????
     */
    public void sendGroupMemberNudge(long qq_, long group_, long member_) {
        byte[] data = BuildPack.build(new SendGroupMemberNudgePack() {{
            this.qq = qq_;
            this.id = group_;
            this.fid = member_;
        }}, 84);
        addTask(data);
    }

    /**
     * 90 [??????]????????????Url
     *
     * @param qq_   qq???
     * @param uuid_ ??????UUID
     * @param res   ????????????
     */
    public void getImageUrls(long qq_, String uuid_, IImageUrls res) {
        getImageUrlMap.put(uuid_, res);
        byte[] data = BuildPack.build(new GetImageUrlPack() {{
            this.qq = qq_;
            this.uuid = uuid_;
        }}, 90);
        addTask(data);
    }

    /**
     * 91 [??????]?????????????????????
     *
     * @param qq_     qq???
     * @param group_  ??????
     * @param member_ ??????
     * @param res     ????????????
     */
    public void getMemberInfo(long qq_, long group_, long member_, IMemberInfo res) {
        QQMember key = new QQMember() {{
            this.qq = qq_;
            this.member = member_;
            this.group = group_;
        }};
        getMemberInfoMap.put(key, res);
        byte[] data = BuildPack.build(new GetMemberInfo() {{
            this.qq = qq_;
            this.id = group_;
            this.fid = member_;
        }}, 91);
        addTask(data);
    }

    /**
     * 92 [??????]??????????????????
     *
     * @param qq_     qq???
     * @param friend_ ??????
     * @param res     ????????????
     */
    public void getFriendInfo(long qq_, long friend_, IFriendInfo res) {
        QQFriend key = new QQFriend() {{
            this.qq = qq_;
            this.friend = friend_;
        }};
        getFriendInfoMap.put(key, res);
        byte[] data = BuildPack.build(new GetFriendInfoPack() {{
            this.qq = qq_;
            this.id = friend_;
        }}, 92);
        addTask(data);
    }

    /**
     * 93 [??????]??????????????????
     *
     * @param qq_         qq???
     * @param id_         ????????????
     * @param fid_        ????????????
     * @param kind_       ????????????
     * @param type_       ????????????
     * @param title_      ??????
     * @param summary_    ??????
     * @param jumpUrl_    ??????Url
     * @param pictureUrl_ ??????Url
     * @param musicUrl_   ??????Url
     */
    public void sendMusicShare(long qq_, long id_, long fid_, MusicKind kind_,
                               SendToType type_, String title_, String summary_,
                               String jumpUrl_, String pictureUrl_, String musicUrl_) {
        byte[] data = BuildPack.build(new SendMusicSharePack() {{
            this.qq = qq_;
            this.id = id_;
            this.fid = fid_;
            this.type = kind_.ordinal() + 1;
            this.type1 = type_.ordinal();
            this.title = title_;
            this.summary = summary_;
            this.jumpUrl = jumpUrl_;
            this.pictureUrl = pictureUrl_;
            this.musicUrl = musicUrl_;
        }}, 93);
        addTask(data);
    }

    /**
     * 94 [??????]?????????????????????
     *
     * @param qq_    qq???
     * @param group_ ??????
     * @param mid_   ??????ID
     */
    public void groupSetEssenceMessage(long qq_, long group_, int mid_) {
        byte[] data = BuildPack.build(new GroupSetEssenceMessagePack() {{
            this.qq = qq_;
            this.id = group_;
            this.mid = mid_;
        }}, 94);
        addTask(data);
    }

    /**
     * 95 [??????]????????????
     *
     * @param qq_      qq???
     * @param id_      ????????????
     * @param fid_     ????????????
     * @param type_    ??????????????????
     * @param file     ??????????????????
     * @param message_ ????????????
     * @param send_    ????????????
     */
    public void messageBuff(long qq_, long id_, long fid_, SendToType type_, String file, List<String> message_, boolean send_) {
        byte[] data = BuildPack.build(new MessageBuffPack() {{
            this.qq = qq_;
            this.id = id_;
            this.fid = fid_;
            this.type = type_.ordinal();
            this.imgurl = file;
            this.text = message_;
            this.send = send_;
        }}, 95);
        addTask(data);
    }

    /**
     * 96 [??????]??????????????????
     *
     * @param qq_     qq???
     * @param friend_ ??????QQ???
     * @param dice_   ??????
     */
    public void sendFriendDice(long qq_, long friend_, int dice_) {
        byte[] data = BuildPack.build(new SendFriendDicePack() {{
            this.qq = qq_;
            this.id = friend_;
            this.dice = dice_;
        }}, 96);
        addTask(data);
    }

    /**
     * 97 [??????]???????????????
     *
     * @param qq_    qq???
     * @param group_ ??????
     * @param dice_  ??????
     */
    public void sendGroupDice(long qq_, long group_, int dice_) {
        byte[] data = BuildPack.build(new SendGroupDicePack() {{
            this.qq = qq_;
            this.id = group_;
            this.dice = dice_;
        }}, 97);
        addTask(data);
    }

    /**
     * 98 [??????]?????????????????????
     *
     * @param qq_     qq???
     * @param group_  ??????
     * @param member_ ?????????
     * @param dice_   ??????
     */
    public void sendGroupPrivateDice(long qq_, long group_, long member_, int dice_) {
        byte[] data = BuildPack.build(new SendGroupPrivateDicePack() {{
            this.qq = qq_;
            this.id = group_;
            this.fid = member_;
            this.dice = dice_;
        }}, 98);
        addTask(data);
    }

    /**
     * 99 [??????]???????????????
     *
     * @param qq_    qq???
     * @param group_ ??????
     * @param file_  ????????????
     * @param name_  ???????????????
     */
    public void groupAddFilePack(long qq_, long group_, String file_, String name_) {
        byte[] data = BuildPack.build(new GroupAddFilePack() {{
            this.qq = qq_;
            this.id = group_;
            this.file = file_;
            this.name = name_;
        }}, 99);
        addTask(data);
    }

    /**
     * 100 [??????]???????????????
     *
     * @param qq_    qq???
     * @param group_ ??????
     * @param fid_   ?????????ID
     */
    public void groupDeleteFile(long qq_, long group_, String fid_) {
        byte[] data = BuildPack.build(new GroupDeleteFilePack() {{
            this.qq = qq_;
            this.id = group_;
            this.fid = fid_;
        }}, 100);
        addTask(data);
    }

    /**
     * 101 [??????]???????????????
     *
     * @param qq_    qq???
     * @param group_ ??????
     * @param res    ??????
     */
    public void groupGetFiles(long qq_, long group_, IGroupFiles res) {
        QQGroup key = new QQGroup() {{
            this.qq = qq_;
            this.group = group_;
        }};
        getGroupFilesMap.put(key, res);
        byte[] data = BuildPack.build(new GroupGetFilesPack() {{
            this.qq = qq_;
            this.id = group_;
        }}, 101);
        addTask(data);
    }

    /**
     * 102 [??????]???????????????
     *
     * @param qq_    qq???
     * @param group_ ??????
     * @param fid_   ??????ID
     * @param dir_   ????????????
     */
    public void groupMoveFile(long qq_, long group_, String fid_, String dir_) {
        byte[] data = BuildPack.build(new GroupMoveFilePack() {{
            this.qq = qq_;
            this.id = group_;
            this.fid = fid_;
            this.dir = dir_;
        }}, 102);
        addTask(data);
    }

    /**
     * 103 [??????]??????????????????
     *
     * @param qq_    qq???
     * @param group_ ??????
     * @param fid_   ??????ID
     * @param name_  ????????????
     */
    public void groupRemoveFile(long qq_, long group_, String fid_, String name_) {
        byte[] data = BuildPack.build(new GroupRenameFilePack() {{
            this.qq = qq_;
            this.id = group_;
            this.fid = fid_;
            this.now = name_;
        }}, 103);
        addTask(data);
    }

    /**
     * 104 [??????]????????????????????????
     *
     * @param qq_    qq???
     * @param group_ ??????
     * @param dir_   ???????????????
     */
    public void groupAddDir(long qq_, long group_, String dir_) {
        byte[] data = BuildPack.build(new GroupAddDirPack() {{
            this.qq = qq_;
            this.id = group_;
            this.dir = dir_;
        }}, 104);
        addTask(data);
    }

    /**
     * 105 [??????]????????????????????????
     *
     * @param qq_    qq???
     * @param group_ ??????
     * @param dir_   ???????????????
     */
    public void groupRemoveDir(long qq_, long group_, String dir_) {
        byte[] data = BuildPack.build(new GroupDeleteDirPack() {{
            this.qq = qq_;
            this.id = group_;
            this.dir = dir_;
        }}, 105);
        addTask(data);
    }

    /**
     * 106 [??????]???????????????????????????
     *
     * @param qq_    qq???
     * @param group_ ??????
     * @param old_   ????????????
     * @param now_   ????????????
     */
    public void groupRenameDir(long qq_, long group_, String old_, String now_) {
        byte[] data = BuildPack.build(new GroupRenameDirPack() {{
            this.qq = qq_;
            this.id = group_;
            this.old = old_;
            this.now = now_;
        }}, 106);
        addTask(data);
    }

    /**
     * 107 [??????]??????????????????????????????
     *
     * @param qq_    qq???
     * @param group_ ??????
     * @param fid_   ??????ID
     * @param file_  ??????????????????
     */
    public void groupDownloadFile(long qq_, long group_, String fid_, String file_) {
        byte[] data = BuildPack.build(new GroupDownloadFilePack() {{
            this.qq = qq_;
            this.id = group_;
            this.fid = fid_;
            this.dir = file_;
        }}, 107);
        addTask(data);
    }

    /**
     * 108 [??????]?????????????????????
     *
     * @param qq_     qq???
     * @param group_  ??????
     * @param member_ ?????????QQ???
     * @param set_    ????????????
     */
    public void groupSetAdmin(long qq_, long group_, long member_, boolean set_) {
        byte[] data = BuildPack.build(new GroupSetAdminPack() {{
            this.qq = qq_;
            this.id = group_;
            this.fid = member_;
            this.type = set_;
        }}, 108);
        addTask(data);
    }

    /**
     * 109 [??????]???????????????
     *
     * @param qq_    qq???
     * @param group_ ??????
     * @param res    ??????
     */
    public void groupGetAnnouncements(long qq_, long group_, IGroupAnnouncements res) {
        QQGroup key = new QQGroup() {{
            this.qq = qq_;
            this.group = group_;
        }};
        getGroupAnnouncementsMap.put(key, res);
        byte[] data = BuildPack.build(new GroupGetAnnouncementsPack() {{
            this.qq = qq_;
            this.id = group_;
        }}, 109);
        addTask(data);
    }

    /**
     * 110 [??????]???????????????
     *
     * @param qq_                  qq???
     * @param group_               ??????
     * @param imageFile_           ????????????
     * @param sendToNewMember_     ??????????????????
     * @param isPinned_            ??????
     * @param showEditCard_        ????????????????????????????????????????????????
     * @param showPopup_           ????????????
     * @param requireConfirmation_ ?????????????????????
     * @param text_                ????????????
     */
    public void groupAddAnnouncement(long qq_, long group_, String imageFile_, boolean sendToNewMember_,
                                     boolean isPinned_, boolean showEditCard_, boolean showPopup_,
                                     boolean requireConfirmation_, String text_) {
        byte[] data = BuildPack.build(new GroupAddAnnouncementPack() {{
            this.qq = qq_;
            this.id = group_;
            this.imageFile = imageFile_;
            this.sendToNewMember = sendToNewMember_;
            this.isPinned = isPinned_;
            this.showEditCard = showEditCard_;
            this.showPopup = showPopup_;
            this.requireConfirmation = requireConfirmation_;
            this.text = text_;
        }}, 110);
        addTask(data);
    }

    /**
     * 111 [??????]???????????????
     *
     * @param qq_    qq???
     * @param group_ ??????
     * @param fid_   ??????ID
     */
    public void groupRemoveAnnouncement(long qq_, long group_, String fid_) {
        byte[] data = BuildPack.build(new GroupDeleteAnnouncementPack() {{
            this.qq = qq_;
            this.id = group_;
            this.fid = fid_;
        }}, 111);
        addTask(data);
    }

    /**
     * 112 [??????]????????????????????????
     *
     * @param qq_    qq???
     * @param group_ ??????QQ???
     * @param file_  ????????????
     */
    public void sendFriendSoundFile(long qq_, long group_, String file_) {
        byte[] data = BuildPack.build(new SendFriendSoundFilePack() {{
            this.qq = qq_;
            this.id = group_;
            this.file = file_;
        }}, 112);
        addTask(data);
    }
}
