package haiying.service.auth.user;


import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.util.JsonMapper;
import haiying.service.auth.AppWxClientFactory;
import haiying.service.auth.base.AppSetting;
import haiying.service.auth.base.WxEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户操作相关
 */
public class Users {

    private static Logger logger = LoggerFactory.getLogger(Users.class);

    private WxClient wxClient;

    public static Users defaultUsers() {
        return with(AppSetting.defaultSettings());
    }

    public static Users with(AppSetting appSetting) {
        Users cards = new Users();
        cards.setWxClient(AppWxClientFactory.getInstance().with(appSetting));
        return cards;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    public SessionKey code2Session(String code) {
        String url = WxEndpoint.get("url.user.code2session")+code;
        //String urls = "https://api.weixin.qq.com/sns/jscode2session?appid=wxbb7e8dcb7483b4c6&secret=001083ebd06b7e44f48aa998095e802f&"+"js_code="+code+"&grant_type=authorization_code";
        String sessionKey = wxClient.get(String.format(url, wxClient.getClientId(), wxClient.getClientSecret(), code));
        logger.debug("code to session key: {}", sessionKey);
        return JsonMapper.nonEmptyMapper().fromJson(sessionKey, SessionKey.class);
    }

}
