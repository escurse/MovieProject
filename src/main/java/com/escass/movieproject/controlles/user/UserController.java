package com.escass.movieproject.controlles.user;


import com.escass.movieproject.entities.user.EmailTokenEntity;
import com.escass.movieproject.entities.user.UserEntity;
import com.escass.movieproject.results.CommonResult;
import com.escass.movieproject.results.Result;
import com.escass.movieproject.services.user.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // region 회원가입
    @RequestMapping(value = "/register", method = RequestMethod.GET, produces =
            MediaType.TEXT_HTML_VALUE)
    public ModelAndView getRegister() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/register");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String postRegister(HttpServletRequest request, UserEntity user

    ) throws MessagingException {

        Result result = this.userService.register(request, user);
        JSONObject response = new JSONObject();
        response.put(Result.NAME, result.nameToLower());
        return response.toString();

    }
// endregion

    // region 이메일 토큰
    @RequestMapping(value = "/validate-email-token", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getValidateEmailToken(EmailTokenEntity emailToken) {
        Result result = this.userService.validateEmailToken(emailToken);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(Result.NAME, result.nameToLower());
        modelAndView.setViewName("user/validateEmailToken");

        return modelAndView;
    }
// endregion

    // region 로그인
    @RequestMapping(value = "login",method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getLogin() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("user/login");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody

    public String postLogin(UserEntity user, HttpSession session) {
        Result result = this.userService.login(user);
        JSONObject response = new JSONObject();
        // 로그인 성공 시 세션에 사용자 정보 추가
        if (result == CommonResult.SUCCESS) {
            session.setAttribute("user", user);
        }
        // JSON 응답 생성
        response.put(Result.NAME, result.nameToLower());
        response.put(Result.NAMES, user.getUsNum());


        return response.toString();
    }

// endregion

    // region 마이페이지
    @RequestMapping(value = "/myPage", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getMyPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/myPage");
        return modelAndView;
    }
    // endregion

    // region 아이디 / 닉네임 중복 검사
    @RequestMapping(value = "/check-duplicate-id", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String checkDuplicateId(@RequestParam("user") String user) {
        Result result = userService.checkDuplicateUser(user);
        JSONObject response = new JSONObject();
        response.put(Result.NAME, result.nameToLower());
        return response.toString();
    }

    @RequestMapping(value = "/check-duplicate-nickname", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String checkDuplicateNickname(@RequestParam("nickname") String nickname) {
        Result result = userService.checkDuplicateNickname(nickname);
        JSONObject response = new JSONObject();
        response.put(Result.NAME, result.nameToLower());
        return response.toString();
    }
// endregion

    // region 아이디 / 비밀번호 찾기
    @RequestMapping(value = "find-id", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getFindId(UserEntity user) {
        Result result = userService.findUserId(user);
        ModelAndView modelAndView = new ModelAndView();
        if (result == CommonResult.SUCCESS) {
            modelAndView.addObject("name", user.getUsName());
            modelAndView.addObject("id", user.getUsId());
        }
        modelAndView.setViewName("user/find-id");
        return modelAndView;
    }

    @RequestMapping(value = "find-id", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String postFindId(UserEntity user) {

        Result result = userService.findUserId(user);
        JSONObject response = new JSONObject();
        if (result == CommonResult.SUCCESS) {
            response.put("name", user.getUsName());
            response.put("id", user.getUsId());
        }
        response.put(Result.NAME, result.nameToLower());
        return response.toString();
    }



    @RequestMapping(value = "find-password", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getFindPassword() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/find-password");
        return modelAndView;
    }

    @RequestMapping(value = "find-password", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String postFindPassword(UserEntity user, HttpServletRequest request, @RequestParam(value="usEmail", required = false) String usEmail) throws MessagingException {
        Result result = userService.findUserPassword(user);
        this.userService.provokeRecoverPassword(request, usEmail);
        JSONObject response = new JSONObject();
        response.put(Result.NAME, result.nameToLower());

        return response.toString();
    }

    @RequestMapping(value = "find-password-result", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getFindPasswordResult() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/find-password-result");
        return modelAndView;
    }


    @RequestMapping(value = "/recover-email", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String recoverEmail(UserEntity user) {
        Result result = this.userService.recoverEmail(user);
        JSONObject response = new JSONObject();
        response.put(Result.NAME, result.nameToLower());
        if (result == CommonResult.SUCCESS) {
            response.put("email", user.getUsEmail());
        }
        return response.toString();
    }

    @RequestMapping(value = "/find-password-result",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ModelAndView getRecoverPassword(@RequestParam(value = "userEmail", required = false)String userEmail,
                                           @RequestParam(value = "key", required = false) String key) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userEmail", userEmail);
        modelAndView.addObject("key", key);
        modelAndView.setViewName("user/find-password-result");
        return modelAndView;
    }


    // endregion
}
