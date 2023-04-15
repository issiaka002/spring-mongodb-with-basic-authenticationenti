package ci.inphb.appspringmongo.Domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Document
@Data @AllArgsConstructor @NoArgsConstructor @ToString @Builder
public class Category {
    @Id
    private String id;
    private String name;
    @DBRef
    private Collection<Product> products;
}
