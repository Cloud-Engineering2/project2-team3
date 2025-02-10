package ce3.wbc.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class Chef {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chef_id")
    private Integer chefId;

    @Column(name = "chef_name", nullable = false)
    private String chefName;

    @Column(name = "chef_category", nullable = false)
    private String chefCategory;

    @Column(name = "chef_image")
    private String chefImage;

    public static Chef of(String chefName, String chefCategory, String chefImage) {
        return new Chef(null,chefName, chefCategory, chefImage);
    }

}
