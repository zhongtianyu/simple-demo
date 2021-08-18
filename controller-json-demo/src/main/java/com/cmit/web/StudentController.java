package com.cmit.web;

import com.cmit.model.Student;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2020/8/29 20:56
 */
@RestController
public class StudentController {

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String student(@RequestBody Student student) {
        return student.toString();
    }
}
