package br.gov.sp.fatec.projetomaven.entity.player;

import javax.persistence.DiscriminatorValue;

import br.gov.sp.fatec.projetomaven.entity.Player;
import br.gov.sp.fatec.projetomaven.entity.enums.PositionEnum;

@DiscriminatorValue("POINT_GUARD")
public class PointGuard extends Player {

    @Override
    public PositionEnum getPosition() {
        return PositionEnum.POINT_GUARD;
    }
    
}