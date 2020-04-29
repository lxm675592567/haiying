package haiying;


import haiying.service.auth.base.AppSetting;
import haiying.service.auth.base.WxEndpoint;
import haiying.service.auth.user.SessionKey;
import haiying.service.auth.user.Users;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @borball on 11/7/2016.
 */
public class UsersTest {

	@Test
	@Ignore
	public void testCode2Session() {
//		SessionKey sessionKey = Users.with(new AppSetting("appid", "screctkey"))
//				.code2Session("011RedXY1NegvX0eLWXY1vVfXY1RedXu");
//		assertNotNull(sessionKey);
		String code ="1213";
		String url = WxEndpoint.get("url.user.code2session")+code;
		System.out.println(url);
	}
}
