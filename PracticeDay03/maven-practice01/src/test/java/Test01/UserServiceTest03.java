package Test01;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import UserService.UserService;

@DisplayName("测试-学生业务操作")
public class UserServiceTest03 {
    @DisplayName("测试-获取年龄")
    @Test
    public void testGetAge(){
        Integer age = new UserService().getAge("110002200505091218");
        System.out.println(age);
    }

    @DisplayName("测试-获取性别")
    @Test
    public void testGetGender(){
        String gender = new UserService().getGender("612429198904201611");
        System.out.println(gender);
    }

    @DisplayName("测试-获取性别3")
    @ParameterizedTest //让该测试可以执行多次,每次执行时仅参数不同
    @ValueSource(strings = {"612429198904201611","612429198904201631","612429198904201626"}) //提供测试数据
    public void testGetGender3(String idcard) {
        String gender = new UserService().getGender(idcard);
        System.out.println(gender);
    }
}
