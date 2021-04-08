package viceCity.models.guns;

public class Pistol extends BaseGun {
    public Pistol(String name) {
        super(name, 10, 100);
    }

    @Override
    public int fire() {
        return reload();
    }


    private int reload() {
        if (getBulletsPerBarrel() > 0) {
            super.bulletsPerBarrel -= 1;
            return 1;
        } else if (getBulletsPerBarrel() == 0 && getTotalBullets() > 0) {
            super.totalBullets -= 10;
            super.bulletsPerBarrel = 9;
            return 1;
        } else {
            return 0;
        }
    }
}
