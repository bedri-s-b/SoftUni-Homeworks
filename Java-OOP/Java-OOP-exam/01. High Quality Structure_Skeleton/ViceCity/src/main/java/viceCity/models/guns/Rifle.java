package viceCity.models.guns;

public class Rifle extends BaseGun {

    public Rifle(String name) {
        super(name, 50, 500);
    }

    @Override
    public int fire() {
        return reload();
    }

    private int reload() {
        if (getBulletsPerBarrel() > 0){
            super.bulletsPerBarrel-=10;
            return 10;
        }else if (getBulletsPerBarrel() == 0 && getTotalBullets() > 0){
            super.totalBullets -= 50;
            super.bulletsPerBarrel = 40;
            return 10;
        }else {
            return 0;
        }
    }
}
