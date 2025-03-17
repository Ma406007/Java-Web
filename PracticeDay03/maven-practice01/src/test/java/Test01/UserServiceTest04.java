package Test01;

import UserService.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//覆盖率代码(待完善)

@DisplayName("用户信息测试类")
public class UserServiceTest04 {
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
    }

    //测试获取性别--null
    @Test
    @DisplayName("获取性别--null值")
    public void testGetGender1() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.getGender(null);
        });
    }

    //测试获取性别--长度不足
    @Test
    @DisplayName("获取性别--长度不足")
    public void testGetGender2() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.getGender("100");
        });
    }

    //测试获取性别--超出长度
    @Test
    @DisplayName("获取性别--长度超出")
    public void testGetGender3() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.getGender("1000002000100110111100000");
        });
    }

    //测试获取性别--正常:男
    @Test
    @DisplayName("获取性别--正常男性身份证")
    public void testGetGender4() {
        String gender = userService.getGender("100000200010011011");
        Assertions.assertEquals("男", gender);
    }

    //测试获取性别--正常:女
    @Test
    @DisplayName("获取性别--正常女性身份证")
    public void testGetGender5() {
        String gender = userService.getGender("100000200010011021");
        Assertions.assertEquals("女", gender);
    }
}
