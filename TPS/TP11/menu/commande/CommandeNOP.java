package menu.commande;


/** Commande... qui ne fait rien !
  * @author	Xavier Crégut
  * @version	$Revision: 1.1 $
  */
final public class CommandeNOP implements CommandeNeutre {

	public void executer() {
	}

	public boolean estExecutable() {
		return true;
	}

}
