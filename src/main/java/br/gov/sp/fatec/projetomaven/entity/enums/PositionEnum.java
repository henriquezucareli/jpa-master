package br.gov.sp.fatec.projetomaven.entity.enums;

import br.gov.sp.fatec.projetomaven.entity.player.Center;
import br.gov.sp.fatec.projetomaven.entity.player.PointGuard;
import br.gov.sp.fatec.projetomaven.entity.player.PowerForward;
import br.gov.sp.fatec.projetomaven.entity.player.ShootingGuard;
import br.gov.sp.fatec.projetomaven.entity.player.SmallForward;

public enum PositionEnum {
    
    POINT_GUARD(PointGuard.class), 
    SHOOTING_GUARD(ShootingGuard.class), 
    SMALL_FORWARD(SmallForward.class), 
    POWER_FORWARD(PowerForward.class), 
    CENTER(Center.class);

    private Class<?> clazz;

    private PositionEnum(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Class<?> getPositionClass(){
        return this.clazz;
    }

    public String getPositionClassName() {
        return clazz.getName();
    }

}