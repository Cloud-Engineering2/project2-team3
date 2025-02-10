package ce3.wbc.service;

import ce3.wbc.dto.ChefDto;
import ce3.wbc.entity.Chef;
import ce3.wbc.repository.ChefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChefService {

    @Autowired
    private ChefRepository chefRepository;

    public List<ChefDto> getAllChefs() {
        return chefRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private ChefDto toDto(Chef chef) {
        ChefDto dto = new ChefDto();
        dto.setChefId(chef.getChefId());
        dto.setChefName(chef.getChefName());
        dto.setChefCategory(chef.getChefCategory());
        dto.setChefImage(chef.getChefImage());
        return dto;
    }

}
