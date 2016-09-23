package homework1;

import java.util.List;

public class PostConverter {
	String convert(List<Post> posts) {
		StringBuilder builder = new StringBuilder();
		for(Post post: posts) {
			builder.append("<strong>" + post.getTitle() +"</strong> <br><br>");
			builder.append(post.getUser() +"<br>");
			builder.append(post.getDate() + "<br>");
			builder.append(post.getContent() +"<br><br>");
		}
		return builder.toString();
	}
}
