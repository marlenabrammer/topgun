package games.topgun;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class PilotTest {
    private Pilot pilot1;
    private Pilot pilot2;
    private Weapon weapon1;
    private Weapon weapon2;


    @Before
    public void setUp(){

      pilot1 = new Pilot();
      pilot2 = new Pilot();


      weapon1 = new Weapon((500-17),(850+1));

    }
    @Test
    public void pilot_shouldBeSame_WhenGenerated() {
        Assert.assertEquals(pilot1.getX_coordinate(), pilot2.getX_coordinate());
    }

    @Test
    public void pilot_weaponShouldBeSame_CompareXPosition(){
        Assert.assertEquals(pilot1.getX_coordinate(), weapon1.getX_coordinate());
    }
    @Test
    public void pilot_weaponShouldBeSame_CompareYPosition(){
        Assert.assertEquals(pilot1.getY_coordinate(), weapon1.getY_coordinate());
    }

}