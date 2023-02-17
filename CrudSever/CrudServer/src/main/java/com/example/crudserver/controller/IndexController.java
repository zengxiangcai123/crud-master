package com.example.crudserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crudserver.controller.dto.RequestParam;
import com.example.crudserver.controller.dto.ResponseDto;
import com.example.crudserver.dao.StudentMapper;
import com.example.crudserver.entity.Student;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/crud")
public class IndexController {
    @Resource
    private StudentMapper studentMapper;

    @GetMapping("/test")
    public String test(){
        return "hello world";
    }

    @PostMapping("/list")
    public ResponseDto<?> list(@RequestBody RequestParam param){
        if(param.pageNum==null || param.pageSize==null){
            return new ResponseDto<>(false,"缺少pageNum或者pageSize字段",null);
        }
        Page<Student> page = new Page<>(param.pageNum,param.pageSize);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        if(param.keyword!=null)
            queryWrapper.like("name", param.keyword);
        return new ResponseDto<>(true,null, studentMapper.selectPage(page, queryWrapper));
    }
    @PostMapping("/add")
    public ResponseDto<?> add(@RequestBody Student student){
        if(student.getName()==null) return new ResponseDto<>(false,"姓名不能为空！",null);
        studentMapper.insert(student);
        return new ResponseDto<>(true, null, "添加成功！");
    }
    @GetMapping("/delete")
    public ResponseDto<?> delete(@org.springframework.web.bind.annotation.RequestParam("id") Long id)
    {
        if(id==null) return new ResponseDto<>(false,"学号参数缺少！",null);
        studentMapper.deleteById(id);
        return new ResponseDto<>(true, null, "删除成功！");
    }

    @PostMapping("/modify")
    public ResponseDto<?> modify(@RequestBody Student student){
        if(student.getId()==null || student.getName()==null) return new ResponseDto<>(false,"学号，姓名不能为空！",null);
        studentMapper.updateById(student);
        return new ResponseDto<>(true, null, "修改成功！");
    }
}
