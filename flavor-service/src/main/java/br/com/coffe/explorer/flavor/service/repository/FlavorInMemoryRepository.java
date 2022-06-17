package br.com.coffe.explorer.flavor.service.repository;

import br.com.coffe.explorer.core.domain.entity.Flavor;
import br.com.coffe.explorer.core.domain.port.output.FlavorRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlavorInMemoryRepository implements FlavorRepository {

    private static final List<Flavor> flavors;

    static {
        Flavor fruity = new Flavor("FRUITY", "Frutado", null);

            Flavor berry = new Flavor("BERRY", "Frutas Vermelhas", fruity);

                Flavor blackberry = new Flavor("BLACKBERRY", "Amora-silvestre", berry);
                Flavor raspberry = new Flavor("RASPBERRY", "Framboesa", berry);
                Flavor blueberry = new Flavor("BLUEBERRY", "Mirtilo", berry);
                Flavor strawberry = new Flavor("STRAWBERRY", "Morango", berry);


            Flavor driedFruit = new Flavor("DRIED_FRUIT", "Frutas secas", fruity);

                Flavor raisin = new Flavor("RAISIN", "Uva-passa", driedFruit);
                Flavor prune = new Flavor("PRUNE", "Ameixa-seca", driedFruit);

            Flavor otherFruit = new Flavor("OTHER_FRUIT", "Outras frutas", fruity);

                Flavor coconut = new Flavor("COCONUT", "Coco", otherFruit);
                Flavor cherry = new Flavor("CHERRY", "Cereja", otherFruit);
                Flavor pomegranate = new Flavor("POMEGRANATE", "Romã", otherFruit);
                Flavor pineapple = new Flavor("PINEAPPLE", "Abacaxi", otherFruit);
                Flavor grape = new Flavor("GRAPE", "Uva", otherFruit);
                Flavor apple = new Flavor("APPLE", "Maçã", otherFruit);
                Flavor peach = new Flavor("PEACH", "Pêssego", otherFruit);
                Flavor pear = new Flavor("PEAR", "Pera", otherFruit);

            Flavor citrusFruit = new Flavor("CITRUS_FRUIT", "Frutas cítricas", fruity);

                Flavor grapefruit = new Flavor("GRAPE_FRUIT", "Toranja", citrusFruit);
                Flavor orange = new Flavor("ORANGE", "Laranja", citrusFruit);
                Flavor lemon = new Flavor("LEMON", "Limão siciliano", citrusFruit);
                Flavor lime = new Flavor("LIME", "Limão", citrusFruit);

        Flavor sourFermented = new Flavor("SOUR_FERMENTED", "Azedo/Fermentado", null);

            Flavor sour = new Flavor("SOUR", "Azedo", sourFermented);

                Flavor sourAromatics = new Flavor("SOUR_AROMATICS", "Aromas azedos", sour);
                Flavor aceticAcid = new Flavor("ACETIC_ACID", "Ácido acético", sour);
                Flavor butyricAcid = new Flavor("BUTYRIC_ACID", "Ácido butírico", sour);
                Flavor isovalericAcid = new Flavor("ISOVALERIC_ACID", "Ácido isovalérico", sour);
                Flavor citricAcid = new Flavor("CITRIC_ACID", "Ácido cítrico", sour);
                Flavor malicAcid = new Flavor("MALIC_ACID", "Ácido málico", sour);

            Flavor alcoholFermented = new Flavor("ALCOHOL_FERMENTED", "Álcool/Fermentado", sourFermented);

                Flavor winey = new Flavor("WINEY", "Vinhoso", alcoholFermented);
                Flavor whiskey = new Flavor("WHISKEY", "Uísque", alcoholFermented);
                Flavor fermented = new Flavor("FERMENTED", "Fermentado", alcoholFermented);
                Flavor overripe = new Flavor("OVERRIPE", "Sobre amadurecido", alcoholFermented);

        Flavor greenVegetative = new Flavor("GREEN_VEGETATIVE", "Verde/Vegetal", null);

            Flavor oliveOil = new Flavor("OLIVE_OIL", "Azeite de oliva", greenVegetative);
            Flavor raw = new Flavor("RAW", "Cru", greenVegetative);
            Flavor greenVegetative2 = new Flavor("GREEN_VEGETATIVE_2", "Verde/Vegetal", greenVegetative);

                Flavor underRipe = new Flavor("UNDER_RIPE", "Verde", greenVegetative2);
                Flavor peapod = new Flavor("PEAPOD", "Vagem", greenVegetative2);
                Flavor fresh = new Flavor("FRESH", "Fresco", greenVegetative2);
                Flavor darkGreen = new Flavor("DARK_GREEN", "Verde-escuro", greenVegetative2);
                Flavor vegetative = new Flavor("VEGETATIVE", "Vegetal", greenVegetative2);
                Flavor hayLike = new Flavor("HAY_LIKE", "Feno", greenVegetative2);
                Flavor herbLike = new Flavor("HERB_LIKE", "Herbáceo", greenVegetative2);

            Flavor beany = new Flavor("BEANY", "Grãos", greenVegetative);

        Flavor other = new Flavor("OTHER", "Outros", null);

            Flavor paperyMusty = new Flavor("PAPERY_MUSTY", "Papel/Mofado", other);

                Flavor stale = new Flavor("STALE", "Vencido", paperyMusty);
                Flavor cardboard = new Flavor("CARDBOARD", "Papelão", paperyMusty);
                Flavor papery = new Flavor("PAPERY", "Papel", paperyMusty);
                Flavor woody = new Flavor("WOODY", "Amadeirado", paperyMusty);
                Flavor moldyDamp = new Flavor("MOLDY_DAMP", "Úmido/Fungos", paperyMusty);
                Flavor mustyDusty = new Flavor("MUSTY_DUSTY", "Mofado/Seco", paperyMusty);
                Flavor mustyEarthy = new Flavor("MUSTY_EARTHY", "Mofado/Terroso", paperyMusty);
                Flavor animalic = new Flavor("ANIMALIC", "Animal", paperyMusty);
                Flavor meatyBrothy = new Flavor("MEATY_BROTHY", "Caldo de carne", paperyMusty);
                Flavor phenolic = new Flavor("PHENOLIC", "Fenólico", paperyMusty);

            Flavor chemical = new Flavor("CHEMICAL", "Químico", other);

                Flavor bitter = new Flavor("BITTER", "Amargo", chemical);
                Flavor salty = new Flavor("SALTY", "Salgado", chemical);
                Flavor medicinal = new Flavor("MEDICINAL", "Medicinal", chemical);
                Flavor petroleum = new Flavor("PETROLEUM", "Petróleo", chemical);
                Flavor skunky = new Flavor("SKUNKY", "Gambá", chemical);
                Flavor rubber = new Flavor("RUBBER", "Borracha", chemical);


        Flavor roasted = new Flavor("ROASTED", "Assado", null);

            Flavor pipeTobacco = new Flavor("PIPE_TOBACCO", "Fumo de rolo", roasted);
            Flavor tobacco = new Flavor("TOBACCO", "Tabaco", roasted);

            Flavor burnt = new Flavor("BURNT", "Queimado", roasted);

                Flavor acrid = new Flavor("ACRID", "Acre", burnt);
                Flavor ashy = new Flavor("ASHY", "Cinzas", burnt);
                Flavor smoky = new Flavor("SMOKY", "Fumaça", burnt);
                Flavor brownRoast = new Flavor("BROWN_ROAST", "Tostado/Queimado", burnt);

            Flavor cereal = new Flavor("CEREAL", "Cereal", roasted);

                Flavor grain = new Flavor("GRAIN", "Grãos", cereal);
                Flavor malt = new Flavor("MALT", "Malte", cereal);

        Flavor spices = new Flavor("SPICES", "Especiarias", null);

            Flavor pungent = new Flavor("PUNGENT", "Pungente", spices);
            Flavor pepper = new Flavor("PEPPER", "Pimenta", spices);

            Flavor brownSpice = new Flavor("BROWN_SPICE", "Pimenta marrom", spices);

                Flavor anise = new Flavor("ANISE", "Anis", brownSpice);
                Flavor nutmeg = new Flavor("NUTMEG", "Noz-moscada", brownSpice);
                Flavor cinnamon = new Flavor("CINNAMON", "Canela", brownSpice);
                Flavor clove = new Flavor("CLOVE", "Cravo", brownSpice);

        Flavor nuttyCocoa = new Flavor("NUTTY_COCOA", "Noz/Cacau", null);

            Flavor nutty = new Flavor("NUTTY", "Noz", nuttyCocoa);

                Flavor peanuts = new Flavor("PEANUTS", "Amendoim", nutty);
                Flavor hazelnut = new Flavor("HAZELNUT", "Avelã", nutty);

            Flavor cocoa = new Flavor("COCOA", "Cacau", nuttyCocoa);

                Flavor chocolate = new Flavor("CHOCOLATE", "Chocolate", cocoa);
                Flavor darkChocolate = new Flavor("DARK_CHOCOLATE", "Chocolate amargo", cocoa);

        Flavor sweet = new Flavor("SWEET", "Doce", null);

            Flavor brownSugar = new Flavor("BROWN_SUGAR", "Açúcar mascavo", sweet);

                Flavor molasses = new Flavor("MOLASSES", "Melaço", brownSugar);
                Flavor mappleSyrup = new Flavor("MAPPLE_SYRUP", "Xarope de bordo", brownSugar);
                Flavor caramelized = new Flavor("CARAMELIZED", "Caramelizado", brownSugar);
                Flavor honey = new Flavor("HONEY", "Mel", brownSugar);

            Flavor vanilla = new Flavor("VANILLA", "Baunilha", sweet);
            Flavor vanillin = new Flavor("VANILLIN", "Vanilina", sweet);
            Flavor overallSweet = new Flavor("OVERALL_SWEET", "Bastante doce", sweet);
            Flavor sweetAromatics = new Flavor("SWEET_AROMATICS", "Aromas doces", sweet);

        Flavor floral = new Flavor("FLORAL", "Floral", null);

            Flavor blackTea = new Flavor("BLACK_TEA", "Chá preto", floral);

            Flavor floral2 = new Flavor("FLORAL_2", "Floral", floral);

                Flavor chamomile = new Flavor("CHAMOMILE", "Camomila", floral);
                Flavor rose = new Flavor("ROSE", "Rosa", floral);
                Flavor jasmine = new Flavor("JASMINE", "Jasmim", floral);

        flavors = new ArrayList<>();
        flavors.addAll(List.of(
                fruity,
                berry,
                blackberry,
                raspberry,
                blueberry,
                strawberry,
                driedFruit,
                raisin,
                prune,
                otherFruit,
                coconut,
                cherry,
                pomegranate,
                pineapple,
                grape,
                apple,
                peach,
                pear,
                citrusFruit,
                grapefruit,
                orange,
                lemon,
                lime,
                sourFermented,
                sour,
                sourAromatics,
                aceticAcid,
                butyricAcid,
                isovalericAcid,
                citricAcid,
                malicAcid,
                alcoholFermented,
                winey,
                whiskey,
                fermented,
                overripe,
                greenVegetative,
                oliveOil,
                raw,
                greenVegetative2,
                underRipe,
                peapod,
                fresh,
                darkGreen,
                vegetative,
                hayLike,
                herbLike,
                beany,
                other,
                paperyMusty,
                stale,
                cardboard,
                papery,
                woody,
                moldyDamp,
                mustyDusty,
                mustyEarthy,
                animalic,
                meatyBrothy,
                phenolic,
                chemical,
                bitter,
                salty,
                medicinal,
                petroleum,
                skunky,
                rubber,
                roasted,
                pipeTobacco,
                tobacco,
                burnt,
                acrid,
                ashy,
                smoky,
                brownRoast,
                cereal,
                grain,
                malt,
                spices,
                pungent,
                pepper,
                brownSpice,
                anise,
                nutmeg,
                cinnamon,
                clove,
                nuttyCocoa,
                nutty,
                peanuts,
                hazelnut,
                cocoa,
                chocolate,
                darkChocolate,
                sweet,
                brownSugar,
                molasses,
                mappleSyrup,
                caramelized,
                honey,
                vanilla,
                vanillin,
                overallSweet,
                sweetAromatics,
                floral,
                blackTea,
                floral2,
                chamomile,
                rose,
                jasmine
        ));
    }

    @Override
    public Optional<List<Flavor>> findFlavors() {
        return Optional.of(flavors);
    }

    @Override
    public Optional<Flavor> findByCode(String flavorCode) {
        return flavors.stream()
                .filter(flavor -> flavor.getCode().equals(flavorCode))
                .findFirst();
    }
}
