package io.github.isxwzya.GenshinBotCM.src.LuckToday;

import java.io.File;
import java.util.Calendar;
import java.util.Random;

public class LuckToday {

    static String date = getNowDate();
    static String cachePath = "LuckTodayCache\\"+ date + ".yml";

    public int getRandomInt(int a){
        Random Ran = new Random(a);
        return Ran.nextInt(101);
    }
    public static String getNowDate(){
        Calendar calendar =Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return year + "-" + month + "-" + day;
    }
    public static void loadLuckToday(){
        File cacheDir = new File("LuckTodayCache");
        if (!cacheDir.exists()) {
            if (!cacheDir.mkdir()) {
                System.out.println("无法创建人品存储文件夹！");
            }
        }
        File cacheFile = new File(cachePath);
    }
}


/*public class UserLuck {
    public static Map<Long,Integer> cacheLuckMap = new HashMap<>();

    public static String luckMessage;
    public static String fileDate;

    public static Integer luckMax;
    public static Integer luckMin;

    public static Random random = new Random();

    public static FileWriter getWriter;

    public static Integer getRandomInt(int max,int min) {
        return random.nextInt(max-min+1)+min;
    }

    public static void getLuck(MessageChainBuilder chain, long number, MessageChain message) {
        if (!getNowDate().equals(fileDate)) {
            updateLuckDate();
        }
        if (cacheLuckMap.containsKey(number)) {
            chain.append(luckMessage.replace("{rp}",String.valueOf(cacheLuckMap.get(number))));
        } else {
            int luck = getRandomInt(luckMax,luckMin);
            chain.append(luckMessage.replace("{rp}",String.valueOf(luck)));
            writerInput(number,luck);
        }
    }

    public static void updateLuckDate() {
        sendInfo("日期已更新！今日人品缓存刷新中！");
        cacheLuckMap.clear();
        sendInfo("已更新今日人品缓存");
        writerUpdate();
        sendInfo("已更新今日人品存储");
        sendInfo("今日人品模块日期更新完毕！");
    }

    public static void loadLuck() throws Exception{
        File cacheDir = new File(getPluginConfigDir(pluginName)+"/luckSave");
        if (!cacheDir.exists()) {
            if (!cacheDir.mkdir()) {
                sendError("无法创建人品存储文件夹！");
            }
        }
        String date = getNowDate();
        File cacheFile = new File(getPluginConfigDir(pluginName)+"/luckSave/"+date+".yml");
        getWriter = new FileWriter(cacheFile,true);
        if (cacheFile.exists()) {
            Yaml yaml = new Yaml();
            FileInputStream input;
            input = new FileInputStream(cacheFile);
            Map<?,?> luckCache = yaml.loadAs(input, Map.class);
            if (luckCache != null) {
                Iterator<?> iterator = luckCache.keySet().iterator();
                sendInfo("正在读取人品保存文件");
                Object user;
                long userNumber;
                int luckNumber;
                while (iterator.hasNext()) {
                    user = iterator.next();
                    userNumber = Long.parseLong(user.toString());
                    luckNumber = Integer.parseInt(luckCache.get(user).toString());
                    cacheLuckMap.put(userNumber, luckNumber);
                }
                luckCache.clear();
            }
            sendInfo("用户今日人品缓存读取完成");
        }
        fileDate = getNowDate();
    }

    public static void writerUpdate() {
        String date = null;
        try {
            date = getNowDate();
            if (getWriter != null) {
                getWriter.close();
            }
            String targetFilePath = getPluginConfigDir(pluginName)+"/luckSave/"+date+".yml";
            getWriter = new FileWriter(targetFilePath,true);
        } catch (IOException e) {
            sendException(e);
            sendWarn("今日人品模块写入功能初始化失败！");
        } finally {
            fileDate = date;
        }
    }

    public static void writerInput(Long number,int userLuck) {
        try {
            cacheLuckMap.put(number,userLuck);
            getWriter.write("\r\n"+number+": "+userLuck);
            getWriter.flush();
        } catch (IOException e) {
            sendException(e);
            sendWarn("用户人品缓存写入失败！");
        }
    }
}
public class MossLuck {

    public static void onEnable() throws Exception{
        sendInfo("欢迎使用MossLuck插件 By 墨守MossCG");
        sendInfo("正在加载配置文件");
        InputStream propertiesStream = MossLuck.class.getClassLoader().getResourceAsStream("plugin.properties");
        properties = new Properties();
        properties.load(propertiesStream);
        pluginName = properties.getProperty("pluginName");
        saveDefaultConfig(pluginName);
        loadDefaultConfig(pluginName);
        getConfig = getDefaultConfig(pluginName);
        luckMessage = getConfig.get("luckMessage");
        luckMax = Integer.valueOf(getConfig.get("luckMax"));
        luckMin = Integer.valueOf(getConfig.get("luckMin"));
        sendInfo("配置文件加载完成");
        sendInfo("正在加载人品记录");
        loadLuck();
        sendInfo("记录人品加载完成");
        sendInfo("正在注册指令");
        loadCommand();
        sendInfo("指令注册完成");
    }

    public static String pluginName;
    public static Properties properties;
    public static Map<String,String> getConfig;

    public static void loadCommand() throws Exception{
        Class<?> methodClass = Class.forName("org.mossmc.mosscg.MossLuck.UserLuck");
        String[] commands = getConfig.get("luckCommand").split("\\|");
        int i = 0;
        while (i<commands.length) {
            registerCommand(commands[i], methodClass.getDeclaredMethod("getLuck", MessageChainBuilder.class, long.class, MessageChain.class));
            i++;
        }
    }

    public static String getNowDate() {
        Calendar rightNow = Calendar.getInstance();
        int year = rightNow.get(Calendar.YEAR);
        int month = rightNow.get(Calendar.MONTH)+1;
        int day = rightNow.get(Calendar.DAY_OF_MONTH);
        return year + "-" + month + "-" + day;
    }
}

 */