/*!
 * Copyright © 2022 uyawer. All rights Reserved.
 */

package com.uyawer.portal.controller;

import com.uyawer.portal.constants.Page;
import com.uyawer.portal.constants.type.BloodType;
import com.uyawer.portal.constants.type.GenderType;
import com.uyawer.portal.helper.EmployeesHelper;
import com.uyawer.portal.model.entity.EmployeeEntity;
import com.uyawer.portal.service.EmployeeService;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(path = {"/admin/manage/employees"})
public class AdminManageEmployeeController {

    private final EmployeeService employeeService;

    public AdminManageEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Transactional(readOnly = true)
    @GetMapping
    public ModelAndView get() {
        // 全ての従業員情報を取得
        List<EmployeeEntity> entityList = employeeService.findAll();
        // 画面表示
        return new ModelAndView(Page.ADMIN_MANAGE_EMPLOYEES)
            .addObject("genderTypeList", Stream.of(GenderType.values()).toList())
            .addObject("bloodTypeList", Stream.of(BloodType.values()).toList())
            .addObject("employeeList", EmployeesHelper.convertDtoList(entityList));
    }
}
