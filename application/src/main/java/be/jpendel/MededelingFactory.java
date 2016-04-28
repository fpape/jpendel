package be.jpendel;

import com.google.common.collect.Sets;

import java.util.*;
import java.util.stream.Collectors;

public class MededelingFactory {
    public static void main(String[] args) {
        MededelingFactory mf = new MededelingFactory();

        System.out.println(GestructureerdeMededeling.vanKredietNummer("1112222233"));

        System.out.println(mf.maakMededelingUitbetaling("1112222233", Sets.newHashSet(new Contractant(){

            @Override
            public PersoonsInformatie getPersoonsInformatie() {
                return new PersoonsInformatie() {
                    @Override
                    public String getVoornaam() {
                        return "Kris";
                    }

                    @Override
                    public String getNaam() {
                        return "Hofmans";
                    }

                    @Override
                    public boolean isNatuurlijkPersoon() {
                        return true;
                    }
                };
            }
        })));


        System.out.println(mf.maakMededelingUitbetaling("1112222233", Sets.newHashSet(new Contractant(){

            @Override
            public PersoonsInformatie getPersoonsInformatie() {
                return new PersoonsInformatie() {
                    @Override
                    public String getVoornaam() {
                        return "Kris";
                    }

                    @Override
                    public String getNaam() {
                        return "Hofmans";
                    }

                    @Override
                    public boolean isNatuurlijkPersoon() {
                        return true;
                    }
                };
            }
        }, new Contractant(){

            @Override
            public PersoonsInformatie getPersoonsInformatie() {
                return new PersoonsInformatie() {
                    @Override
                    public String getVoornaam() {
                        return "Gert";
                    }

                    @Override
                    public String getNaam() {
                        return "Vilain";
                    }

                    @Override
                    public boolean isNatuurlijkPersoon() {
                        return true;
                    }
                };
            }
        })));

        System.out.println(mf.maakMededelingUitbetaling("1112222233", Sets.newHashSet(new Contractant(){

            @Override
            public PersoonsInformatie getPersoonsInformatie() {
                return new PersoonsInformatie() {
                    @Override
                    public String getVoornaam() {
                        return "Doet er helemaal niet meer toe";
                    }

                    @Override
                    public String getNaam() {
                        return "Rotate IT";
                    }

                    @Override
                    public boolean isNatuurlijkPersoon() {
                        return false;
                    }
                };
            }
        })));
    }
    public Mededeling maakMededelingUitbetaling(final String kredietNummer, final Set<Contractant> contractanten) {
        String kredietDossier = "Dossier: " + kredietNummer;
        String contractantNamen = String.join(" ", geefContractNamen(contractanten));

        return Mededeling.vrij(kredietDossier + " " + contractantNamen);
    }

    private List<String> geefContractNamen(Set<Contractant> contractanten) {
        boolean meerdereContractanten = contractanten.size() > 1;
        return contractanten.stream()
                .map(c -> c.getPersoonsInformatie())
                .map(meerdereContractanten ? PersoonsInformatie::afgekorteNaam : PersoonsInformatie::volledigeNaam)
                .collect(Collectors.toList());
    }

    public Mededeling maakMededelingVordering(String kredietNummer) {
        return GestructureerdeMededeling.vanKredietNummer(kredietNummer);
    }


    enum MededelingType { VRIJ, GESTRUCTUREERD}
    interface Contractant {
        PersoonsInformatie getPersoonsInformatie();
    }
    interface PersoonsInformatie {
        String getVoornaam();
        String getNaam();
        boolean isNatuurlijkPersoon();
        default boolean isRechtsPersoon(){
            return !isNatuurlijkPersoon();
        }
        default String volledigeNaam(){
            return isRechtsPersoon() ? getNaam() : getNaam() + " "  + getVoornaam();
        }
        default String afgekorteNaam(){
            return getNaam();
        }
    }

    static class Mededeling {
        private String tekst;
        private MededelingType mededelingType;

        Mededeling(){}

        protected Mededeling(MededelingType mededelingType, String tekst) {
            this.mededelingType = mededelingType;
            this.tekst = tekst;
        }

        public static Mededeling vrij(String tekst){
            return new GestructureerdeMededeling(MededelingType.VRIJ, tekst);
        }

        public MededelingType getMededelingType() {
            return mededelingType;
        }

        public String getTekst() {
            return tekst;
        }

        @Override
        public String toString() {
            return tekst;
        }
    }

    static class GestructureerdeMededeling extends Mededeling {
        private static final String OMRING = "+++";
        private static final String SCHEIDING = "/";
        private static final Integer[] GROEP_START_POSITIES = new Integer[]{3, 5, 2};

        protected GestructureerdeMededeling(MededelingType mededelingType, String tekst) {
            super(mededelingType, tekst);
        }

        public static Mededeling vanKredietNummer(String tekst){
            String formattedTekst = String.format(formattering(GROEP_START_POSITIES),
                    splitsTekst(GROEP_START_POSITIES, tekst));
            return new GestructureerdeMededeling(MededelingType.GESTRUCTUREERD, formattedTekst);
        }

        private static String[] splitsTekst(Integer[] groepen, String tekst) {
            Collection<String> gesplittedGroepen = new ArrayList<>();
            int huidigePositie = 0;

            for (Integer startPositieGroep : groepen) {
                gesplittedGroepen.add(tekst.substring(huidigePositie));
                huidigePositie += startPositieGroep;
            }

            return gesplittedGroepen.toArray(new String[]{});
        }

        private static String formattering(Integer... groepen) {
            String resultaat = String.join(SCHEIDING, Arrays.asList(groepen)
                    .stream()
                    .map(g -> geefGroep(g))
                    .collect(Collectors.toList()));

            return OMRING + resultaat + OMRING;
        }

        private static String geefGroep(int groepLengte) {
            return "%" + groepLengte + "." + groepLengte + "s";
        }
    }
}
