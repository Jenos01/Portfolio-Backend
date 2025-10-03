package com.example.demo.Service;


import com.example.demo.Entity.Links;
import com.example.demo.Repository.LinksRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LinksService {

    private final LinksRepository linksRepository;

    public List<Links> getAllLinks() {
        return linksRepository.findAll();
    }

    public Links addLink(Links links) {
        return linksRepository.save(links);
    }
}
