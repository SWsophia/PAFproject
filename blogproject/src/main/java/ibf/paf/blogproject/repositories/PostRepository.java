package ibf.paf.blogproject.repositories;

import ibf.paf.blogproject.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

}
