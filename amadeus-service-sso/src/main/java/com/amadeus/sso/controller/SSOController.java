package com.amadeus.sso.controller;

import com.amadeus.api.sso.SSOApi;
import com.amadeus.framework.domain.admin.AdminInfo;
import com.amadeus.framework.domain.admin.AdminLoginResult;
import com.amadeus.framework.domain.student.StudentInfo;
import com.amadeus.framework.domain.student.StudentLogin;
import com.amadeus.framework.domain.student.StudentLoginResult;
import com.amadeus.framework.domain.teacher.TeacherLogin;
import com.amadeus.framework.domain.teacher.TeacherLoginResult;
import com.amadeus.framework.model.response.CommonCode;
import com.amadeus.framework.model.response.ResponseResult;
import com.amadeus.framework.model.response.SendCodeResult;
import com.amadeus.framework.utils.*;
import com.amadeus.sso.service.AdminService;
import com.amadeus.sso.service.StudentService;
import com.amadeus.sso.service.TeacherService;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/sso")
public class SSOController implements SSOApi {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private JavaMailSender sender;

    private static final String KEY = "TGElMjB5b2hkYSUyMHN0YXNlbGxh";

    /**
     * 学生登录
     *
     * @param studentLogin 学生登录信息
     * @return ResponseResult
     */
    @Override
    @GetMapping("/studentLogin")
    public StudentLoginResult studentLogin(@ModelAttribute("studentLogin") @Validated StudentLogin studentLogin) {
        if (studentLogin.getTelephoneNumber() != null) {
            Optional<StudentLogin> loginOptional = studentService.findByTelephoneNumber(studentLogin.getTelephoneNumber());
            if (loginOptional.isPresent()) {
                StudentLogin student = loginOptional.get();
                if (student.getAccountState() != 1) {
                    return new StudentLoginResult(CommonCode.ACCOUNT_IS_FORBIDDEN, null);
                } else if (student.getActivationState() != 1) {
                    //跳转到激活页面
                    return new StudentLoginResult(CommonCode.ACCOUNT_NOT_ACTIVATE, null);
                } else if (encoder.matches(studentLogin.getPassword(), student.getPassword())) {
                    return new StudentLoginResult(CommonCode.SUCCESS, student);
                }
            }
        } else if (studentLogin.getEmail() != null) {
            Optional<StudentLogin> loginOptional = studentService.findByEmail(studentLogin.getEmail());
            if (loginOptional.isPresent()) {
                StudentLogin student = loginOptional.get();
                if (student.getAccountState() != 1) {
                    return new StudentLoginResult(CommonCode.ACCOUNT_IS_FORBIDDEN, null);
                } else if (student.getActivationState() != 1) {
                    //跳转到激活页面
                    return new StudentLoginResult(CommonCode.ACCOUNT_NOT_ACTIVATE, null);
                } else if (encoder.matches(studentLogin.getPassword(), student.getPassword())) {
                    return new StudentLoginResult(CommonCode.SUCCESS, student);
                }
            }
        }
        return new StudentLoginResult(CommonCode.FAIL, null);
    }

    @Override
    @GetMapping("/teacherLogin")
    public TeacherLoginResult teacherLogin(@Validated @ModelAttribute("teacherLogin") TeacherLogin teacherLogin) {
        if (teacherLogin.getTelephoneNumber() != null) {
            Optional<TeacherLogin> loginOptional = teacherService.findByTelephoneNumber(teacherLogin.getTelephoneNumber());
            if (loginOptional.isPresent()) {
                TeacherLogin teacher = loginOptional.get();
                if (teacher.getAccountState() != 1) {
                    return new TeacherLoginResult(CommonCode.ACCOUNT_IS_FORBIDDEN, null);
                } else if (teacher.getActivationState() != 1) {
                    //跳转到激活页面
                    return new TeacherLoginResult(CommonCode.ACCOUNT_NOT_ACTIVATE, null);
                } else if (encoder.matches(teacherLogin.getPassword(), teacher.getPassword())) {
                    return new TeacherLoginResult(CommonCode.SUCCESS, teacher);
                }
            }
        }
        return new TeacherLoginResult(CommonCode.FAIL, null);
    }

    /**
     * 用户注册
     *
     * @param studentLogin 用户登录信息
     * @return ResponseResult
     */
    @Override
    @PostMapping("/studentRegister")
    public ResponseResult studentRegister(StudentLogin studentLogin) {
        if (studentLogin.getTelephoneNumber() != null) {
            studentLogin.setStudentId(new UUIDUtil().generateUUID());
            studentLogin.setPassword(new BCryptPasswordUtil().encodePassword(encoder, studentLogin.getPassword()));
            studentLogin.setActivationState(1);
            studentLogin.setAccountState(1);
            StudentLogin saveStudentLogin = studentService.saveStudentLogin(studentLogin);
            if (saveStudentLogin != null) {
                //注册成功后向student_info表中插入一条数据
                StudentInfo studentInfo = new StudentInfo();
                studentInfo.setInfoId(new UUIDUtil().generateUUID());
                studentInfo.setStudentName("未命名");
                studentInfo.setCertificateType(1);
                studentInfo.setMemberLevel(1);
                studentInfo.setUserBalance(BigDecimal.valueOf(0.00));
                studentInfo.setUserPoint(500);
                studentInfo.setStudentLogin(saveStudentLogin);
                StudentInfo saveStudentInfo = studentService.saveStudentInfo(studentInfo);
                if (saveStudentInfo != null) {
                    return new ResponseResult(CommonCode.SUCCESS);
                }
            }
            return new ResponseResult(CommonCode.FAIL);
        } else if (studentLogin.getEmail() != null) {
            studentLogin.setStudentId(new UUIDUtil().generateUUID());
            studentLogin.setPassword(new BCryptPasswordUtil().encodePassword(encoder, studentLogin.getPassword()));
            studentLogin.setActivationState(1);
            studentLogin.setAccountState(1);
            StudentLogin saveStudentLogin = studentService.saveStudentLogin(studentLogin);
            if (saveStudentLogin != null) {
                //注册成功后向student_info表中插入一条数据
                StudentInfo studentInfo = new StudentInfo();
                studentInfo.setInfoId(new UUIDUtil().generateUUID());
                studentInfo.setStudentName("未命名");
                studentInfo.setCertificateType(1);
                studentInfo.setMemberLevel(1);
                studentInfo.setUserBalance(BigDecimal.valueOf(0.00));
                studentInfo.setUserPoint(500);
                studentInfo.setStudentLogin(saveStudentLogin);
                StudentInfo saveStudentInfo = studentService.saveStudentInfo(studentInfo);
                if (saveStudentInfo != null) {
                    return new ResponseResult(CommonCode.SUCCESS);
                }
            }
            return new ResponseResult(CommonCode.FAIL);
        }
        return new ResponseResult(CommonCode.INPUT_ACCOUNT_IS_NULL);
    }

