package ce3.wbc.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class Chef {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chef_id")
    private Integer chefId;

    @Column(name = "chef_name", nullable = false)
    private String chefName;

    @Column(name = "chef_category")
    private String chefCategory;
    
    @Column(name = "chef_image")
    private String chefImage;
    
    public static Chef of(String chefName, String chefCategory, String chefImage) {
        return new Chef(null,chefName, chefCategory, chefImage);
    }

}
