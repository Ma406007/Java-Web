package Test01;

import UserService.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserServiceTest01 {
    @Test
    public void GetAgeTest01() {
        Integer age = new UserService().getAge("110002200505091218");
        System.out.println(age);
    }

    @Test
    public void GetGenderTest01() {
        String gender = new UserService().getGender("110002200505091218");
        System.out.println(gender);
    }

    /*
      JUnit提供了一些辅助方法用来帮我们确定被测试的方法是否按照预期的效果正常工作,这种方式称为断言
      断言方法:
        1.assertEquals(Object exp, Object act, String msg) 检查两个值是否相等,不相等就报错
        2.assertNotEquals(Object unexp, Object act, String msg) 检查两个值是否不相等,相等就报错
        3.assertNull(Object obj, String msg) 检查给定的对象是否为null,不为null就报错
        4.assertNotNull(Object obj, String msg) 检查给定的对象是否不为null,为null就报错
        5.assertTrue(boolean condition, String msg) 检查给定的条件是否为true,不为true就报错
        6.assertFalse(boolean condition, String msg) 检查给定的条件是否为false,不为false就报错
        7.assertSame(Object exp, Object act, String msg) 检查两个对象是否是同一个对象,不是同一个对象就报错
    */
    @Test
    public void GetAgeTestWithAssertion01() {
        Integer age = new UserService().getAge("110002200505091218");
        Assertions.assertNotEquals(18, age, "两个值相等");
        //String s1 = new String("Hello");
        //String s2 = "Hello";
        //Assertions.assertSame(s1, s2, "不是同一个对象引用");
    }

    @Test
    public void GetGenderTestWithAssertion01() {
        String gender = new UserService().getGender("612429198904201611");
        Assertions.assertEquals("男", gender);
    }

    @Test
    public void GetGenderTestWithAssertion02() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new UserService().getGender("null");
        });
    }

    /*
      JUnit中还提供了一些注解,还增强其功能,常见的注解有以下几个:
        1.@Test 测试类中的方法用它修饰才能成为测试方法才能启动执行 用来单元测试
        2.@BeforeEach 用来修饰一个实例方法,该方法会在每一个测试方法执行之前执行一次 用来初始化资源(准备工作)
        3.@AfterEach 用来修饰一个实例方法,该方法会在每一个测试方法执行之后执行一次 用来释放资源(清理工作)
        4.@BeforeAll 用来修饰一个静态方法,该方法会在所有测试方法执行之前执行一次 用来初始化资源(准备工作)
        5.@AfterAll 用来修饰一个静态方法,该方法会在所有测试方法执行之后执行一次 用来释放资源(清理工作)
          (以上见UserServiceTest02)
        6.@ParameterizedTest 参数化测试的注解(可以让单个测试运行多次,每次运行时仅参数不同) 用了该注解就不需要@Test注解了
        7.@ValueSource 用来提供参数化测试的数据 与参数化测试注解配合使用
        8.@DisplayName 指定测试类和测试方法显示的名称(默认为类名和方法名)
          (以上见UserServiceTest03)
        思考:在maven项目中,test目录存放单元测试的代码是否可以在main目录中编写单元测试? 答:可以但是不规范
    */
}
