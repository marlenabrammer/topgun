package games.topgun;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WeaponTest {
    private Pilot pilot1;

    private Weapon weapon1;
    private Weapon weapon2;

    @Before
    public void setUp() throws Exception {
        pilot1 = new Pilot();

        weapon1 = new Weapon((230-6),(450+1));
        weapon2 = new Weapon(pilot1.getX_coordinate(), pilot1.getY_coordinate());

    }

    @Test
    public void weapon_weapon2ShouldNotEqual_Compare(){
        Assert.assertNotEquals(weapon1, weapon2);
    }
}