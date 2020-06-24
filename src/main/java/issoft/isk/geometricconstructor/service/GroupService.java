package issoft.isk.geometricconstructor.service;

import issoft.isk.geometricconstructor.model.entity.Group;
import issoft.isk.geometricconstructor.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    @Autowired
    final private GroupRepository groupRepository;

    public Group save(Group picture) {
        return groupRepository.save(picture);
    }

    public Optional<Group> findById(Long id) {
        return groupRepository.findById(id);
    }
}
