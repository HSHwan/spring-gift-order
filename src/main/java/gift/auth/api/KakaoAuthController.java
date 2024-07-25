package gift.auth.api;

import gift.auth.vo.KakaoProperties;
import gift.member.application.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/oauth/kakao")
public class KakaoAuthController {

    private final MemberService memberService;
    private final KakaoProperties kakaoProperties;

    public KakaoAuthController(MemberService memberService,
                               KakaoProperties kakaoProperties) {
        this.memberService = memberService;
        this.kakaoProperties = kakaoProperties;
    }

    @GetMapping
    public String redirectKakaoLogin() {
        return "redirect:" + kakaoProperties.getKakaoAuthUrl();
    }

    @GetMapping("/callback")
    public String handleKakaoCallback(RedirectAttributes redirectAttributes,
                                      @RequestParam("code") String code) {
        redirectAttributes.addFlashAttribute("tokenResponse", memberService.authenticate(code));
        return "redirect:/members/order";
    }

}
