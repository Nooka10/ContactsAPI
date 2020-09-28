package ch.benoitschopfer.model.mappers;

import ch.benoitschopfer.model.entity.SkillLevel;
import ch.benoitschopfer.model.other.SkillLevelAdd;
import ch.benoitschopfer.model.other.SkillLevelUpdate;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-30T23:39:22+0200",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 11.0.8 (AdoptOpenJDK)"
)
public class SkillLevelMapperImpl implements SkillLevelMapper {

    @Override
    public SkillLevel SkillLevelAddToSkillLevel(SkillLevelAdd skillLevelAdd) {
        if ( skillLevelAdd == null ) {
            return null;
        }

        SkillLevel skillLevel = new SkillLevel();

        skillLevel.setLevel( skillLevelAdd.getLevel() );

        return skillLevel;
    }

    @Override
    public SkillLevelAdd skillLevelToSkillLevelAdd(SkillLevel skillLevel) {
        if ( skillLevel == null ) {
            return null;
        }

        SkillLevelAdd skillLevelAdd = new SkillLevelAdd();

        skillLevelAdd.setLevel( skillLevel.getLevel() );

        return skillLevelAdd;
    }

    @Override
    public SkillLevel SkillLevelUpdateToSkillLevel(SkillLevelUpdate skillLevelUpdate) {
        if ( skillLevelUpdate == null ) {
            return null;
        }

        SkillLevel skillLevel = new SkillLevel();

        skillLevel.setLevel( skillLevelUpdate.getLevel() );

        return skillLevel;
    }

    @Override
    public SkillLevelUpdate skillLevelToSkillLevelUpdate(SkillLevel skillLevel) {
        if ( skillLevel == null ) {
            return null;
        }

        SkillLevelUpdate skillLevelUpdate = new SkillLevelUpdate();

        skillLevelUpdate.setLevel( skillLevel.getLevel() );

        return skillLevelUpdate;
    }
}