    /**
     * 查询账号是否存在
     *
     * @param studentLogin 用户信息
     * @return ResponseResult
     */
    @Override
    @GetMapping("/studentExist")
    public ResponseResult studentExist(@ModelAttribute("studentLogin") @Validated StudentLogin studentLogin) {
        boolean isExist;
        if (studentLogin.getTelephoneNumber() != null && !studentLogin.getTelephoneNumber().equals("")) {
            isExist = studentService.existsStudentLoginByTelephoneNumber(studentLogin.getTelephoneNumber());
        } else {
            isExist = studentService.existsStudentLoginByEmail(studentLogin.getEmail());
        }
        if (isExist) {
            return new ResponseResult(CommonCode.ACCOUNT_EXIST);
        }
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 管理员登录
     *
     * @param adminInfo 管理员信息
     * @return adminInfo
     */
    @Override
    @GetMapping("/adminLogin")
    public AdminLoginResult adminLogin(@Validated @ModelAttribute("adminInfo") AdminInfo adminInfo) {
        if (adminInfo.getTelephoneNumber() != null && !adminInfo.getTelephoneNumber().equals("")) {
            Optional<AdminInfo> optional = adminService.findByTelephoneNumber(adminInfo.getTelephoneNumber());
            if (optional.isPresent()) {
                if (new BCryptPasswordUtil().matchPassword(encoder, adminInfo.getPassword(), optional.get().getPassword())) {
                    if (optional.get().getAccountState() == 1) {
                        if (optional.get().getIdentity() == 1) {
                            return new AdminLoginResult(CommonCode.CAMPUS_ADMIN_IDENTITY, optional.get());
                        }
                        return new AdminLoginResult(CommonCode.SUPER_ADMIN_IDENTITY, optional.get());
                    }
                    return new AdminLoginResult(CommonCode.ACCOUNT_IS_FORBIDDEN, null);
                }
                return new AdminLoginResult(CommonCode.VERIFICATION_CODE_NOT_TRUE, null);
            }
            return new AdminLoginResult(CommonCode.ACCOUNT_NOT_EXIST, null);
        }
        return new AdminLoginResult(CommonCode.EMAIL_IS_NULL, null);
    }

    /**
     * 向邮箱发送验证码
     *
     * @param email   用户邮箱
     * @param session session
     * @return ResponseResult
     */
    @Override
    @GetMapping("/sendEmailCode")
    public ResponseResult sendEmailCode(@Validated @RequestParam("email") String email, HttpSession session) {
        int verificationCode = new VerificationCodeUtil().sendEmailVerificationCode(sender, email);
        String token = KEY + email + verificationCode;
        session.setAttribute("token", new BCryptPasswordUtil().encodePassword(encoder, token));
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 向用户手机发送验证码
     *
     * @param telephoneNumber 手机号
     * @return ResponseResult
     */
    @Override
    @GetMapping("/sendSingleSms")
    public SendCodeResult sendSingleSms(@Validated @RequestParam("telephoneNumber") String telephoneNumber) {
        int verificationCode = new VerificationCodeUtil().generateVerificationCode();
        String token = KEY + telephoneNumber + verificationCode;
        SmsSingleSenderResult result = new QcloudSmsUtil().sendSingleSms(verificationCode, telephoneNumber);
        if (result.result == 0 && result.errMsg.equals("OK")) {
            return new SendCodeResult(CommonCode.SUCCESS, token);
        }
        return new SendCodeResult(CommonCode.FAIL, null);
    }

    /**
     * 添加管理员
     *
     * @param adminInfo 管理员信息
     * @return ResponseResult
     */
    @Override
    @PostMapping("/saveAdmin")
    public ResponseResult saveAdmin(@Validated @ModelAttribute("adminInfo") AdminInfo adminInfo) {
        adminInfo.setAdminId(new UUIDUtil().generateUUID());
        String randomPassword = new RandomPasswordUtil().generateString(8);
        adminInfo.setPassword(new BCryptPasswordUtil().encodePassword(encoder, randomPassword));
        adminInfo.setIdentity(1);
        adminInfo.setAccountState(1);
        AdminInfo save = adminService.save(adminInfo);
        if (save != null) {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("804236345@qq.com");
            simpleMailMessage.setTo(adminInfo.getEmail());
            simpleMailMessage.setSubject("院校管理员账号");
            simpleMailMessage.setText("账号:" + adminInfo.getEmail() + " 密码:" + randomPassword);
            new VerificationCodeUtil().sendEmailAndPassword(simpleMailMessage, sender);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

}
