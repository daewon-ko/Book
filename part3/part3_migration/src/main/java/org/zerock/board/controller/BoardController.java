package org.zerock.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.service.board.BoardService;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    @GetMapping("/")
    public String index() {
        return "redirect:/guestbook/list";
    }


    @GetMapping("/list")
    public void list(@ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, Model model) {
        log.info("list.........." + pageRequestDTO);
        model.addAttribute("result", service.getList(pageRequestDTO));
        log.info("PageResultDTO: {}", service.getList(pageRequestDTO));
    }

    @PostMapping("/register")
    public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes) {
        log.info("dto.." + dto);

        //새로 추가된 엔티티의 번호
        Long bno = service.register(dto);

        redirectAttributes.addFlashAttribute("msg", bno);

        return "redirect:/guestbook/list";
    }

    @GetMapping("/register")
    public void register() {
        log.info("register get...");
    }

    @GetMapping({"/read", "/modify"})
    public void read(long bno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
        log.info("bno :" + bno);
        BoardDTO dto = service.read(bno);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/remove")
    public String remove(long bno, RedirectAttributes redirectAttributes) {
        log.info("bno: " + bno);
        service.remove(bno);
        redirectAttributes.addFlashAttribute("msg", bno);
        return "redirect:/board/list";
    }

    @PostMapping("/modify")
    public String modify(BoardDTO dto, @ModelAttribute PageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {
        log.info("post modify...");
        log.info("dto:"+dto);
        service.modify(dto);
        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("bno", dto.getBno());
        return "redirect:/board/read";
    }
}
