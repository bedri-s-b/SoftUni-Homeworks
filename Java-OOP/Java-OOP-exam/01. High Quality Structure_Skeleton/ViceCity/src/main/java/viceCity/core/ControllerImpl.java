package viceCity.core;

import com.sun.source.tree.IfTree;
import viceCity.common.ConstantMessages;
import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.players.BasePlayer;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;
import viceCity.repositories.GunRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private MainPlayer mainPlayer;
    private Collection<Player> players;
    private GunRepository guns;
    private GangNeighbourhood neighbourhood;

    public ControllerImpl() {
        this.mainPlayer = new MainPlayer();
        this.players = new ArrayList<>();
        this.guns = new GunRepository();
        this.neighbourhood = new GangNeighbourhood();
    }

    @Override
    public String addPlayer(String name) {
        players.add(new CivilPlayer(name));
        return String.format(ConstantMessages.PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun = null;

        if (type.equals("Rifle")) {
            gun = new Rifle(name);
        } else if (type.equals("Pistol")) {
            gun = new Pistol(name);
        }
        if (gun != null) {
            guns.add(gun);
            return String.format(ConstantMessages.GUN_ADDED,name,type);
        }
        return ConstantMessages.GUN_TYPE_INVALID;
    }

    @Override
    public String addGunToPlayer(String name) {

        Collection<Gun> guns = this.guns.getModels();
        List<Gun> collect = new ArrayList<>(guns);

        if (name.equals("Vercetti")){
            if (collect.size() == 0){
                return ConstantMessages.GUN_QUEUE_IS_EMPTY;
            }
            Gun remove = collect.remove(0);
            mainPlayer.getGunRepository().add(remove);
            guns.remove(remove);
            return String.format(ConstantMessages.GUN_ADDED_TO_MAIN_PLAYER, remove.getName(),"Tommy Vercetti");
        }else {
            if (collect.size() == 0){
                return ConstantMessages.GUN_QUEUE_IS_EMPTY;
            }
            Player player = players.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
            if (player != null){
                player.getGunRepository().add(collect.get(0));
                Gun gun = collect.remove(0);
                guns.remove(gun);
                return String.format(ConstantMessages.GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(),player.getName());
            }
            return ConstantMessages.CIVIL_PLAYER_DOES_NOT_EXIST;
        }
    }

    @Override
    public String fight() {
        int currentLifePoints = mainPlayer.getLifePoints();
        int playersAllLifePoints = players.stream().mapToInt(Player::getLifePoints).sum();
        int totalLifePointsBefore = currentLifePoints + playersAllLifePoints;
        int civilPlayersCount = players.size();
        neighbourhood.action(mainPlayer,players);
        int after = mainPlayer.getLifePoints() + players.stream().mapToInt(Player::getLifePoints).sum();

        if (after == totalLifePointsBefore){
            return ConstantMessages.FIGHT_HOT_HAPPENED;
        }else {
           // players.removeIf(player -> !player.isAlive());

            return ConstantMessages.FIGHT_HAPPENED + System.lineSeparator()
                    + String.format(ConstantMessages.MAIN_PLAYER_LIVE_POINTS_MESSAGE, mainPlayer.getLifePoints()) +
                    System.lineSeparator()
                    + String.format(ConstantMessages.MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE,civilPlayersCount - players.size()) +
                    System.lineSeparator()
                    + String.format(ConstantMessages.CIVIL_PLAYERS_LEFT_MESSAGE, players.size());
        }

    }
}
