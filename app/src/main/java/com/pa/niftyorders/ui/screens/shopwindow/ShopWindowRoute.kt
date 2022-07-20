package com.pa.niftyorders.ui.screens.shopwindow

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pa.niftyorders.R
import com.pa.niftyorders.domain.model.entities.CartLine
import com.pa.niftyorders.ui.NiftyOrdersAppState
import com.pa.niftyorders.ui.screens.cart.CartScreen
import com.pa.niftyorders.ui.theme.ThemeElements
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BottomBar(
    appState: NiftyOrdersAppState
) {
    BottomAppBar(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = ThemeElements.colors.primaryBackgroundColor,
        contentColor = ThemeElements.colors.buttonCaptionColor,
        elevation = 4.dp
    ) {
        appState.tabs.forEach { tab ->
            Text(tab.name)
        }
    }
}

@Composable
fun ShopWindowRoute(
    viewModel: ShopWindowViewModel = hiltViewModel(),
    appState: NiftyOrdersAppState,
    isExpandedScreen: Boolean
) {

    val uiState = viewModel.uiState

    ShopWindowRoute(
        uiState = uiState,
        appState = appState,
        isExpandedScreen = isExpandedScreen,
        doScroll = { lazyListState, coroutineScope ->
            viewModel.onEvent(
                ShopWindowEvent.ProductRowScroll(
                    lazyListState = lazyListState,
                    coroutineScope = coroutineScope
                )
            )
        },
        onProductClick = { productId ->
            viewModel.onEvent(
                ShopWindowEvent.ProductInDisplaySelect(
                    productId = productId
                )
            )
        },
        onQuantityIncrease = { cartLineId ->
            viewModel.onEvent(
                ShopWindowEvent.CartQuantityIncrease(
                    cartLineId = cartLineId
                )
            )
        },
        onQuantityDecrease = { cartLineId ->
            viewModel.onEvent(
                ShopWindowEvent.CartQuantityDecrease(
                    cartLineId = cartLineId
                )
            )
        },
        onProductInDisplaySelect = { productId ->
            viewModel.onEvent(
                ShopWindowEvent.ProductInDisplaySelect(
                    productId = productId
                )
            )
        },
        onFeaturedProductGroupSelect = { productGroupId ->
            viewModel.onEvent(
                ShopWindowEvent.FeaturedProductGroupSelect(
                    productGroupId = productGroupId
                )
            )
        },
        onAddToCart = { cartLine ->
            viewModel.onEvent(
                ShopWindowEvent.ProductAddToCart(
                    cartLine = cartLine
                )
            )
        },
        onDismissAddToCart = {
            viewModel.onEvent(
                ShopWindowEvent.AddToCartDismiss
            )
        }
    )
}

@Composable
private fun ShopWindowRoute(
    uiState: ShopWindowState,
    appState: NiftyOrdersAppState,
    isExpandedScreen: Boolean,
    doScroll: (LazyListState, CoroutineScope) -> Unit,
    onProductClick: (Long) -> Unit,
    onQuantityIncrease: (Long?) -> Unit,
    onQuantityDecrease: (Long?) -> Unit,
    onAddToCart: (CartLine) -> Unit,
    onProductInDisplaySelect: (Long) -> Unit,
    onFeaturedProductGroupSelect: (Long) -> Unit,
    onDismissAddToCart: () -> Unit
) {
    val shopWindowScreenType = getScreenType(
        isExpandedScreen = isExpandedScreen,
        uiState = uiState
    )

    when (shopWindowScreenType) {
        ShopWindowScreenType.ShopWindowScreenWithCart -> ShopWindowScreenWithCartScreen(
            uiState = uiState,
            doScroll = doScroll,
            onProductClick = onProductClick,
            onQuantityIncrease = onQuantityIncrease,
            onQuantityDecrease = onQuantityDecrease,
            onFeaturedProductGroupSelect = onFeaturedProductGroupSelect,
            onAddToCart = onAddToCart,
            onDismissAddToCart = onDismissAddToCart
        )
        ShopWindowScreenType.ShopWindowScreen -> ShopWindowScreen(
            appState = appState,
            uiState = uiState,
            doScroll = doScroll,
            onProductClick = onProductClick,
            onQuantityIncrease = onQuantityIncrease,
            onQuantityDecrease = onQuantityDecrease,
            onFeaturedProductGroupSelect = onFeaturedProductGroupSelect,
            onAddToCart = onAddToCart,
            onDismissAddToCart = onDismissAddToCart
        )
        ShopWindowScreenType.CartScreen -> CartScreen(
            productsInCart = uiState.productsInCart,
            cartTotal = uiState.cartTotal,
            onProductClick = onProductClick,
            onQuantityIncrease = onQuantityIncrease,
            onQuantityDecrease = onQuantityDecrease
        )
    }
}

class MenuElement(
    val caption: String,
    val drawableRes: Int
)

@Composable
private fun DrawerItem(
    menuElement: MenuElement,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    createDemoData: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable {
                scope.launch {
                    createDemoData()
                    scaffoldState.drawerState.close()
                }
            }
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            modifier = Modifier.size(32.dp),
            painter = painterResource(menuElement.drawableRes),
            contentDescription = "My orders icon",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = menuElement.caption,
            color = ThemeElements.colors.secondaryTextColor,
            style = MaterialTheme.typography.body1
        )
    }
    Divider(
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth(0.75f),
        color = ThemeElements.colors.secondaryTintColor
    )
}

@Composable
private fun DrawerContent(
    appState: NiftyOrdersAppState,
    demoDataCreation: () -> Unit
) {

    val menuElements = generateMenuElements()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ThemeElements.colors.primaryBackgroundColor)
            .padding(12.dp)
    ) {
        menuElements.forEach { menuElement ->
            DrawerItem(
                menuElement = menuElement,
                scope = rememberCoroutineScope(),
                scaffoldState = appState.scaffoldState,
                createDemoData = demoDataCreation
            )
        }
    }
}

private fun generateMenuElements(
): List<MenuElement> {

    val menuList = mutableListOf<MenuElement>()

    menuList.add(
        MenuElement(
            caption = "Create demo data",
            drawableRes = R.drawable.my_orders,
        )
    )

    return menuList
}

private enum class ShopWindowScreenType {
    ShopWindowScreen,
    ShopWindowScreenWithCart,
    CartScreen
}

private fun getScreenType(
    isExpandedScreen: Boolean,
    uiState: ShopWindowState
): ShopWindowScreenType {
    return when (isExpandedScreen) {
        true -> ShopWindowScreenType.ShopWindowScreenWithCart
        false -> {
            if (uiState.isCartOpen) ShopWindowScreenType.CartScreen else ShopWindowScreenType.ShopWindowScreen
        }
    }
}