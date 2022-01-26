package com.pjankows.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pjankows.rest.webservices.restfulwebservices.post.Post;
import com.pjankows.rest.webservices.restfulwebservices.post.PostNotFoundException;

@RestController
public class UserController {
	
	@Autowired
	private UserDaoService service;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = service.findUser(id);
		if(user==null)
			throw new UserNotFoundException("id-"+ id);
		
		EntityModel<User> resource = EntityModel.of(user);
		
		WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		service.saveUser(user);
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PostMapping("/users/{userId}/posts")
	public ResponseEntity<Object> createPost(@RequestBody Post post, @PathVariable int userId) {
		service.savePost(post, userId);
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{postId}")
		.buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}	
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id){
		User user = service.deleteUser(id);
		
		if(user==null)
			throw new UserNotFoundException("id-"+ id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/users/{userId}/posts")
	public List<Post> retrieveAllPosts(@PathVariable int userId) {
		User user = service.findUser(userId);
		if(user==null)
			throw new UserNotFoundException("id-"+ userId);
		return service.getAllPosts(userId);
	}
	
	@GetMapping("/users/{userId}/posts/{postId}")
	public Post retrievePost(@PathVariable int userId,@PathVariable int postId) {
		User user = service.findUser(userId);
		if(user==null)
			throw new UserNotFoundException("id-"+ userId);
		else {
			Post post = service.getPost(postId, userId);
			if(post==null)
				throw new PostNotFoundException("id-"+ postId);
			else
				return post;
		}
	}
}
