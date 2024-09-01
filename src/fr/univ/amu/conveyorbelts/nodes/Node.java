package fr.univ.amu.conveyorbelts.nodes;
//Achraf,
//        je pense que tu devrais faire une interface pour les nœuds du réseaux, avec pour l'instant une seule méthode update (ou peut-être aussi graphicRepresentation,
//        mais cela devrait plutôt être une troisième interface). Il faut faire attention dans l'update du réseau, on traite tous les nœuds (splitter, entrée et sortie),
//        puis tous les tapis. J'ai pushé la modification.
  //      Il faut continuer à travailler sur la représentation textuelle du réseau.

import fr.univ.amu.conveyorbelts.Belt;

import java.util.List;
import java.util.function.UnaryOperator;

public interface Node {
    void update();
    List<Belt> inputs();
    List<Belt> outputs();
    Node copy(UnaryOperator<Belt> beltToBeltCopy);
    boolean isInSameState(Node node);
}
