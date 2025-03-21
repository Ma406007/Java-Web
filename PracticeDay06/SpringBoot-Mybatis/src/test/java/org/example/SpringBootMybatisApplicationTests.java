package org.example;

import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/*
  在创建出来的SpringBoot工程中的src下的test目录下已经自动帮我们创建好了测试类,并且在测试类上已经添加了注解@SpringBootTest,代表该测试类已经与SpringBoot整合
  该测试类在运行时会自动通过引导类加载Spring的环境(IOC容器),我们要测试某个bean对象,就可以直接通过@Autowired注解直接将其注入进行,然后就可以测试了
*/

//注意:测试类所在包需要与引导类所在包相同
@SpringBootTest//在单元测试时会进行SpringBoot环境的加载
class SpringBootMybatisApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testFindAll(){
        List<User> userList = userMapper.selectAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testDeleteById() {
        userMapper.deleteById(1);
    }

    @Test
    public void testInsert() {
        userMapper.insert(new User(1, "zhouyu", "123456", "周瑜", 20));
    }

    @Test
    public void testUpdate() {
        userMapper.updateById(new User(3, "gaoyuanyuan", "123456", "高圆圆", 23));
    }

    @Test
    public void testSelect() {
        User user = new User(null, "zhouyu", "123456", "周瑜", 23);
        User selectUser = userMapper.select(user.getUsername(), user.getPassword());
        System.out.println(selectUser);
    }
}