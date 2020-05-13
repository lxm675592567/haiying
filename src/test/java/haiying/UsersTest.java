package haiying;


import haiying.service.auth.base.AppSetting;
import haiying.service.auth.base.WxEndpoint;
import haiying.service.auth.domain.User;
import haiying.service.auth.service.UserService;
import haiying.service.auth.user.SessionKey;
import haiying.service.auth.user.Users;
import haiying.util.CommUtil;
import jdk.internal.org.objectweb.asm.tree.MultiANewArrayInsnNode;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.Assert.assertNotNull;

/**
 * @borball on 11/7/2016.
 */
public class UsersTest {

	private final UserService UserService;

	public UsersTest(UserService UserService) { // <3>
		this.UserService = UserService;
	}


	@Test
	@Ignore
	public void testCode2Session() {
//		SessionKey sessionKey = Users.with(new AppSetting("appid", "screctkey"))
//				.code2Session("011RedXY1NegvX0eLWXY1vVfXY1RedXu");
//		assertNotNull(sessionKey);
//		String code ="1213";
//		String url = WxEndpoint.get("url.user.code2session")+code;
//		System.out.println(url);
//		User user = new User();
//		user.setLoginType(1);
//		User user = UserService.findByOpenId("openId");
//		int loginType = user.getLoginType();
//		if(!Objects.isNull(loginType)){
//			//如果没有,注册,并返回一个注册标识给前端
//			System.out.println("111");
//		}
String time = "2018年3月22日";
		time.replace("年","-");
		System.out.println(time);
	}

	public static void main(String[] args) {
//		User user = new User();
//		user.setLoginType(1);
//		int loginType = user.getLoginType();
//		if(!Objects.isNull(loginType)){
//			//如果没有,注册,并返回一个注册标识给前端
//			System.out.println("111");
//		}
		String time = "2018年3月22日";
		String years = time.replaceAll("(?:年|月)", "-");
		String data = years.replace("日", "");
		System.out.println(data);
	}
}
