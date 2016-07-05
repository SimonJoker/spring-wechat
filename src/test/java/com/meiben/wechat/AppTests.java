package com.meiben.wechat;

import com.meiben.wechat.common.utils.HttpUtil;
import org.junit.Test;

import java.io.IOException;

//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class AppTests {
//    private MockMvc mockMvc;
//
//    @SuppressWarnings("SpringJavaAutowiringInspection")
//    @Autowired
//    protected WebApplicationContext wac;
//
//    @Before
//    public void setup() {
//        this.mockMvc = webAppContextSetup(this.wac).build();
//    }

    @Test
    public void simple() throws IOException {
//        AccessToken token =
//                AccessTokenUtil.getAccessToken(this.getClass().getResource("/").getFile().toString());
//
//        String menu = JSONObject.fromObject(WxMenuSetting.initMenu()).toString();
//        System.out.println("menu--:\n"+menu);
//        try {
//            int result = WxMenuSetting.createMenu(menu);
//            if (result != 0){
//                System.out.println("if");
//                token = AccessTokenUtil
//                        .requestAccessToken(this.getClass().getResource("/").getFile().toString());
//                WxMenuSetting.createMenu(menu);
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        String url
                ="https://api.weixin.qq.com/cgi-bin/user/info?access_token=qS-TubBVln1spepaKI58Wfpca3CzK9Mh1oHOlyjBwI3b4iRDojyuYznegg_GPNkkc6nRRUAUUSY3OS1nc4YgiMJ-sCrzlOWeRyTqimfW0T2Vt2kheYe0UnDG4xxJoZWfRYCjAFALSW&openid=otUVXv-Ui_Ky_XZHEaIMoHs4s2f0&lang=zh_CN";
        System.out.println(HttpUtil.doGetStr(url));


    }
}
