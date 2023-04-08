package com.ch.controller;

import com.ch.domain.PageHandler;
import com.ch.domain.SearchCondition;
import com.ch.dto.BrandDto;
import com.ch.service.brand.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
//    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    BrandService brandService;

    public AdminController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/home")
    public String adminHome() {
        log.info("관리자 홈 이동");
        return "/admin/adminHome";
    }

    /* 상품 등록 페이지 접속 */
    @GetMapping("/productManage")
    public String productManage() {
        log.info("상품 등록 페이지 접속");
        return "/admin/productManageForm";
    }

    /* 상품 관리 페이지 접속 */
    @GetMapping("/productEnroll")
    public String productEnroll() {
        log.info("상품 등록 페이지 접속");
        return "/admin/productEnrollForm";
    }

    /* 브랜드 등록 페이지 접속 */
    @GetMapping("/brandEnroll")
    public String brandEnrollgGet() {
        log.info("Get:brandEnroll");
        return "/admin/brandEnrollForm";
    }

    @PostMapping("/brandEnroll")
    public String brandEnrollPost(BrandDto brandDto, RedirectAttributes rttr) {
        log.info("브랜드 등록 페이지 접속");
        log.info("brand={}", brandDto);

        if (brandService.check(brandDto.getBrandName()) == 1) {
            log.info("이미 존재합니다.");
            return "redirect:/admin/brandEnroll";
        }
        brandService.enroll(brandDto);  // 중복도 확인해야 한다.
        rttr.addFlashAttribute("enroll_result", brandDto.getBrandName());
        return "redirect:/admin/brandManage";
    }

    /* 작가 관리 페이지 접속 */

    @GetMapping("/brandManage")
    public String brandManage(@ModelAttribute SearchCondition sc, Model model) {
        log.info("브랜드 관리 페이지 접속");

        int totalCnt = brandService.getSearchResultCnt(sc);
        PageHandler ph = new PageHandler(totalCnt, sc);
        List<BrandDto> list = brandService.getsearchResultPage(sc);
        model.addAttribute("list", list);
        model.addAttribute("ph", ph);

        return "/admin/brandManageForm";
    }

    @GetMapping("/brandDetail")
    public String authorGetInfoGET(Integer brandId, Integer page, Integer pageSize, Model model) {

        log.info("GET: /admin/brandDetail");
        model.addAttribute("brandId", brandId);
        return "/admin/brandDetail";
    }

}
