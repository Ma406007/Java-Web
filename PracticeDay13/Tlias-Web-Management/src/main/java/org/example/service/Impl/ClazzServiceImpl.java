package org.example.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.ClazzMapper;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;
import org.example.pojo.PageResult;
import org.example.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    /**
     * 班级列表查询
     * */
    @Override
    public PageResult pagelist(ClazzQueryParam clazzQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());

        //2.执行查询
        List<Clazz> clazzList = clazzMapper.pagelist(clazzQueryParam);
        Page<Clazz> p = (Page<Clazz>)clazzList;

        //3.封装结果
        return new PageResult(p.getTotal(), clazzList);
    }

    /**
     * 添加班级
     * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert(Clazz clazz) throws Exception {
        try {
            //1.补全信息
            clazz.setCreateTime(LocalDateTime.now());
            clazz.setUpdateTime(LocalDateTime.now());

            //2.保存班级信息
            clazzMapper.insert(clazz);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 根据ID查询班级信息
     * */
    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }

    /**
     * 修改班级信息
     * */
    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    /**
     * 删除班级信息
     * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);
    }
}
