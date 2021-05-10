import frc.robot.Roulette.Roulette;
import frc.robot.Roulette.RouletteColor;
import frc.robot.Roulette.RouletteComponentsA;
import org.junit.jupiter.api.Test;

import static frc.robot.Roulette.RouletteConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouletteTest {
    private final Roulette roulette = new Roulette(new RouletteComponentsA());

    @Test
    public void testCalc(){
        assertEquals(-0.125,roulette.getRounds(ROULETTE_YELLOW, ROULETTE_BLUE));
        assertEquals(0.125, roulette.getRounds(ROULETTE_BLUE, ROULETTE_YELLOW));
        assertEquals(0.25,roulette.getRounds(ROULETTE_BLUE,ROULETTE_BLUE));
    }

}
