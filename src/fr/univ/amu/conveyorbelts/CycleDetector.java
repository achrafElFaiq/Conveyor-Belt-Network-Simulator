package fr.univ.amu.conveyorbelts;

public class CycleDetector {

  // s_0 et t_0
  // s_n = update(s_{n-1}), t_n = t_{n-1} si n est impair, t_n = update(t_{n-1})
  // on s'arrête dès que s_n = t_n avec n >= 1 --> s_n est dans le cycle
  // s_{n+1}, s_{n+2}, ... jusqu'à s_{n+p} = t_n, p est la période.

    // on cree 2 copie, on fait l pdate normal por la 1 ere et 1/2 por la 2 eme
    // pis on compare les extremites
    //on trove network cycle
    private static final int MAX_ITERATIONS_BEFORE_GIVING_UP = 100000;

    private BeltNetwork periodicState;


    public CycleDetector(BeltNetwork network) {
        this.periodicState = findPeriodicState(network);
    }

    public BeltNetwork getPeriodicState() {
        return periodicState;
    }

    private BeltNetwork findPeriodicState(BeltNetwork network) {
        BeltNetwork state1 = network.copy();
        BeltNetwork state2 = network.copy();
        int nbUpdates = 0;
        while (nbUpdates < MAX_ITERATIONS_BEFORE_GIVING_UP) {
            nbUpdates++;
            state1.update();
            if (nbUpdates % 2 == 0) {
                state2.update();
            }
            if (state1.isInSameState(state2)) {
                return state1;
            }
        }
        return null;
    }

    public int getPeriod() {
        BeltNetwork state = periodicState.copy();
        int nbUpdates = 0;
        do {
            state.update();
            nbUpdates++;
        } while (!periodicState.isInSameState(state));
        return nbUpdates;
    }

    public int getPeriodThroughput(int beltIndex) {
        BeltNetwork state = periodicState.copy();
        int initialNbDropped = state.getBelts().get(beltIndex).nbDroppedItems();
        do {
            state.update();
        } while (!periodicState.isInSameState(state));
        return state.getBelts().get(beltIndex).nbDroppedItems()
            - initialNbDropped;
    }

}
