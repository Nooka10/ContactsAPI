package ch.benoitschopfer.controller;

import ch.benoitschopfer.model.other.SkillAddOrUpdate;
import ch.benoitschopfer.model.entity.Skill;
import ch.benoitschopfer.model.mappers.SkillMapper;
import ch.benoitschopfer.repository.SkillRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-24T16:13:09.139748+02:00[Europe/Paris]")
@RestController
@RequestMapping("${openapi.Contacts API.base-path:/api/secured}")
public class SkillsApiController implements SkillsApi {

  private final NativeWebRequest request;

  private SkillMapper skillMapper = Mappers.getMapper(SkillMapper.class);

  @Autowired
  SkillRepository skillRepository;

  @Autowired
  public SkillsApiController(NativeWebRequest request) {
    this.request = request;
  }

  @Override
  public Optional<NativeWebRequest> getRequest() {
    return Optional.ofNullable(request);
  }

  @Override
  public ResponseEntity<?> addSkill(@Valid SkillAddOrUpdate skillAddOrUpdate) {
    try {
      Skill savedSkill = skillRepository.save(skillMapper.skillAddToSkill(skillAddOrUpdate));

      String location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/skills/{id}").buildAndExpand(savedSkill.getId()).toUriString();

      return ResponseEntity
        .created(new URI(location))
        .body(savedSkill);
    } catch (URISyntaxException e) {
      return ResponseEntity.badRequest().body("Unable to create " + skillAddOrUpdate);
    }
  }

  @Override
  public ResponseEntity<Void> deleteSkill(String name) {
    Optional<Skill> skill = skillRepository.findByName(name);

    if (skill.isEmpty()) {
      return ResponseEntity.notFound().build();
    } else {
      skillRepository.delete(skill.get());
      return ResponseEntity.noContent().build();
    }
  }

  @Override
  public ResponseEntity<Skill> getSkill(String name) {
    Optional<Skill> skill = skillRepository.findByName(name);

    if (skill.isEmpty()) { return ResponseEntity.notFound().build(); }

    return ResponseEntity.ok(skill.get());
  }

  @Override
  public ResponseEntity<Page<Skill>> getSkills(@Valid String name, Pageable pageable) {
    if (name != null) {
      Specification<Skill> spec = (skill, cq, cb) -> cb.like(skill.get("name"), "%" + name + "%");
      return ResponseEntity.ok(skillRepository.findAll(spec, pageable));
    } else {
      return ResponseEntity.ok(skillRepository.findAll(pageable));
    }
  }

  @Override
  public ResponseEntity<Skill> updateSkill(String name, @Valid SkillAddOrUpdate skillAddOrUpdate) {
    Optional<Skill> optionalSkill = skillRepository.findByName(name);

    if (optionalSkill.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Skill skill = optionalSkill.get();

    skill.setName(skillAddOrUpdate.getName());

    skillRepository.save(skill);

    return ResponseEntity.ok(skill);
  }
}
