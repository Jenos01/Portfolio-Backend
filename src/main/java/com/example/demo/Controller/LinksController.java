package com.example.demo.Controller;


import com.example.demo.Entity.Links;
import com.example.demo.Service.LinksService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("links")
@RequiredArgsConstructor
public class LinksController {

    private final LinksService linksService;

    @GetMapping
    public List<Links> getAllLinks() {
        return linksService.getAllLinks();
    }


    @PostMapping
    public Links addLink(@RequestBody Links links) {
        return linksService.addLink(links);
    }
}
