package org.mage.test.cards.single;

import mage.constants.PhaseStep;
import mage.constants.Zone;
import mage.counters.CounterType;
import org.junit.Ignore;
import org.junit.Test;
import org.mage.test.serverside.base.CardTestPlayerBase;

/**
 * @author JRHerlehy
 *         Created on 7/27/17.
 */
public class RimescaleDragonTest extends CardTestPlayerBase {

    private final String dragon = "Rimescale Dragon";
    private final String snowMountain = "Snow-Covered Mountain";

    private final String thopter = "Ornithopter";
    private final String terror = "Terror";

    private final String ability = "{2}{S}: Tap target";

    //TODO: Remove ignore flags once snow mana payment is implemented for testing framework.

    @Test
    @Ignore
    public void testActivatedAbility() {
        this.setupTest();

        this.setStopAt(1, PhaseStep.POSTCOMBAT_MAIN);
        this.execute();

        this.assertTapped(thopter, true);
        this.assertCounterCount(thopter, CounterType.ICE, 1);
    }

    @Test
    @Ignore
    public void testStaticAbility() {
        this.setupTest();

        this.setStopAt(2, PhaseStep.UPKEEP);
        this.execute();

        this.assertTapped(thopter, true);
        this.assertCounterCount(thopter, CounterType.ICE, 1);
    }

    @Test
    @Ignore
    public void testStaticAbilityEnded() {
        this.setupTest();

        this.castSpell(1, PhaseStep.POSTCOMBAT_MAIN, playerB, terror, dragon);

        this.setStopAt(2, PhaseStep.UPKEEP);
        this.execute();

        this.assertTapped(thopter, false);
        this.assertCounterCount(thopter, CounterType.ICE, 1);
    }

    private void setupTest() {
        this.addCard(Zone.BATTLEFIELD, playerA, dragon);
        this.addCard(Zone.BATTLEFIELD, playerA, snowMountain, 3);

        this.addCard(Zone.BATTLEFIELD, playerB, thopter);
        this.addCard(Zone.BATTLEFIELD, playerB, "Swamp", 2);
        this.addCard(Zone.HAND, playerB, terror);

        this.activateAbility(1, PhaseStep.PRECOMBAT_MAIN, playerA, ability, thopter);
    }
}
