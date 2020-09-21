package br.gov.sp.fatec.projetomaven.entity.player;

import javax.persistence.DiscriminatorValue;

import br.gov.sp.fatec.projetomaven.entity.Player;
import br.gov.sp.fatec.projetomaven.entity.enums.PositionEnum;

@DiscriminatorValue("CENTER")
public class Center extends Player {

    @Override
    public PositionEnum getPosition() {
         return PositionEnum.CENTER;
    }
    
}