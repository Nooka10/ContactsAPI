package ch.benoitschopfer.model.mappers;

import ch.benoitschopfer.model.entity.Skill;
import ch.benoitschopfer.model.other.SkillAddOrUpdate;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-30T23:39:22+0200",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 11.0.8 (AdoptOpenJDK)"
)
public class SkillMapperImpl implements SkillMapper {

    @Override
    public Skill skillAddToSkill(SkillAddOrUpdate skillAddOrUpdate) {
        if ( skillAddOrUpdate == null ) {
            return null;
        }

        Skill skill = new Skill();

        skill.setName( skillAddOrUpdate.getName() );

        return skill;
    }

    @Override
    public SkillAddOrUpdate skillToSkillAdd(Skill skill) {
        if ( skill == null ) {
            return null;
        }

        SkillAddOrUpdate skillAddOrUpdate = new SkillAddOrUpdate();

        skillAddOrUpdate.setName( skill.getName() );

        return skillAddOrUpdate;
    }

    @Override
    public Skill skillUpdateToSkill(SkillAddOrUpdate skillAddOrUpdate) {
        if ( skillAddOrUpdate == null ) {
            return null;
        }

        Skill skill = new Skill();

        skill.setName( skillAddOrUpdate.getName() );

        return skill;
    }

    @Override
    public SkillAddOrUpdate skillToSkillUpdate(Skill skill) {
        if ( skill == null ) {
            return null;
        }

        SkillAddOrUpdate skillAddOrUpdate = new SkillAddOrUpdate();

        skillAddOrUpdate.setName( skill.getName() );

        return skillAddOrUpdate;
    }
}
