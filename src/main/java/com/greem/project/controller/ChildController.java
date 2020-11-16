package com.greem.project.controller;

import com.greem.project.domain.child.ChildDto;
import com.greem.project.service.ChildService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ChildController {
    private final ChildService childService;

    @RequestMapping(method = RequestMethod.GET, value = "/child")
    public List<ChildDto> getChildren() {
        return childService.getAllChildren();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/child/{childId}")
    public ChildDto getChild(@PathVariable Long childId) {
        return childService.getChild(childId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/child")
    public ChildDto addChild(@RequestBody ChildDto childDto) {
        return childService.addChild(childDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/child")
    public ChildDto updateChild(@RequestBody ChildDto childDto) {
        return childService.updateChild(childDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/child/{childId}")
    public ChildDto deleteChild(@PathVariable Long childId) {
        return childService.deleteChild(childId);
    }
}
