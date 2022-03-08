package com.lijian.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lijian.service.CheckItemService;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass  = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

}
