package br.gov.sp.fatec.projetomaven.entity.player;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.gov.sp.fatec.projetomaven.entity.Player;
import br.gov.sp.fatec.projetomaven.entity.enums.PositionEnum;

@Entity
@DiscriminatorValue("SHOOTING_GUARD")
public class ShootingGuard extends Player {

    @Override
    public PositionEnum getPosition() {
        return PositionEnum.SHOOTING_GUARD;
    }
    
}