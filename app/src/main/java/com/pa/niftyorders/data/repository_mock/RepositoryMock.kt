package com.pa.niftyorders.data.repository_mock

import com.pa.niftyorders.domain.model.entities.CartLine
import com.pa.niftyorders.domain.model.entities.Product
import com.pa.niftyorders.domain.model.entities.ProductGroup
import com.pa.niftyorders.domain.model.entities.Promotion
import com.pa.niftyorders.domain.repository.Repository
import java.math.BigDecimal
import javax.inject.Inject

class RepositoryMock @Inject constructor() : Repository {
    override suspend fun getTopProducts(): List<Product> {
        return sampleProducts
    }

    override suspend fun getProductsInGroup(groupId: Long): List<Product> {
        return emptyList()
    }

    override suspend fun getFeaturedProductGroups(): List<ProductGroup> {
        return emptyList()
    }

    override suspend fun getProductsInCart(): List<CartLine> {
        return sampleCart
    }

    override suspend fun changeQuantityInCart(
        cartLineId: Long,
        changeQuanityBy: Int,
        changeTotalPriceBy: BigDecimal
    ) {
        return
    }

    override suspend fun createDemoData() {
        return
    }

    override suspend fun addProductToCart(cartLine: CartLine) {
        return
    }

    override suspend fun getPromotions(): List<Promotion> {
        return samplePromotions
    }

}

val sampleProductGroups = listOf(
    ProductGroup(
        id = 1,
        name = "Waffles",
        description = "Crispy stuff",
        featured = true,
        imageUrl = ""
    ),
    ProductGroup(
        id = 2,
        name = "Bagels",
        description = "Crispy stuff",
        featured = true,
        imageUrl = ""
    ),
    ProductGroup(
        id = 3,
        name = "Churros",
        description = "Crispy stuff",
        featured = true,
        imageUrl = ""
    ),
    ProductGroup(
        id = 4,
        name = "Muffins",
        description = "Crispy stuff",
        featured = true,
        imageUrl = ""
    ),
    ProductGroup(
        id = 5,
        name = "Doughnuts",
        description = "Crispy stuff",
        featured = true,
        imageUrl = ""
    ), ProductGroup(
        id = 6,
        name = "Croissants",
        description = "Crispy stuff",
        featured = true,
        imageUrl = ""
    )
)

