package com.vietnamroller.ranking.controller;

import com.vietnamroller.ranking.model.Overall;
import com.vietnamroller.ranking.service.OverallService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/overall")
public class OverallController extends GenericReactiveController<Overall, Long> {

    public OverallController(OverallService service) {
        super(service);
    }
}
