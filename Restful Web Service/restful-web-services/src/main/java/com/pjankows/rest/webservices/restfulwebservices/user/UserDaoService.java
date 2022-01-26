package com.pjankows.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pjankows.rest.webservices.restfulwebservices.post.Post;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	
	private static int usersCount = 3;
	
	static {
		User currUser;
		currUser = new User(1, "Adam", new Date());
		currUser.addPost(new Post(1, "First post of Adam.", new Date()));
		users.add(currUser);
		currUser = new User(2, "Eve", new Date());
		currUser.addPost(new Post(1, "First post of Eve.", new Date()));
		users.add(currUser);
		currUser = new User(3, "Jack", new Date());
		currUser.addPost(new Post(1, "First post of Jack.", new Date()));
		users.add(currUser);
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public void saveUser(User user) {
		if(user.getId()==null) {
			user.setId(++usersCount);
		}
		users.add(user);
	}
	
	public User findUser(int id) {
		for(User user:users) {
			if(user.getId()==id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteUser(int id) {
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId()==id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
	
	public void savePost(Post post, int userId) {
		for(User user:users) {
			if(user.getId()==userId) {
				user.addPost(post);
			}
		}
	}
	
	public Post getPost(int postId, int userId) {
		/*
		 * for(User user:users) { if(user.getId()==userId) { for(Post
		 * post:user.getUserPosts()) { if(post.getId()==postId) return post; } } }
		 */
		return null;
	}
	
	public List<Post> getAllPosts(int userId) {
		/*
		 * for(User user:users) { if(user.getId()==userId) return user.getUserPosts(); }
		 */
		return null;
	}
	
}
 