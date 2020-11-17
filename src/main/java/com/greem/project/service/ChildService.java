package com.greem.project.service;

import com.greem.project.domain.child.Child;
import com.greem.project.domain.child.ChildDto;
import com.greem.project.mapper.ChildMapper;
import com.greem.project.repository.ChildRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChildService {

    private final ChildRepository childRepository;
    private final ChildMapper childMapper;

    public List<ChildDto> getAllChildren() {
        return childMapper.mapToChildDtoList(childRepository.findAll());
    }

    public ChildDto getChild(Long childId) {
        return childMapper.mapToChildDto(childRepository.findById(childId).orElse(new Child()));
    }

    public ChildDto addChild(ChildDto childDto) {
        return childMapper.mapToChildDto(childRepository.save(childMapper.mapToChild(childDto)));
    }

    public ChildDto updateChild(ChildDto childDto) {
        Optional<Child> requestedChild = childRepository.findById(childDto.getId());
        if (requestedChild.isPresent()) {
            return childMapper.mapToChildDto(childRepository.save(childMapper.mapToChild(childDto)));
        }
        return new ChildDto();
    }

    public ChildDto deleteChild(Long childId) {
        Optional<Child> requestedChild = childRepository.findById(childId);
        if (requestedChild.isPresent()) {
            childRepository.deleteById(childId);
            return childMapper.mapToChildDto(requestedChild.get());
        }
        return new ChildDto();
    }
}
