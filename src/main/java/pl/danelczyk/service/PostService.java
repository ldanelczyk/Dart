package pl.danelczyk.service;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.danelczyk.model.Post;
import pl.danelczyk.model.User;
import pl.danelczyk.repository.PostRepository;
import pl.danelczyk.repository.UserRepository;

@Service
public class PostService {

	private PostRepository postRepository;
	private UserRepository userRepository;

	@Autowired
	public void setPostRepository(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void addPost(String inputTitle, String inputUrl, String inputDescription, 
			String nameOfLoggedUser) {
		Timestamp time = new Timestamp(new Date().getTime());
		Post post = new Post(inputTitle, inputUrl, inputDescription, time);

		User user = userRepository.findByUsername(nameOfLoggedUser);
		post.setUser(user);
		postRepository.save(post);
	}
	
	public List<Post> getAllPosts(Comparator<Post> comparator) {
		List<Post> posts = postRepository.findAll();
		if (comparator != null && posts != null) {
			posts.sort(comparator);
		}
		return posts;
	}

	public void removePost(String title) {
		Post post = postRepository.findByTitle(title);
		postRepository.delete(post);
	}

	public boolean checkDuplicate(String title) {
		boolean result = true;
		Post duplicatePost = postRepository.findByTitle(title);
		if (duplicatePost != null) {
			result = false;
		}
		return result;
	}

}
