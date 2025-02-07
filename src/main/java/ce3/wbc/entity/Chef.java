package ce3.wbc.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Chef {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chef_id")
    private Integer chefId;
    @Column(name = "chef_name")
    private String chefName;
    @Column(name = "chef_category")
    private String chefCategory;
    @Column(name = "chef_image")
    private String chefImage;


}
