package info.soto.moviecatalogservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/endpointdoc")
public class EndpointDocController {
    private final RequestMappingHandlerMapping handlerMapping;

    @Autowired
    public EndpointDocController(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    @RequestMapping(method= RequestMethod.GET)
    public List<String> show() {
        ArrayList<String> map = new ArrayList<String>();
        this.handlerMapping.getHandlerMethods().entrySet().stream().forEach(entry -> {
            map.add("Entry: " + entry.getKey() + " => " + entry.getValue());
        });
        return map;
    }
}
