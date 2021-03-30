package menu.commande;

import menu.*;

/** Une CommandeNeutre est une commande qui ne modifie pas le
  * modèle et n'a donc pas besoin d'être annulée (ni conservée).
  * C'est une interface de marquage.
  * @author	Xavier Crégut
  * @version	1.1
  */
public interface CommandeNeutre extends Commande {
}
