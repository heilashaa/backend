package com.haapp.formicary.domain.service;

import com.haapp.formicary.domain.model.Tag;
import com.haapp.formicary.persistence.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;

@Service
@AllArgsConstructor
public class TagService {

    private TagRepository repository;
    private ModelMapper modelMapper;

    public List<Tag> getAll(){
        var tags = repository.findAll();
        return asList(modelMapper.map(tags, Tag[].class));
    }

    public Tag findByNameOrCreate(String name){
        var optional = repository.findByName(name);
        if(optional.isPresent()){
            return modelMapper.map(optional.get(), Tag.class);
        }
        Tag tag = new Tag();
        tag.setName(name);
        return save(tag);
    }

    public Tag save(Tag tag) {
        var dataTag = modelMapper.map(tag, com.haapp.formicary.persistence.model.Tag.class);
        dataTag  = repository.save(dataTag);
        return modelMapper.map(dataTag, Tag.class);
    }
}
