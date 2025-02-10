package ce3.wbc.service;

import ce3.wbc.controller.rto.response.ChefRes;
import ce3.wbc.dto.ChefDto;
import ce3.wbc.entity.Chef;
import ce3.wbc.repository.ChefRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChefService {

    private final ChefRepository chefRepository;

    public List<ChefRes> getAllChefs() {
        return chefRepository.findAll().stream()
                .map(ChefRes::toResponse)
                .collect(Collectors.toList());
    }
}
