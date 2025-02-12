package ce3.wbc.service;

import ce3.wbc.controller.rto.response.ChefRes;
import ce3.wbc.dto.ChefDto;
import ce3.wbc.entity.Chef;
import ce3.wbc.repository.ChefRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Transactional
@RequiredArgsConstructor
@Service
public class ChefService {

    private final ChefRepository chefRepository;
    private final S3ImageService s3ImageService;
    private static final String DEFAULT_IMAGE_KEY = "ChefDefault.jpg";


    public List<ChefRes> getAllChefs() {
        return chefRepository.findAll().stream()
                .map(ChefRes::toResponse)
                .collect(Collectors.toList());
    }

    public Map<String, List<ChefRes>> getChefsGroupedByCategory() {
        return chefRepository.findAll().stream()
                .collect(Collectors.groupingBy(Chef::getChefCategory,
                        Collectors.mapping(ChefRes::toResponse, Collectors.toList())));
    }

    public ChefDto saveChef(@Valid ChefDto chefDto, MultipartFile file) {
        String s3Key;
        String chefEngName = chefDto.getChefImage();// EngName == chefImage
        String originalImgName = (file != null) ? file.getOriginalFilename() : "ChefDefault";

        if (file == null || file.isEmpty()) {
            s3Key = DEFAULT_IMAGE_KEY;
        } else {
            //s3이미지저장
            s3Key = s3ImageService.uploadS3(file, chefEngName);
        }
        Chef chef = Chef.of(
                chefDto.getChefName(),
                chefDto.getChefCategory(),
                s3Key,
                originalImgName);
        Chef saved = chefRepository.save(chef);


        return ChefDto.toDto(saved);
    }


}
