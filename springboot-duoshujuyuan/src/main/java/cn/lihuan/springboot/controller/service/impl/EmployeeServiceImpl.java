package cn.lihuan.springboot.controller.service.impl;

import cn.lihuan.springboot.controller.entity.EmployeeEntity;
import cn.lihuan.springboot.controller.mapper.EmployeeMapper;
import cn.lihuan.springboot.controller.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lihuan
 * @since 2021-04-30
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, EmployeeEntity> implements EmployeeService {

}
