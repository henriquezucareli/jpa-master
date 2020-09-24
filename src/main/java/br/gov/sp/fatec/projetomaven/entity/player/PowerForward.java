package br.gov.sp.fatec.projetomaven.entity.player;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.gov.sp.fatec.projetomaven.entity.Player;
import br.gov.sp.fatec.projetomaven.entity.enums.PositionEnum;

@Entity
@DiscriminatorValue("POWER_FORWARD")
public class PowerForward extends Player {

    @Override
    public PositionEnum getPosition() {
        return PositionEnum.POWER_FORWARD;
    }
    
}