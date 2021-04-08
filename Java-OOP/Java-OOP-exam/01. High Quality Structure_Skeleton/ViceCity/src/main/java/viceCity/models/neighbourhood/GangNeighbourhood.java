package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;
import viceCity.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.stream.Collectors;

public class GangNeighbourhood implements Neighbourhood {
    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {

        for (Gun model : mainPlayer.getGunRepository().getModels()) {
            for (Player civilPlayer : civilPlayers) {
                while (civilPlayer.isAlive() && model.canFire()) {
                    civilPlayer.takeLifePoints(model.fire());
                }
            }
        }


        //civilPlayers = civilPlayers.stream().filter(Player::isAlive).collect(Collectors.toList());
        civilPlayers.removeIf(player -> !player.isAlive());

        if (!civilPlayers.isEmpty()) {
            for (Player civilPlayer : civilPlayers) {
                for (Gun model : civilPlayer.getGunRepository().getModels()) {
                    while (model.canFire() && mainPlayer.isAlive()) {
                        mainPlayer.takeLifePoints(civilPlayer.getGunRepository().find(model.getName()).fire());
                    }
                }

            }
        }


    }
}
