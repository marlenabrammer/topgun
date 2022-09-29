package games.topgun;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnemyTest {

    Enemy enemy1;
    Enemy enemy2;
    int offset = 1000/5;
    int enemy1X;

    @Before
    public void setUp() throws Exception {
        enemy1 = new Enemy(200,0);
        enemy2 = new Enemy(offset,0);

    }

    @Test
    public void enemy_testOffset_shouldBeEquals(){
        assertEquals(enemy1.getY_coordinate(),enemy2.getY_coordinate());

    }
}