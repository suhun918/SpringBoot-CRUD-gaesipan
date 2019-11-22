package com.cos.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.crud.model.Post;
import com.cos.crud.repository.PostRepository;

@Controller
//이거 걸어두면 기본이 http://localhost:8080/post/........이다
@RequestMapping("/post")
public class PostController {

	@Autowired // DI(의존성 주입) 됐음 : 이미 있는 객체에 접근 한것
	private PostRepository postRepository;

	// 위에 기본 루트 걸어놨으니까 /만 걸면
	// http://localhost:8080/post/로 이동
	// Model은 데이터를 Controller에서 Presentation(jsp 파일) 계층까지
	// 들고간다.

	// GET =>http://localhost:8080/post
	// http://localhost:8080/post/
	@GetMapping("")
	public String post(Model model) {
		List<Post> posts = postRepository.findAll();
		model.addAttribute("posts", posts);// 오브젝트타입
		// webapp/WEB-INF/views/post/list.jsp
		return "post/list";
	}

	// GET => http://localhost:8080/post/writeForm
	@GetMapping("/writeForm")
	public String writeForm() {
		return "post/writeForm";
	}

	// POST =>http://localhost:8080/post/update
	@PostMapping("/update")
	public String update(Post post) { // param, form
		try {
			postRepository.update(post);
			// redirect 는 함수를 호출한다.
			// why? 그냥 페이지로 이동하면 데이터가 담겨있지 않아서.
			return "redirect:/post";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/post";
	}

	// 원래는 post를 써야하지만 지금은 간단한 거 테스트 중이니까 Get방식을 사용한다
	// GET =>http://localhost:8080/post/delete/id값
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		try {
			postRepository.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/post";
	}

	// POST =>http://localhost:8080/post/save
	@PostMapping("/save")
	public String save(Post post) { // id =0, userId=0
		try {
			postRepository.save(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/post";
	}

	// GET =>http://localhost:8080/post/id값
	@GetMapping("/{id}")
	public String post(@PathVariable int id, Model model) {
		Post post = postRepository.findById(id);
		model.addAttribute("post", post);
		return "post/detail";
	}

	@GetMapping("/updateForm/{id}")
	public String updateForm(@PathVariable int id, Model model) {
		Post post = postRepository.findById(id);
		model.addAttribute("post", post);

		return "post/updateForm";
	}
}
