package org.example.service.Impl;

import org.example.mapper.EmpLogMapper;
import org.example.pojo.EmpLog;
import org.example.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;

    /**
     * 注解@Transactional当中的第二个属性propagation是用来配置事务的传播行为的
     * 所谓事务的传播行为指的就是在A方法运行的时候
     * 首先会开启一个事务,在A方法当中又调用了B方法,B方法自身也具有事务,那么B方法在运行的时候,到底是加入到A方法的事务当中来还是B方法在运行的时候新建一个事务
     * 这个就涉及到了事务的传播行为
     * */

    /**
     * - REQUIRED：大部分情况下都是用该传播行为即可
     * - REQUIRES_NEW：当我们不希望事务之间相互影响时，可以使用该传播行为。比如：下订单前需要记录日志，不论订单保存成功与否，都需要保证日志记录能够记录成功
     * */
    @Transactional(propagation = Propagation.REQUIRES_NEW)//Propagation.REQUIRES_NEW:不论是否有事务都创建新事务,运行在一个独立的事务中
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}