val sampleProducts = listOf(
    Product(
        id = 1,
        groupId = 1,
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
        groupId = 1,
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
        groupId = 1,
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
        groupId = 2,
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
        groupId = 2,
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
        groupId = 2,
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
        groupId = 3,
        name = "Adgeeka hot as the devil's toe",
        description = "Honey is a sweet, viscous food substance made by honey bees and some other bees.[1][2] Bees produce honey from the sugary secretions of plants (floral nectar) or from secretions of other insects (such as honeydew), by regurgitation, enzymatic activity, and water evaporation. Honey bees store honey in wax structures called honeycombs, whereas stingless bees store honey in pots made of wax and resin.[1][2][3] Honey gets its sweetness from the monosaccharides fructose and glucose, and has about the same relative sweetness as sucrose (table sugar).[5][6] Fifteen millilitres (1 US tablespoon) of honey provides around 190 kilojoules (46 kilocalories) of food energy.[7] It has attractive chemical properties for baking and a distinctive flavor when used as a sweetener.[5] Most microorganisms do not grow in honey, so sealed honey does not spoil, even after thousands of years.[8][9][10]",
        price = BigDecimal(99.99),
        imageUrl = "https://www.chefmarket.ru/blog/wp-content/uploads/2018/05/3_1522162464_3dc93.jpg"
    ),
    Product(
        id = 8,
        groupId = 3,
        name = "Mojito with a touch of rum",
        description = "Honey is a sweet, viscous food substance made by honey bees and some other bees.[1][2] Bees produce honey from the sugary secretions of plants (floral nectar) or from secretions of other insects (such as honeydew), by regurgitation, enzymatic activity, and water evaporation. Honey bees store honey in wax structures called honeycombs, whereas stingless bees store honey in pots made of wax and resin.[1][2][3] Honey gets its sweetness from the monosaccharides fructose and glucose, and has about the same relative sweetness as sucrose (table sugar).[5][6] Fifteen millilitres (1 US tablespoon) of honey provides around 190 kilojoules (46 kilocalories) of food energy.[7] It has attractive chemical properties for baking and a distinctive flavor when used as a sweetener.[5] Most microorganisms do not grow in honey, so sealed honey does not spoil, even after thousands of years.[8][9][10]",
        price = BigDecimal(99.99),
        imageUrl = "https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/3279978A-FC6C-4231-A42C-DF759994C99C/Derivates/4278FB29-8E6B-4986-BF60-231C91231A01.jpg"
    ),
    Product(
        id = 9,
        groupId = 3,
        name = "Paella de mariscos valenciana",
        description = "Honey is a sweet, viscous food substance made by honey bees and some other bees.[1][2] Bees produce honey from the sugary secretions of plants (floral nectar) or from secretions of other insects (such as honeydew), by regurgitation, enzymatic activity, and water evaporation. Honey bees store honey in wax structures called honeycombs, whereas stingless bees store honey in pots made of wax and resin.[1][2][3] Honey gets its sweetness from the monosaccharides fructose and glucose, and has about the same relative sweetness as sucrose (table sugar).[5][6] Fifteen millilitres (1 US tablespoon) of honey provides around 190 kilojoules (46 kilocalories) of food energy.[7] It has attractive chemical properties for baking and a distinctive flavor when used as a sweetener.[5] Most microorganisms do not grow in honey, so sealed honey does not spoil, even after thousands of years.[8][9][10]",
        price = BigDecimal(99.99),
        imageUrl = "https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/f3d7d1b4-4df6-4d72-b2f0-acf05cccae08/Derivates/4513fa48-f03c-4f8e-bea0-18385dd4bdd0.jpg"
    )
)


val sampleCart = sampleProducts.take(7).mapIndexed { index, item ->
    CartLine(
        id = index.toLong(),
        productId = item.id,
        name = item.name,
        imageUrl = item.imageUrl,
        quantity = (index + 1).toFloat(),
        price = item.price,
        totalPrice = item.price * (index.toBigDecimal() + BigDecimal(1)),
    )
}

val promotionImages = mapOf<BigDecimal, String>(
    BigDecimal(5) to "https://easycdn.blob.core.windows.net/deals/md-1318-web-banner-eng-my.jpg",
    BigDecimal(10) to "https://img.freepik.com/free-psd/10-percent-off-promotion-summer-sale-banner-template_445059-335.jpg?w=2000",
    BigDecimal(15) to "https://sg.everydayonsales.com/wp-content/uploads/2022/05/26-May-2-Jun-2022-METRO-15-off-on-cosmetics-and-fragrances-Promotion.jpeg",
    BigDecimal(20) to "https://onecms-res.cloudinary.com/image/upload/s--arAgKXyv--/f_auto%2Cq_auto/c_fill%2Cg_auto%2Ch_622%2Cw_830/v1/tdy-migration/treasures-yi-dian-xin.jpg?itok=jAJ_6OqK",
    BigDecimal(25) to "https://easycdn.blob.core.windows.net/deals/eng.jpg",
    BigDecimal(30) to "https://www.walgreens.com/store/c/contact-lenses/ID=359432-tier2clense#overlay--video-two-goose"
)

val samplePromotions = sampleProducts.take(6).mapIndexed() { index, item ->
    Promotion(
        id = index.toLong(),
        productId = item.id,
        discount = BigDecimal((index + 1) * 5),
        price = item.price,
        name = item.name,
        description = "Get discount of ${index * 5}% only today",
        imageUrl = promotionImages[BigDecimal((index + 1) * 5)] ?: ""
    )
}
