package com.pa.niftyorders.data.repository_mock

import com.pa.niftyorders.domain.model.entities.CartLine
import com.pa.niftyorders.domain.model.entities.Product
import com.pa.niftyorders.domain.repository.Repository
import java.math.BigDecimal
import javax.inject.Inject

class RepositoryMock @Inject constructor() : Repository {
    override suspend fun getTopProducts(): List<Product> {
        return sampleProducts
    }

    override suspend fun getProductsInCart(): List<CartLine> {
        return sampleCart
    }

    override suspend fun changeQuantityInCart(cartLineId: Long, changeQuanityBy: Int, changeTotalPriceBy: BigDecimal) {
        return
    }

    override suspend fun createDemoData() {
        return
    }

    override suspend fun addProductToCart(cartLine: CartLine) {
        return
    }

}

val sampleProducts = listOf(
    Product(
        id = 1,
        name = "Adgeeka hot as the devil's toe, are you friend or foe",
        price = BigDecimal(200.99),
        description = "Honey is a sweet, viscous food substance made by honey bees and some other bees.[1][2] Bees produce honey from the sugary secretions of plants (floral nectar) or from secretions of other insects (such as honeydew), by regurgitation, enzymatic activity, and water evaporation. Honey bees store honey in wax structures called honeycombs, whereas stingless bees store honey in pots made of wax and resin.[1][2][3] The bees work together as a group with the regurgitation and digestion for as long as 20 minutes, passing the nectar from one bee to the next, until the product reaches the honeycombs in storage quality.[16] It is then placed in honeycomb cells and left unsealed while still high in water content (about 50 to 70%) and natural yeasts which, unchecked, would cause the sugars in the newly formed honey to ferment.[21] Bees are among the few insects that can generate large amounts of body heat, and the hive bees constantly regulate the hive temperature, either heating with their bodies or cooling with water evaporation, to maintain a fairly constant temperature of about 35 °C (95 °F) in the honey-storage areas. The process continues as hive bees flutter their wings constantly to circulate air and evaporate water from the honey to a content around 18%, raising the sugar concentration beyond the saturation point and preventing fermentation.[15][16] The bees then cap the cells with wax to seal them.[16] As removed from the hive by a beekeeper, honey has a long shelf life and will not ferment if properly sealed.[15]\n" +
                "\n" +
                "Some wasp species, such as Brachygastra lecheguana and Brachygastra mellifica found in South and Central America, are known to feed on nectar and produce honey.[22]\n" +
                "\n" +
                "Some wasps, such as Polistes versicolor, consume honey, alternating between feeding on pollen in the middle of their lifecycles and feeding on honey, which can better provide for their energy needs.[23]",
        imageUrl = "https://cdn.w600.comps.canstockphoto.com/round-knolling-composition-of-various-pictures_csp87075789.jpg"
    ),
    Product(
        id = 2,
        name = "Mojito with a touch of rum",
        description = "Honey is a sweet, viscous food substance made by honey bees and some other bees.[1][2] Bees produce honey from the sugary secretions of plants (floral nectar) or from secretions of other insects (such as honeydew), by regurgitation, enzymatic activity, and water evaporation. Honey bees store honey in wax structures called honeycombs, whereas stingless bees store honey in pots made of wax and resin.[1][2][3] The bees work together as a group with the regurgitation and digestion for as long as 20 minutes, passing the nectar from one bee to the next, until the product reaches the honeycombs in storage quality.[16] It is then placed in honeycomb cells and left unsealed while still high in water content (about 50 to 70%) and natural yeasts which, unchecked, would cause the sugars in the newly formed honey to ferment.[21] Bees are among the few insects that can generate large amounts of body heat, and the hive bees constantly regulate the hive temperature, either heating with their bodies or cooling with water evaporation, to maintain a fairly constant temperature of about 35 °C (95 °F) in the honey-storage areas. The process continues as hive bees flutter their wings constantly to circulate air and evaporate water from the honey to a content around 18%, raising the sugar concentration beyond the saturation point and preventing fermentation.[15][16] The bees then cap the cells with wax to seal them.[16] As removed from the hive by a beekeeper, honey has a long shelf life and will not ferment if properly sealed.[15]\n" +
                "\n" +
                "Some wasp species, such as Brachygastra lecheguana and Brachygastra mellifica found in South and Central America, are known to feed on nectar and produce honey.[22]\n" +
                "\n" +
                "Some wasps, such as Polistes versicolor, consume honey, alternating between feeding on pollen in the middle of their lifecycles and feeding on honey, which can better provide for their energy needs.[23]",
        price = BigDecimal(99.99),
        imageUrl = "https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/3279978A-FC6C-4231-A42C-DF759994C99C/Derivates/4278FB29-8E6B-4986-BF60-231C91231A01.jpg"
    ),
    Product(
        id = 3,
        name = "Paella de mariscos valenciana",
        description = "Honey is a sweet, viscous food substance made by honey bees and some other bees.[1][2] Bees produce honey from the sugary secretions of plants (floral nectar) or from secretions of other insects (such as honeydew), by regurgitation, enzymatic activity, and water evaporation. Honey bees store honey in wax structures called honeycombs, whereas stingless bees store honey in pots made of wax and resin.[1][2][3] The bees work together as a group with the regurgitation and digestion for as long as 20 minutes, passing the nectar from one bee to the next, until the product reaches the honeycombs in storage quality.[16] It is then placed in honeycomb cells and left unsealed while still high in water content (about 50 to 70%) and natural yeasts which, unchecked, would cause the sugars in the newly formed honey to ferment.[21] Bees are among the few insects that can generate large amounts of body heat, and the hive bees constantly regulate the hive temperature, either heating with their bodies or cooling with water evaporation, to maintain a fairly constant temperature of about 35 °C (95 °F) in the honey-storage areas. The process continues as hive bees flutter their wings constantly to circulate air and evaporate water from the honey to a content around 18%, raising the sugar concentration beyond the saturation point and preventing fermentation.[15][16] The bees then cap the cells with wax to seal them.[16] As removed from the hive by a beekeeper, honey has a long shelf life and will not ferment if properly sealed.[15]\n" +
                "\n" +
                "Some wasp species, such as Brachygastra lecheguana and Brachygastra mellifica found in South and Central America, are known to feed on nectar and produce honey.[22]\n" +
                "\n" +
                "Some wasps, such as Polistes versicolor, consume honey, alternating between feeding on pollen in the middle of their lifecycles and feeding on honey, which can better provide for their energy needs.[23]",
        price = BigDecimal(299.99),
        imageUrl = "https://www.myfooddata.com/images/creative/splash.png"
    ),
    Product(
        id = 4,
        name = "Adgeeka hot as the devil's toe",
        description = "Honey is a sweet, viscous food substance made by honey bees and some other bees.[1][2] Bees produce honey from the sugary secretions of plants (floral nectar) or from secretions of other insects (such as honeydew), by regurgitation, enzymatic activity, and water evaporation. Honey bees store honey in wax structures called honeycombs, whereas stingless bees store honey in pots made of wax and resin.[1][2][3] The bees work together as a group with the regurgitation and digestion for as long as 20 minutes, passing the nectar from one bee to the next, until the product reaches the honeycombs in storage quality.[16] It is then placed in honeycomb cells and left unsealed while still high in water content (about 50 to 70%) and natural yeasts which, unchecked, would cause the sugars in the newly formed honey to ferment.[21] Bees are among the few insects that can generate large amounts of body heat, and the hive bees constantly regulate the hive temperature, either heating with their bodies or cooling with water evaporation, to maintain a fairly constant temperature of about 35 °C (95 °F) in the honey-storage areas. The process continues as hive bees flutter their wings constantly to circulate air and evaporate water from the honey to a content around 18%, raising the sugar concentration beyond the saturation point and preventing fermentation.[15][16] The bees then cap the cells with wax to seal them.[16] As removed from the hive by a beekeeper, honey has a long shelf life and will not ferment if properly sealed.[15]\n" +
                "\n" +
                "Some wasp species, such as Brachygastra lecheguana and Brachygastra mellifica found in South and Central America, are known to feed on nectar and produce honey.[22]\n" +
                "\n" +
                "Some wasps, such as Polistes versicolor, consume honey, alternating between feeding on pollen in the middle of their lifecycles and feeding on honey, which can better provide for their energy needs.[23]",
        price = BigDecimal(200.99),
        imageUrl = "https://cdn.w600.comps.canstockphoto.com/round-knolling-composition-of-various-pictures_csp87075789.jpg"
    ),
    Product(
        id = 5,
        name = "Mojito with a touch of rum",
        description = "Honey is a sweet, viscous food substance made by honey bees and some other bees.[1][2] Bees produce honey from the sugary secretions of plants (floral nectar) or from secretions of other insects (such as honeydew), by regurgitation, enzymatic activity, and water evaporation. Honey bees store honey in wax structures called honeycombs, whereas stingless bees store honey in pots made of wax and resin.[1][2][3] The bees work together as a group with the regurgitation and digestion for as long as 20 minutes, passing the nectar from one bee to the next, until the product reaches the honeycombs in storage quality.[16] It is then placed in honeycomb cells and left unsealed while still high in water content (about 50 to 70%) and natural yeasts which, unchecked, would cause the sugars in the newly formed honey to ferment.[21] Bees are among the few insects that can generate large amounts of body heat, and the hive bees constantly regulate the hive temperature, either heating with their bodies or cooling with water evaporation, to maintain a fairly constant temperature of about 35 °C (95 °F) in the honey-storage areas. The process continues as hive bees flutter their wings constantly to circulate air and evaporate water from the honey to a content around 18%, raising the sugar concentration beyond the saturation point and preventing fermentation.[15][16] The bees then cap the cells with wax to seal them.[16] As removed from the hive by a beekeeper, honey has a long shelf life and will not ferment if properly sealed.[15]\n" +
                "\n" +
                "Some wasp species, such as Brachygastra lecheguana and Brachygastra mellifica found in South and Central America, are known to feed on nectar and produce honey.[22]\n" +
                "\n" +
                "Some wasps, such as Polistes versicolor, consume honey, alternating between feeding on pollen in the middle of their lifecycles and feeding on honey, which can better provide for their energy needs.[23]",
        price = BigDecimal(99.99),
        imageUrl = "https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/3279978A-FC6C-4231-A42C-DF759994C99C/Derivates/4278FB29-8E6B-4986-BF60-231C91231A01.jpg"
    ),
    Product(
        id = 6,
        name = "Paella de mariscos valenciana",
        description = "Honey is a sweet, viscous food substance made by honey bees and some other bees.[1][2] Bees produce honey from the sugary secretions of plants (floral nectar) or from secretions of other insects (such as honeydew), by regurgitation, enzymatic activity, and water evaporation. Honey bees store honey in wax structures called honeycombs, whereas stingless bees store honey in pots made of wax and resin.[1][2][3] The bees work together as a group with the regurgitation and digestion for as long as 20 minutes, passing the nectar from one bee to the next, until the product reaches the honeycombs in storage quality.[16] It is then placed in honeycomb cells and left unsealed while still high in water content (about 50 to 70%) and natural yeasts which, unchecked, would cause the sugars in the newly formed honey to ferment.[21] Bees are among the few insects that can generate large amounts of body heat, and the hive bees constantly regulate the hive temperature, either heating with their bodies or cooling with water evaporation, to maintain a fairly constant temperature of about 35 °C (95 °F) in the honey-storage areas. The process continues as hive bees flutter their wings constantly to circulate air and evaporate water from the honey to a content around 18%, raising the sugar concentration beyond the saturation point and preventing fermentation.[15][16] The bees then cap the cells with wax to seal them.[16] As removed from the hive by a beekeeper, honey has a long shelf life and will not ferment if properly sealed.[15]\n" +
                "\n" +
                "Some wasp species, such as Brachygastra lecheguana and Brachygastra mellifica found in South and Central America, are known to feed on nectar and produce honey.[22]\n" +
                "\n" +
                "Some wasps, such as Polistes versicolor, consume honey, alternating between feeding on pollen in the middle of their lifecycles and feeding on honey, which can better provide for their energy needs.[23]",
        price = BigDecimal(99.99),
        imageUrl = "https://www.myfooddata.com/images/creative/splash.png"
    ),
    Product(
        id = 7,
        name = "Adgeeka hot as the devil's toe",
        description = "Honey is a sweet, viscous food substance made by honey bees and some other bees.[1][2] Bees produce honey from the sugary secretions of plants (floral nectar) or from secretions of other insects (such as honeydew), by regurgitation, enzymatic activity, and water evaporation. Honey bees store honey in wax structures called honeycombs, whereas stingless bees store honey in pots made of wax and resin.[1][2][3] Honey gets its sweetness from the monosaccharides fructose and glucose, and has about the same relative sweetness as sucrose (table sugar).[5][6] Fifteen millilitres (1 US tablespoon) of honey provides around 190 kilojoules (46 kilocalories) of food energy.[7] It has attractive chemical properties for baking and a distinctive flavor when used as a sweetener.[5] Most microorganisms do not grow in honey, so sealed honey does not spoil, even after thousands of years.[8][9][10]",
        price = BigDecimal(99.99),
        imageUrl = "https://www.chefmarket.ru/blog/wp-content/uploads/2018/05/3_1522162464_3dc93.jpg"
    ),
    Product(
        id = 8,
        name = "Mojito with a touch of rum",
        description = "Honey is a sweet, viscous food substance made by honey bees and some other bees.[1][2] Bees produce honey from the sugary secretions of plants (floral nectar) or from secretions of other insects (such as honeydew), by regurgitation, enzymatic activity, and water evaporation. Honey bees store honey in wax structures called honeycombs, whereas stingless bees store honey in pots made of wax and resin.[1][2][3] Honey gets its sweetness from the monosaccharides fructose and glucose, and has about the same relative sweetness as sucrose (table sugar).[5][6] Fifteen millilitres (1 US tablespoon) of honey provides around 190 kilojoules (46 kilocalories) of food energy.[7] It has attractive chemical properties for baking and a distinctive flavor when used as a sweetener.[5] Most microorganisms do not grow in honey, so sealed honey does not spoil, even after thousands of years.[8][9][10]",
        price = BigDecimal(99.99),
        imageUrl = "https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/3279978A-FC6C-4231-A42C-DF759994C99C/Derivates/4278FB29-8E6B-4986-BF60-231C91231A01.jpg"
    ),
    Product(
        id = 9,
        name = "Paella de mariscos valenciana",
        description = "Honey is a sweet, viscous food substance made by honey bees and some other bees.[1][2] Bees produce honey from the sugary secretions of plants (floral nectar) or from secretions of other insects (such as honeydew), by regurgitation, enzymatic activity, and water evaporation. Honey bees store honey in wax structures called honeycombs, whereas stingless bees store honey in pots made of wax and resin.[1][2][3] Honey gets its sweetness from the monosaccharides fructose and glucose, and has about the same relative sweetness as sucrose (table sugar).[5][6] Fifteen millilitres (1 US tablespoon) of honey provides around 190 kilojoules (46 kilocalories) of food energy.[7] It has attractive chemical properties for baking and a distinctive flavor when used as a sweetener.[5] Most microorganisms do not grow in honey, so sealed honey does not spoil, even after thousands of years.[8][9][10]",
        price = BigDecimal(99.99),
        imageUrl = "https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/f3d7d1b4-4df6-4d72-b2f0-acf05cccae08/Derivates/4513fa48-f03c-4f8e-bea0-18385dd4bdd0.jpg"
    )
)


val sampleCart = sampleProducts.take(7).mapIndexed() { index, item ->
    CartLine(
        id = index.toLong(),
        productId = item.id,
        name = item.name,
        imageUrl = item.imageUrl,
        quantity = (index+1).toFloat(),
        price = item.price,
        totalPrice = item.price * (index.toBigDecimal()+BigDecimal(1)),
        )
}
