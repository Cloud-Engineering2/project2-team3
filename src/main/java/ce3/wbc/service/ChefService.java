package ce3.wbc.service;

import ce3.wbc.repository.ChefRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChefService {
    private final ChefRepository chefRepository;



}
