package com.haapp.formicary.config;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ModelMapperUtils {

    private ModelMapper modelMapper;

    public  <D, T> List<D> mapAll(final Collection<T> source, Class<D> outCLass) {
        return source.stream()
                .map(entity -> modelMapper.map(entity, outCLass))
                .collect(Collectors.toList());
    }

}
