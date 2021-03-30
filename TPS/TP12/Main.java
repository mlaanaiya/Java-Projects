import java.util.*;
import java.util.stream.Collectors;
import java.io.*;
import java.util.zip.*;
import java.time.LocalDateTime;


/**
 * La classe principale.
 *
 * Tous les traitements demandés sont faits dans la mêthode
 * {@code repondreQuestions}.
 * Il serait plus logique d'écrire des méthodes qui pemettraient d'améliorer
 * la structuration et la réutilisation.  Cependant l'objectif est ici la
 * manipulation de l'API des collections, pas la structuration du code
 * en sous-programmes.
 */

public class Main {

	private static void repondreQuestions(Reader in) {
		Iterable<PointDeVente> iterable = PointDeVenteUtils.fromXML(in);

		// Construire un tableau associatif (pdvs) des points de vente avec un
		// accès par identifiant
		Map<Long, PointDeVente> pdvs = new HashMap<>();
		for (PointDeVente pdv: iterable) {
			pdvs.put(pdv.getIdentifiant(), pdv);
		}


		// Nombre de point de vente
		System.out.println("Nombre de PDV : " + pdvs.size());


		// Collecter tous les services fournis
		Set<String> services = new TreeSet<>();
		for (PointDeVente pdv: pdvs.values()) {
			services.addAll(pdv.getServices());
		}


		// Afficher le nombre de services existants
		System.out.println("Nombre de services : " + services.size());


		// Afficher les services fournis
		System.out.println("Liste des services :");
		for (String service: services) {
			System.out.println("  - " + service);
		}


		// Afficher le prix du gazole pour le point de vente d'identifiant
		// 31075001 le 25 janvier 2017 à 10h.
		long id1 = 31075001;
		PointDeVente pdv1 = pdvs.get(id1);
		System.out.println("Ville de " + id1 + " = " + pdv1.getVille());
		LocalDateTime d1 = LocalDateTime.parse("2017-01-25T10:00:00");
		System.out.println("Prix de " + id1 + "@" + d1 + " = "
				+ pdvs.get(id1).getPrix(Carburant.GAZOLE, d1));


		// Afficher le nombre de offrant au moins un point de vente
		Set<String> villes = new TreeSet<>();	// ordonnées !
		for (PointDeVente pdv: pdvs.values()) {
			villes.add(pdv.getVille());
		}
		System.out.println("Nb de villes offrant au moins un PDV : "
				+ villes.size());


		// Afficher le nombre moyen de points de vente par ville
		//     Collecter le nombre de PDV par ville
		Map<String, Integer> nbPdvParVille = new HashMap<>();
		for (PointDeVente pdv: pdvs.values()) {
			String ville = pdv.getVille();
			int n = nbPdvParVille.containsKey(ville) ? nbPdvParVille.get(ville) : 0;
			nbPdvParVille.put(ville, n + 1);
		}
		//     Afficher le nombre moyen de points de vente par ville
		System.out.println("Nb moyen de PDV par ville : "
				+ (1.0 * pdvs.values().size() / nbPdvParVille.keySet().size()));


		// le nombre de villes offrants un certain nombre de carburants
		for (int nbCarburants = 1; nbCarburants < 7; nbCarburants++) {
			final int nb = nbCarburants;
			long nbPdvNbCarburantsEtPlus = pdvs.values().stream()
					.filter(p -> p.getPrix().keySet().size() >= nb)
					.count();
			System.out.printf("PDV proposant au moins %d carburant(s) : %d%n",
					nbCarburants, nbPdvNbCarburantsEtPlus);
		}


		// Afficher le nombre et les points de vente dont le code postal est 31200
		List<PointDeVente> pdvs31200 = pdvs.values().stream()
				.filter(p -> p.getCodePostal().equals("31200"))
				.collect(Collectors.toList());
		System.out.println("Nombre de PDV en 31200 : " + pdvs31200.size());
		pdvs31200.stream().forEach(System.out::println);


		// Nombre de PDV de la ville de Toulouse qui proposent et du Gazole
		// et du GPLc.
		long nbToulouseGazoleGPLc = pdvs.values().stream()
				.filter( p -> p.getVille().equals("TOULOUSE") )
				.filter( p -> p.getPrix().containsKey(Carburant.GAZOLE))
				.filter( p -> p.getPrix().containsKey(Carburant.GPLc))
				.count();
		System.out.println("Nb de stations toulousaines vendant Gazole et GPLc : "
				+ nbToulouseGazoleGPLc);


		// Afficher le nom et le nombre de points de vente des villes qui ont au
		// moins 20 points de vente
		// Sans stream (dans le désordre)
		System.out.println("Les villes avec au moins 10 PDV :");
		for (Map.Entry<String, Integer> entree : nbPdvParVille.entrySet()) {
			if (entree.getValue() >= 20) {
				System.out.println("Ville = " + entree.getKey() + " a "
						+ entree.getValue() + " PDV");
			}
		}


		// Avec stream, dans l'ordre décroissant
		System.out.println("Les villes avec au moins 10 PDV (classées) :");
		nbPdvParVille.entrySet().stream()
			.filter(e -> e.getValue() >= 20)
			.sorted( (e1, e2) -> e2.getValue() - e1.getValue())
			.forEach( e -> System.out.println(e.getKey() + " : " + e.getValue()));


		// Les 10 villes qui ont le plus grand nombre de points de vente
		System.out.println("Les 10 villes avec le plus grand nombre de PDV :");
		nbPdvParVille.entrySet().stream()
			.sorted( (e1, e2) -> e2.getValue() - e1.getValue())
			.limit(10)
			.forEach( e -> System.out.println(e.getKey() + " : " + e.getValue()));
	}


	private static Reader ouvrir(String nomFichier)
			throws FileNotFoundException, IOException
	{
		if (nomFichier.endsWith(".zip")) {
			// On suppose que l'archive est bien formée :
			// elle contient fichier XML !
			ZipFile zfile = new ZipFile(nomFichier);
			ZipEntry premiere = zfile.entries().nextElement();
			return new InputStreamReader(zfile.getInputStream(premiere));
		} else {
			return new FileReader(nomFichier);
		}
	}


	public static void main(String[] args) throws FileNotFoundException {
		if (args.length != 1) {
			System.out.println("usage : java Main <fichier.xml>");
		} else {
			try (Reader in = ouvrir(args[0])) {
				repondreQuestions(in);
			} catch (FileNotFoundException e) {
				System.out.println("Fichier non trouvé : " + args[0]);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

}
